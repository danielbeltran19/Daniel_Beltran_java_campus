package com.pruebaTecnica.sistemaGestion.Sales.infrastructure.out.repository;

import com.pruebaTecnica.sistemaGestion.Sales.infrastructure.out.entity.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesJpaRepository extends JpaRepository<SalesEntity, Long>, JpaSpecificationExecutor<SalesEntity> {
    @Query("SELECT s FROM SalesEntity s WHERE s.IdClients = :clientId")
    List<SalesEntity> findSalesByClientId(@Param("clientId") String clientId);

}
