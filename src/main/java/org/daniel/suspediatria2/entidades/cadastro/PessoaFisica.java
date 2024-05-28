package org.daniel.suspediatria2.entidades.cadastro;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Calendar;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PessoaFisica {
    @Id @Column(length = 11)
    private String cpf_id;
    private String nome;
    @Temporal(TemporalType.DATE)
    private Calendar data_nascimento;
    private Long telefone;

    public PessoaFisica() {}

    public String getCpf_id() {
        return cpf_id;
    }

    public void setCpf_id(String cpf_id) {
        this.cpf_id = cpf_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Calendar getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Calendar data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }
}
