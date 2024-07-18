package com.ekan.controller;

import com.ekan.model.Documento;
import com.ekan.service.DocumentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documentos")
public class DocumentoController {

    private final DocumentoService documentoService;

    public DocumentoController(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    @GetMapping("/{id}")
    public List<Documento> getDocumentos(Long id) {
        return documentoService.listarPorBeneficiario(id);
    }
}
