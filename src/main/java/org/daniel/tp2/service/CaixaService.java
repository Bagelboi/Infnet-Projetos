package org.daniel.tp2.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.daniel.tp2.domain.Caixa;

@Service
public class CaixaService {
	@Autowired
	private LinhaProdService linha_service;
	
	private List<Caixa> caixas = new ArrayList<>();

	public void setService(LinhaProdService service) {
		linha_service = service;
	}

	public boolean incluir(Caixa caixa) {
		for (Caixa caixaReg : this.caixas) {
			if (caixaReg.getUID() == caixa.getUID())
				return false;
		}
		caixas.add(caixa);
		linha_service.adicionarCaixa(caixa, -1);
		return true;
	}
	
	public boolean excluir(String sku) {
		linha_service.removerCaixas(sku);
		return caixas.removeIf( caixaReg -> caixaReg.getUID() == sku );
	}
	
	public Collection<Caixa> obter() {
		return caixas;
	}
	
	public Caixa obterId(String sku) {
		for (Caixa caixaReg : this.caixas)
			if (caixaReg.getUID() == sku)
				return caixaReg;
		return null;
	}
	
	public boolean registrado(Caixa caixa) {
		return caixas.contains(caixa);
	}
}
