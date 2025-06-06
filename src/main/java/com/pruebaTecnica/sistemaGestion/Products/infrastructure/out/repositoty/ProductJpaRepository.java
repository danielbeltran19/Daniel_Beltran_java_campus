package com.pruebaTecnica.sistemaGestion.Products.infrastructure.out.repositoty;

import com.pruebaTecnica.sistemaGestion.Products.infrastructure.out.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository  extends JpaRepository<ProductsEntity, Long>, JpaSpecificationExecutor<ProductsEntity> {

}
