package com.pruebaTecnica.sistemaGestion.Sales.domain;

import java.time.LocalDate;
import java.util.List;

public class SalesDTO {
    private String idClients;
    private Long productId;
    private Integer quantity;

    public SalesDTO() {
    }

    public String getIdClients() {
        return idClients;
    }

    public void setIdClients(String idClients) {
        this.idClients = idClients;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
