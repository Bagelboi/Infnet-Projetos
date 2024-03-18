package org.daniel.tp3.service;

import java.util.*;

import org.daniel.tp3.repository.ILinhaProdRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.daniel.tp3.domain.Caixa;
import org.daniel.tp3.domain.LinhaProducao;
import org.daniel.tp3.domain.RobotArm;

@Service
public class LinhaProdService {
	//id objeto, id linha
	//private HashMap<Integer, Integer> robos = new HashMap<>();
	//private HashMap<String, Integer> caixas = new HashMap<>();
	
	//linha de producao
	//private List<LinhaProducao> linhas = new ArrayList<>();
	@Autowired
	private ILinhaProdRep linhaProdRep;


	public boolean incluir(LinhaProducao linha) {
		if (linhaProdRep.findById(linha.getId()).isPresent())
			return false;
		linhaProdRep.save(linha);
		return true;
	}

	public void atualizar(LinhaProducao linha) {
		linhaProdRep.save(linha);
	}


	public boolean excluir(Integer id) {
		linhaProdRep.deleteById(id);
		return linhaProdRep.findById(id).isEmpty();
	}

	public Collection<LinhaProducao> obter() {
		return (Collection<LinhaProducao>) linhaProdRep.findAll();
	}

	public LinhaProducao obterId(Integer id) {
		return linhaProdRep.findById(id).get();
	}

	public boolean registrado(LinhaProducao linha) {
		return linhaProdRep.findById(linha.getId()).isPresent();
	}
	
}
