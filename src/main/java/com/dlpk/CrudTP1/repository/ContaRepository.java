package com.dlpk.CrudTP1.repository;

import com.dlpk.CrudTP1.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>  {
    List<Conta> findByNomeContaining (String nome);
}
