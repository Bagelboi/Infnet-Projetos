package org.daniel.servicoat.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;
    String endereco;
    Integer telefone;
    @Temporal(TemporalType.TIMESTAMP)
    Date dataNascimento;
    @ManyToOne(fetch = FetchType.LAZY)
    Departamento dept;
}