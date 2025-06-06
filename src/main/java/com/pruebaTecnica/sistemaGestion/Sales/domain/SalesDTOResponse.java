package com.pruebaTecnica.sistemaGestion.Sales.domain;

import java.time.LocalDate;
import java.util.List;

public class SalesDTOResponse {
    private Long id;
    private String idClients;
    private String productName;
    private Integer quantity;
    private LocalDate saleDate;
    private Double totalSaleAmount;

    public SalesDTOResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdClients() {
        return idClients;
    }

    public void setIdClients(String idClients) {
        this.idClients = idClients;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public Double getTotalSaleAmount() {
        return totalSaleAmount;
    }

    public void setTotalSaleAmount(Double totalSaleAmount) {
        this.totalSaleAmount = totalSaleAmount;
    }
}
