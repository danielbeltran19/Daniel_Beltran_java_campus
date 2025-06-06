package com.pruebaTecnica.sistemaGestion.Sales.infrastructure.in;

import com.pruebaTecnica.sistemaGestion.Sales.aplication.services.SalesServices;
import com.pruebaTecnica.sistemaGestion.Sales.domain.SalesDTO;
import com.pruebaTecnica.sistemaGestion.Sales.domain.SalesDTOResponse;
import com.pruebaTecnica.sistemaGestion.shared.util.ResponseData;
import com.pruebaTecnica.sistemaGestion.shared.util.ResponseMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class SalesController {
    private final SalesServices salesServices;

    public SalesController(SalesServices salesServices){
        this.salesServices = salesServices;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> savedSales(@RequestBody SalesDTO sales){
        salesServices.save(sales);
        ResponseMessage response = new ResponseMessage(
                HttpStatus.CREATED.name(),
                HttpStatus.CREATED.value(),
                "Venta creada correctamente"
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseData> getAll(Pageable pageable){
        Page<SalesDTOResponse> sales = salesServices.findAll(pageable);
        ResponseData response = new ResponseData(HttpStatus.OK.name(), HttpStatus.OK.value(), sales.getContent());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cliente/{clientId}")
    public ResponseEntity<ResponseData> getSalesByClientId(@PathVariable String clientId) {
        List<SalesDTOResponse> sales = salesServices.findByClientId(clientId);
        ResponseData response = new ResponseData(
                HttpStatus.OK.name(),
                HttpStatus.OK.value(),
                sales
        );
        return ResponseEntity.ok(response);
    }

}
