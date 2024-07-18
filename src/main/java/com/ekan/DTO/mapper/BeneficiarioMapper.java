package com.ekan.DTO.mapper;

import com.ekan.DTO.BeneficiarioDTO;
import com.ekan.model.Beneficiario;
import org.springframework.stereotype.Component;

@Component
public class BeneficiarioMapper {

    public BeneficiarioDTO convertBeneficiarioToDTO(Beneficiario ben) {
        return new BeneficiarioDTO(ben.getId(), ben.getNome(),  ben.getTelefone(), ben.getDataNascimento(),
                ben.getDataInclusao(), ben.getDataAtualizacao());
    }

    public Beneficiario convertDTOToBeneficiario(BeneficiarioDTO benDto) {
        return new Beneficiario.BeneficiarioBuilder()
                .id(benDto.id())
                .nome(benDto.nome())
                .telefone(benDto.telefone())
                .dataNascimento(benDto.dataNascimento())
                .dataInclusao(benDto.dataInclusao())
                .dataAtualizacao(benDto.dataAtualizacao())
                .build();
    }
}
