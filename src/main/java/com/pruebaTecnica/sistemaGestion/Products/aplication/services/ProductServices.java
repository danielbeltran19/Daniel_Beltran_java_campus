package com.pruebaTecnica.sistemaGestion.Products.aplication.services;

import com.pruebaTecnica.sistemaGestion.Products.aplication.port.in.ProductInRepositoy;
import com.pruebaTecnica.sistemaGestion.Products.aplication.port.out.ProductOutRepository;
import com.pruebaTecnica.sistemaGestion.Products.domain.models.ProductsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices implements ProductInRepositoy {

    private final ProductOutRepository productOutRepository;

    public ProductServices(ProductOutRepository productOutRepository){
        this.productOutRepository = productOutRepository;
    }

    @Override
    public ProductsDTO findById(Long id) {
        return productOutRepository.findById(id);
    }

    @Override
    public Page<ProductsDTO> findAll(Pageable pageable) {
        return productOutRepository.findAll(pageable);
    }

    @Override
    public List<ProductsDTO> findByCategoryName(String categoryName) {
        return productOutRepository.findByCategoryName(categoryName);
    }

    @Override
    public ProductsDTO save(ProductsDTO products) {
        return productOutRepository.save(products);
    }

    @Override
    public void update(ProductsDTO products) {
        if (products.getId() == null) {
            throw new IllegalArgumentException("El Id es requerido para la actualizacion.");
        }
        productOutRepository.update(products);
    }

    @Override
    public void deleteById(Long id) {
        productOutRepository.deleteById(id);
    }
}
