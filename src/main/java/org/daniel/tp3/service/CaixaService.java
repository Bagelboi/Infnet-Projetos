package org.daniel.tp3.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.daniel.tp3.domain.LinhaProducao;
import org.daniel.tp3.domain.RobotArm;
import org.daniel.tp3.repository.ICaixaRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.daniel.tp3.domain.Caixa;

@Service
public class CaixaService {
	@Autowired
	private LinhaProdService linha_service;

	@Autowired
	private ICaixaRep caixaRep;

	//private List<Caixa> caixas = new ArrayList<>();

	public void setService(LinhaProdService service) {
		linha_service = service;
	}

	public boolean incluir(Caixa caixa) {
		if (caixaRep.findById(caixa.getUID()).isPresent())
			return false;
		caixaRep.save(caixa);
		return true;
	}

	public void atualizar(Caixa caixa) {
		caixaRep.save(caixa);
	}
	
	public boolean excluir(String uid) {
		caixaRep.deleteById(uid);
		return caixaRep.findById(uid).isEmpty();
	}
	
	public Collection<Caixa> obter() {
		return (Collection<Caixa>) caixaRep.findAll();
	}
	
	public Caixa obterId(String uid) {
		return caixaRep.findById(uid).get();
	}
	
	public boolean registrado(Caixa caixa) {
		return caixaRep.findById(caixa.getUID()).isPresent();
	}

	public boolean transferirCaixa(String id, Integer linha_id) {
		LinhaProducao linhaGet = linha_service.obterId(linha_id);
		Caixa caixaGet = this.obterId(id);
		if (linhaGet != null && caixaGet != null) {
			caixaGet.setLinha(linhaGet);
			this.atualizar(caixaGet);
			return true;
		}
		return false;
	}

	public boolean roboParado(String id) {
		Caixa caixaGet = this.obterId(id);
		if (caixaGet != null) {
			return caixaGet.getLinha() != null;
		}
		return true;
	}

	public boolean roboRegistrado(String id, Integer linha_id) {
		LinhaProducao linhaGet = linha_service.obterId(linha_id);
		Caixa caixaGet = this.obterId(id);
		if (linhaGet != null && caixaGet != null) {
			return caixaGet.getLinha().getId().equals(linhaGet.getId());
		}
		return false;
	}

	public Integer quantCartas(String cardid) {
		Integer quant = 0;
		for (Caixa caixa : caixaRep.findAll()) {
			if (caixa.getCardId().equals(cardid))
				quant += caixa.getQuantCartas();
		}
		return quant;
	}
}
