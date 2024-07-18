package com.ekan.DTO;

import java.util.Date;
import java.util.List;

public record BeneficiarioDTO(Long id,
                              String nome,
                              String telefone,
                              Date dataNascimento,
                              Date dataInclusao,
                              Date dataAtualizacao) {
}
