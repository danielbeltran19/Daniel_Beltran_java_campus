package com.pruebaTecnica.sistemaGestion.Products.infrastructure.out.mapper;

import com.pruebaTecnica.sistemaGestion.Products.domain.models.ProductsDTO;
import com.pruebaTecnica.sistemaGestion.Products.infrastructure.out.entity.ProductsEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductsMapper {

    public ProductsDTO toDomain(ProductsEntity entity) {
        if (entity == null) {
            return null;
        }
        ProductsDTO product = new ProductsDTO();
        product.setId(entity.getId());
        product.setName(entity.getName());
        product.setSku(entity.getSku());
        product.setPrice(entity.getPrice());
        product.setStockQuantity(entity.getStockQuantity());
        product.setCategoryName(entity.getCategoryName());
        return product;
    }

    public ProductsEntity toEntity(ProductsDTO products){
        if (products == null){
            return null;
        }
        ProductsEntity entity = new ProductsEntity();
        entity.setId(products.getId());
        entity.setName(products.getName());
        entity.setSku(products.getSku());
        entity.setPrice(products.getPrice());
        entity.setStockQuantity(products.getStockQuantity());
        entity.setCategoryName(products.getCategoryName());
        return entity;
    }

}
