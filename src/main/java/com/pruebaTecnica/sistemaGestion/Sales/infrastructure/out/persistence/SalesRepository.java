package com.pruebaTecnica.sistemaGestion.Sales.infrastructure.out.persistence;

import com.pruebaTecnica.sistemaGestion.Products.infrastructure.out.entity.ProductsEntity;
import com.pruebaTecnica.sistemaGestion.Products.infrastructure.out.repositoty.ProductJpaRepository;
import com.pruebaTecnica.sistemaGestion.Sales.aplication.port.out.SalesOutRepository;
import com.pruebaTecnica.sistemaGestion.Sales.domain.SalesDTO;
import com.pruebaTecnica.sistemaGestion.Sales.domain.SalesDTOResponse;
import com.pruebaTecnica.sistemaGestion.Sales.infrastructure.out.entity.SalesEntity;
import com.pruebaTecnica.sistemaGestion.Sales.infrastructure.out.mapper.SalesMapper;
import com.pruebaTecnica.sistemaGestion.Sales.infrastructure.out.repository.SalesJpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SalesRepository implements SalesOutRepository {
    private final SalesMapper salesMapper;
    private final SalesJpaRepository salesJpaRepository;
    private final ProductJpaRepository productJpaRepository;

    public SalesRepository(SalesMapper salesMapper, SalesJpaRepository salesJpaRepository, ProductJpaRepository productJpaRepository){
        this.salesMapper = salesMapper;
        this.salesJpaRepository = salesJpaRepository;
        this.productJpaRepository = productJpaRepository;
    }

    @Override
    public Page<SalesDTOResponse> findAll(Pageable pageable) {
        Page<SalesEntity> salesEntities = salesJpaRepository.findAll(pageable);
        Page<SalesDTOResponse> salesDTOResponses = salesEntities.map(salesMapper::toDomain);
        return salesDTOResponses;
    }

    @Override
    @Transactional
    public void save(SalesDTO sales) {
        ProductsEntity product = productJpaRepository.findById(sales.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + sales.getProductId()));

        if (product.getStockQuantity() == null || product.getStockQuantity() < sales.getQuantity()) {
            throw new IllegalArgumentException("Stock insuficiente para el producto: " + product.getName());
        }

        SalesEntity sale = salesMapper.toEntity(sales, product);
        sale.setSaleDate(LocalDate.now());

        double totalAmount = sales.getQuantity() * product.getPrice();
        sale.setTotalSaleAmount(totalAmount);

        product.setStockQuantity(product.getStockQuantity() - sales.getQuantity());

        salesJpaRepository.save(sale);
        productJpaRepository.save(product);
    }

    @Override
    public List<SalesDTOResponse> findByClientId(String clientId) {
        List<SalesEntity> salesEntities = salesJpaRepository.findSalesByClientId(clientId);
        return salesEntities.stream()
                .map(salesMapper::toDomain)
                .collect(Collectors.toList());
    }
}
