package com.ekan.repository;

import com.ekan.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {

    @Query("SELECT d from Documento d where d.beneficiario = :beneficiario")
    public List<Documento> procurarDocumentosPorBeneficiario(@Param("beneficiario") Long beneficiario);

}
