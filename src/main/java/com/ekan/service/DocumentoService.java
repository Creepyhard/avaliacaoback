package com.ekan.service;

import com.ekan.model.Documento;
import com.ekan.repository.DocumentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentoService {

    private DocumentoRepository documentoRepository;

    public DocumentoService(DocumentoRepository documentoRepository) {
        this.documentoRepository = documentoRepository;
    }

    public List<Documento> listarPorBeneficiario(Long beneficiarioId) {
        return documentoRepository.procurarDocumentosPorBeneficiario(beneficiarioId);
    }
}