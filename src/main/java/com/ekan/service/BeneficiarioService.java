package com.ekan.service;

import com.ekan.DTO.BeneficiarioDTO;
import com.ekan.DTO.mapper.BeneficiarioMapper;
import com.ekan.exception.ResourceNotFoundException;
import com.ekan.model.Beneficiario;
import com.ekan.model.Documento;
import com.ekan.repository.BeneficiarioRepository;
import com.ekan.repository.DocumentoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeneficiarioService {

    private final BeneficiarioRepository beneficiarioRepository;
    private final DocumentoRepository documentoRepository;
    private final BeneficiarioMapper benMapper;

    public BeneficiarioService(BeneficiarioRepository beneficiarioRepository, BeneficiarioMapper benMapper, DocumentoRepository documentoRepository) {
        this.beneficiarioRepository = beneficiarioRepository;
        this.benMapper = benMapper;
        this.documentoRepository = documentoRepository;
    }

    public BeneficiarioDTO salvarService(@RequestBody BeneficiarioDTO benDTO) {
        Beneficiario ben = new Beneficiario.BeneficiarioBuilder().createBen(
                        benDTO.nome(),
                        benDTO.telefone(),
                        benDTO.dataNascimento())
                        .dataInclusao(benDTO.dataInclusao())
                        .build();
        return benMapper.convertBeneficiarioToDTO(beneficiarioRepository.save(ben));
    }

    public BeneficiarioDTO salvarBenegiciarioEDocumentoService(@RequestBody BeneficiarioDTO benDTO, @RequestBody Documento doc) {
        Beneficiario ben = new Beneficiario.BeneficiarioBuilder().createBen(
                        benDTO.nome(),
                        benDTO.telefone(),
                        benDTO.dataNascimento())
                        .dataInclusao(benDTO.dataInclusao())
                        .build();
        BeneficiarioDTO benConvertidoDTO = benMapper.convertBeneficiarioToDTO(beneficiarioRepository.save(ben));
        documentoRepository.save(doc);

        return benConvertidoDTO;
    }

    public List<BeneficiarioDTO> listarTodosService() {
        return beneficiarioRepository.findAll().stream().map(benMapper::convertBeneficiarioToDTO).collect(Collectors.toList());
    }

    public BeneficiarioDTO buscarPorIdService(@PathVariable Long id) {
        return beneficiarioRepository.findById(id).map(benMapper::convertBeneficiarioToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Beneficiario não encontrado:" + id));
    }

    public ResponseEntity<Object> deletarService(@PathVariable Long id) {
        return beneficiarioRepository.findById(id).map(record -> {
            beneficiarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Beneficiario não encontrado:" + id));
    }

    public ResponseEntity<BeneficiarioDTO> attBeneficiarioService(Long id, BeneficiarioDTO benDTODetails){
        Beneficiario ben = beneficiarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id não encontrado:" + id + " Confirme a entrada!!"));
        ben = new Beneficiario.BeneficiarioBuilder().attBen(
                benDTODetails.nome(),
                benDTODetails.telefone(),
                ben.getDataNascimento())
                .dataAtualizacao(ben.getDataAtualizacao())
                .build();
        Beneficiario attBeneficiario = beneficiarioRepository.save(ben);

        return ResponseEntity.ok(benMapper.convertBeneficiarioToDTO(attBeneficiario));
    }
}
