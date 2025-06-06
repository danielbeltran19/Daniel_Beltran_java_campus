package com.pruebaTecnica.sistemaGestion.Products.aplication.port.out;

import com.pruebaTecnica.sistemaGestion.Products.domain.models.ProductsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductOutRepository {
    ProductsDTO findById(Long id);
    ProductsDTO save(ProductsDTO products);
    void update(ProductsDTO products);
    void deleteById(Long id);
    Page<ProductsDTO> findAll(Pageable pageable);
    List<ProductsDTO> findByCategoryName(String categoryName);
}
