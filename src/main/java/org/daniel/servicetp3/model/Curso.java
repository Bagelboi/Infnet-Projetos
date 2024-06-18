package org.daniel.servicetp3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;
    Integer creditos;
    Integer horas;
    @ManyToMany(mappedBy = "cursos", fetch = FetchType.LAZY)
    Set<Aluno> alunos;
}
