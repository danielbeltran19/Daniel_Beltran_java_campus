package com.pruebaTecnica.sistemaGestion.Sales.infrastructure.out.mapper;


import com.pruebaTecnica.sistemaGestion.Products.infrastructure.out.entity.ProductsEntity;
import com.pruebaTecnica.sistemaGestion.Sales.domain.SalesDTO;
import com.pruebaTecnica.sistemaGestion.Sales.domain.SalesDTOResponse;
import com.pruebaTecnica.sistemaGestion.Sales.infrastructure.out.entity.SalesEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class SalesMapper {
    public SalesDTOResponse toDomain(SalesEntity entity) {
        if (entity == null) {
            return null;
        }

        SalesDTOResponse response = new SalesDTOResponse();
        response.setId(entity.getId());
        response.setIdClients(entity.getIdClients());
        response.setProductName(entity.getProduct().getName());
        response.setQuantity(entity.getQuantityPurchased());
        response.setSaleDate(entity.getSaleDate());
        response.setTotalSaleAmount(entity.getTotalSaleAmount());

        return response;
    }

    public SalesEntity toEntity(SalesDTO sales, ProductsEntity product) {
        if (sales == null || product == null) {
            return null;
        }

        SalesEntity entity = new SalesEntity();
        entity.setIdClients(sales.getIdClients());
        entity.setProduct(product);
        entity.setQuantityPurchased(sales.getQuantity());
        return entity;
    }

}
