package com.pruebaTecnica.sistemaGestion.Products.infrastructure.in.rest;

import com.pruebaTecnica.sistemaGestion.Products.aplication.services.ProductServices;
import com.pruebaTecnica.sistemaGestion.Products.domain.models.ProductsDTO;
import com.pruebaTecnica.sistemaGestion.shared.util.ResponseData;
import com.pruebaTecnica.sistemaGestion.shared.util.ResponseMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductsController {

    private final ProductServices productServices;

    public ProductsController(ProductServices productServices){
        this.productServices = productServices;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> savedProduct(@RequestBody ProductsDTO products){
        @SuppressWarnings("unused")
        ProductsDTO savedProducts = productServices.save(products);
        ResponseMessage response = new ResponseMessage(
                HttpStatus.CREATED.name(),
                HttpStatus.CREATED.value(),
                "Producto creado correctamente"
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseData> getAll(Pageable pageable){
        Page<ProductsDTO> products = productServices.findAll(pageable);
        ResponseData response = new ResponseData(HttpStatus.OK.name(), HttpStatus.OK.value(), products.getContent());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseData> getProductById(@PathVariable("id") Long id) {
        ProductsDTO products = productServices.findById(id);

        ResponseData response = new ResponseData(HttpStatus.OK.name(), HttpStatus.OK.value(), products);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filtrar")
    public ResponseEntity<ResponseData> filtrarPorCategoria(@RequestParam String categoria) {
        List<ProductsDTO> productos = productServices.findByCategoryName(categoria);
        ResponseData response = new ResponseData(HttpStatus.OK.name(), HttpStatus.OK.value(), productos);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ProductsDTO products) {
        productServices.update(products);
        ResponseMessage response = new ResponseMessage(HttpStatus.OK.name(), HttpStatus.OK.value(), "Se actualizo el producto.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessage> deleteCategory(@PathVariable Long id) {
        try {
            productServices.deleteById(id);
            ResponseMessage response = new ResponseMessage(HttpStatus.OK.name(), HttpStatus.OK.value(), "Producto eliminado correctamente"
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseMessage response = new ResponseMessage(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(),  e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}
