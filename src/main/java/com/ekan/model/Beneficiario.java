package com.ekan.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Beneficiario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private Date dataNascimento;
    private Date dataInclusao;
    private Date dataAtualizacao;

    @OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Documento> documentos;

    private Beneficiario(BeneficiarioBuilder beneficiarioBuilder) {
        this.id = beneficiarioBuilder.id;
        this.nome = beneficiarioBuilder.nome;
        this.telefone = beneficiarioBuilder.telefone;
        this.dataNascimento = beneficiarioBuilder.dataNascimento;
        this.dataInclusao = beneficiarioBuilder.dataInclusao;
        this.dataAtualizacao = beneficiarioBuilder.dataAtualizacao;
    }

    public Beneficiario() {

    }

    public static class BeneficiarioBuilder {
        private Long id;
        private String nome;
        private String telefone;
        private Date dataNascimento;
        private Date dataInclusao;
        private Date dataAtualizacao;

        public BeneficiarioBuilder createBen(String nome, String telefone, Date dataNascimento) {
            this.nome = nome;
            this.telefone = telefone;
            this.dataNascimento = dataNascimento;
            return this;
        }

        public BeneficiarioBuilder attBen(String nome, String telefone, Date dataNascimento) {
            this.nome = nome;
            this.telefone = telefone;
            this.dataNascimento = dataNascimento;
            return this;
        }

        public BeneficiarioBuilder id(Long id) {
            this.id = id;
            return this;
        }
        public BeneficiarioBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }
        public BeneficiarioBuilder telefone(String telefone) {
            this.telefone = telefone;
            return this;
        }
        public BeneficiarioBuilder dataNascimento(Date dataNascimento) {
            this.dataNascimento = dataNascimento;
            return this;
        }
        public BeneficiarioBuilder dataInclusao(Date dataInclusao) {
            if (dataInclusao != null) {
                this.dataInclusao = new Timestamp(System.currentTimeMillis());
            }
            return this;
        }
        public BeneficiarioBuilder dataAtualizacao(Date dataAtualizacao) {
            if (dataAtualizacao != null) {
                this.dataAtualizacao = new Timestamp(System.currentTimeMillis());
            }
            return this;
        }

        public Beneficiario build() {
            return new Beneficiario(this);
        }
    }
}
