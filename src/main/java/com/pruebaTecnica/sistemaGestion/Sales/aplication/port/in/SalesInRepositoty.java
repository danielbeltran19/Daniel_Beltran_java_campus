package com.pruebaTecnica.sistemaGestion.Sales.aplication.port.in;

import com.pruebaTecnica.sistemaGestion.Sales.domain.SalesDTO;
import com.pruebaTecnica.sistemaGestion.Sales.domain.SalesDTOResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SalesInRepositoty {
    void save(SalesDTO sales);
    Page<SalesDTOResponse> findAll(Pageable pageable);
    List<SalesDTOResponse> findByClientId(String clientId);
}
