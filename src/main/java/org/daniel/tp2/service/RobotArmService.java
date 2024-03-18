package org.daniel.tp2.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.daniel.tp2.domain.RobotArm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.daniel.tp2.domain.RobotArm;

@Service
public class RobotArmService {
	@Autowired
	private LinhaProdService linha_service;
	
	private List<RobotArm> robos = new ArrayList<>();

	public void setService(LinhaProdService service) {
		linha_service = service;
	}
	public boolean incluir(RobotArm robo) {
		for (RobotArm roboReg : this.robos) {
			if (roboReg.getId() == robo.getId())
				return false;
		}
		robos.add(robo);
		linha_service.adicionarRobo(robo, -1);
		return true;
	}
	
	public boolean excluir(int id) {
		linha_service.removerRobo(id);
		return robos.removeIf( roboReg -> roboReg.getId() == id );
	}
	
	public Collection<RobotArm> obter() {
		return robos;
	}
	
	public RobotArm obterId(int id) {
		for (RobotArm roboReg : this.robos)
			if (roboReg.getId() == id)
				return roboReg;
		return null;
	}
	
	public boolean registrado(RobotArm robo) {
		return robos.contains(robo);
	}
}
