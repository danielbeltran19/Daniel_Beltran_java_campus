package com.pruebaTecnica.sistemaGestion.Sales.aplication.services;

import com.pruebaTecnica.sistemaGestion.Sales.aplication.port.in.SalesInRepositoty;
import com.pruebaTecnica.sistemaGestion.Sales.aplication.port.out.SalesOutRepository;
import com.pruebaTecnica.sistemaGestion.Sales.domain.SalesDTO;
import com.pruebaTecnica.sistemaGestion.Sales.domain.SalesDTOResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesServices implements SalesInRepositoty {
    private  final SalesOutRepository salesOutRepository;

    public SalesServices(SalesOutRepository salesOutRepository){
        this.salesOutRepository = salesOutRepository;
    }

    @Override
    public Page<SalesDTOResponse> findAll(Pageable pageable) {
        return salesOutRepository.findAll(pageable);
    }

    @Override
    public void save(SalesDTO sales) {
        if (sales.getIdClients().isEmpty()){
            throw new IllegalArgumentException("El Idenfificador del cliente es requerido.");
        }
        if (sales.getProductId() == null ){
            throw new IllegalArgumentException("Los productos son requeridos.");
        }
        if (sales.getQuantity() == null){
            throw new IllegalArgumentException("Las cantidades son requeridas.");
        }
        salesOutRepository.save(sales);
    }

    @Override
    public List<SalesDTOResponse> findByClientId(String clientId) {
        return salesOutRepository.findByClientId(clientId);
    }
}
