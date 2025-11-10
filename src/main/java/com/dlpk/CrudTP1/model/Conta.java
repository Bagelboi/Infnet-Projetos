package com.dlpk.CrudTP1.model;

import com.dlpk.CrudTP1.utils.FormattingUtil;
import jakarta.persistence.*;
import lombok.*;

import java.util.Arrays;
import java.util.stream.Collectors;

@Entity
@Table(name="conta")
@NoArgsConstructor
@AllArgsConstructor
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    private String nome;
    public void setNome(String nome) throws RuntimeException {
        this.nome = FormattingUtil.formatNome(nome);
    }

    @Getter
    @Setter
    private Double saldo;

    @Override
    public String toString() {
        return id + " - " + nome + " - " + saldo;
    }
}
