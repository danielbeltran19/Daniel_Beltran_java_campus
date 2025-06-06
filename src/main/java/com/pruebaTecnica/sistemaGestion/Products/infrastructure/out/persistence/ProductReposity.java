package com.pruebaTecnica.sistemaGestion.Products.infrastructure.out.persistence;

import com.pruebaTecnica.sistemaGestion.Products.aplication.port.out.ProductOutRepository;
import com.pruebaTecnica.sistemaGestion.Products.domain.models.ProductsDTO;
import com.pruebaTecnica.sistemaGestion.Products.infrastructure.out.entity.ProductsEntity;
import com.pruebaTecnica.sistemaGestion.Products.infrastructure.out.mapper.ProductsMapper;
import com.pruebaTecnica.sistemaGestion.Products.infrastructure.out.repositoty.ProductJpaRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductReposity implements ProductOutRepository {
    private final ProductsMapper productsMapper;
    private final ProductJpaRepository productJpaRepository;

    public ProductReposity(ProductsMapper productsMapper, ProductJpaRepository productJpaRepository){
        this.productJpaRepository = productJpaRepository;
        this.productsMapper = productsMapper;
    }

    @Override
    public ProductsDTO findById(Long id) {
        ProductsEntity products = productJpaRepository.findById(id).orElseThrow(() -> new EntityExistsException("No se encontro el producto"));
        return productsMapper.toDomain(products);
    }


    @Override
    public ProductsDTO save(ProductsDTO products) {
        ProductsEntity productsEntity = productsMapper.toEntity(products);
        productJpaRepository.save(productsEntity);
        return productsMapper.toDomain(productsEntity);
    }

    @Override
    public void update(ProductsDTO products) {

        Optional<ProductsEntity> existingProducts = productJpaRepository.findById(products.getId());

        if (existingProducts.isEmpty()){
            throw  new IllegalArgumentException("Producto no encontrado con Id "+products.getId());
        }

        ProductsEntity exisProductsEntity = existingProducts.get();
        Integer stockActual = exisProductsEntity.getStockQuantity();

        exisProductsEntity.setName(products.getName());
        exisProductsEntity.setSku(products.getSku());
        exisProductsEntity.setPrice(products.getPrice());
        exisProductsEntity.setCategoryName(products.getCategoryName());

        exisProductsEntity.setStockQuantity(stockActual);
        productJpaRepository.save(exisProductsEntity);
    }

    @Override
    public Page<ProductsDTO> findAll(Pageable pageable) {
        Page<ProductsEntity> productsEntities = productJpaRepository.findAll(pageable);
        Page<ProductsDTO> productsPage = productsEntities.map(productsMapper::toDomain);
        return productsPage;
    }

    @Override
    public List<ProductsDTO> findByCategoryName(String categoryName) {
        Specification<ProductsEntity> spec = (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("categoryName")),
                        "%" + categoryName.toLowerCase() + "%"
                );

        List<ProductsEntity> productsEntities = productJpaRepository.findAll(spec);
        return productsEntities.stream()
                .map(productsMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        ProductsEntity productsEntity = productJpaRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Producto no encontrado."));
        productJpaRepository.deleteById(productsEntity.getId());
    }
}
