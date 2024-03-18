package org.daniel.tp3.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.daniel.tp3.domain.Caixa;
import org.daniel.tp3.domain.LinhaProducao;
import org.daniel.tp3.domain.RobotArm;
import org.daniel.tp3.repository.ICaixaRep;
import org.daniel.tp3.repository.IRobotArmRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.daniel.tp3.domain.RobotArm;

@Service
public class RobotArmService {
	@Autowired
	private LinhaProdService linha_service;

	@Autowired
	private IRobotArmRep roboRep;

	//private List<Caixa> caixas = new ArrayList<>();

	public void setService(LinhaProdService service) {
		linha_service = service;
	}

	public boolean incluir(RobotArm robo) {
		if (roboRep.findById(robo.getId()).isPresent())
			return false;
		roboRep.save(robo);
		return true;
	}

	public void atualizar(RobotArm robo) {
		roboRep.save(robo);
	}
	public boolean excluir(Integer id) {
		roboRep.deleteById(id);
		return roboRep.findById(id).isEmpty();
	}

	public Collection<RobotArm> obter() {
		return (Collection<RobotArm>) roboRep.findAll();
	}

	public RobotArm obterId(Integer id) {
		return roboRep.findById(id).get();
	}

	public boolean registrado(RobotArm robo) {
		return roboRep.findById(robo.getId()).isPresent();
	}

	//linha_service

	public boolean transferirRobo(Integer id, Integer linha_id) {
		LinhaProducao linhaGet = linha_service.obterId(linha_id);
		RobotArm roboGet = this.obterId(id);
		if (linhaGet != null && roboGet != null) {
			roboGet.setLinha(linhaGet);
			this.atualizar(roboGet);
			return true;
		}
		return false;
	}

	public boolean roboParado(Integer id) {
		RobotArm roboGet = this.obterId(id);
		if (roboGet != null) {
			return roboGet.getLinha() != null;
		}
		return true;
	}

	public boolean roboRegistrado(Integer id, Integer linha_id) {
		LinhaProducao linhaGet = linha_service.obterId(linha_id);
		RobotArm roboGet = this.obterId(id);
		if (linhaGet != null && roboGet != null) {
			return roboGet.getLinha().getId().equals(linhaGet.getId());
		}
		return false;
	}
}

