package org.daniel.tp2.service;

import java.util.*;

import org.springframework.stereotype.Service;

import org.daniel.tp2.domain.Caixa;
import org.daniel.tp2.domain.LinhaProducao;
import org.daniel.tp2.domain.RobotArm;

@Service
public class LinhaProdService {
	//id objeto, id linha
	private HashMap<Integer, Integer> robos = new HashMap<>();
	private HashMap<String, Integer> caixas = new HashMap<>();
	
	//linha de producao
	private List<LinhaProducao> linhas = new ArrayList<>();
	public boolean incluir(LinhaProducao linha) {
		for (LinhaProducao linhaReg : this.linhas) {
			if (linhaReg.getId() == linha.getId())
				return false;
		}
		linhas.add(linha);
		return true;
	}
	
	public boolean excluir(Integer id) {
		boolean toRem = linhas.removeIf( linha -> linha.getId() == id );
		if (toRem) {
			for (int obj_id : robos.keySet())
				robos.remove(obj_id, id);
			for (String obj_id : caixas.keySet())
				caixas.remove(obj_id, id);
		}
		return toRem;
	}
	
	public Collection<LinhaProducao> obter() {
		return linhas;
	}
	
	public LinhaProducao obterId(int id) {
		for (LinhaProducao linha : this.linhas)
			if (linha.getId() == id)
				return linha;
		return null;
	}
	
	public boolean registrado(LinhaProducao linha) {
		return linhas.contains(linha);
	}
	
	public boolean registradoId(Integer id) {
		if (id == -1)
			return true;
		for(LinhaProducao linha : linhas) {
			if (linha.getId() == id)
				return true;
		}
		return false;
	}
	
	//robos
	public boolean transferirRobo(Integer id, Integer linha_id) {
		if (registradoId(linha_id) && robos.containsKey(id)) {
			robos.put(id, linha_id);
			return true;
		}
		return false;
	}
	
	public boolean adicionarRobo(RobotArm robo, Integer linha_id) {
		if (registradoId(linha_id) && !robos.containsKey(robo.getId())) {
			robos.put(robo.getId(), linha_id);
			return true;
		}
		return false;
	}

	public boolean removerRobo(Integer index) {
		return robos.remove(index) != null;
	}

	public boolean roboParado(Integer id) {
		return robos.get(id) < 0;
	}

	public boolean roboRegistrado(int id) {
		return robos.containsKey(id);
	}

	//caixas
	public boolean transferirCaixa(String id, Integer linha_id) {
		if (registradoId(linha_id) && caixas.containsKey(id)) {
			caixas.put(id, linha_id);
			return true;
		}
		return false;
	}
	
	public boolean adicionarCaixa(Caixa caixa, Integer linha_id) {
		if (registradoId(linha_id) && !caixas.containsKey(caixa.getUID())) {
			caixas.put(caixa.getUID(), linha_id);
			return true;
		}
		return false;
	}

	public boolean removerCaixas(String index) {
		return caixas.remove(index) != null;
	}

	public boolean caixaParada(String id) {
		return caixas.get(id) < 0;
	}

	public boolean caixaRegistrado(String sku) {
		return caixas.containsKey(sku);
	}
	
}
