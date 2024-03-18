package org.daniel.tp3.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LinhaProducao {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;
	private boolean ativa;
	private String setor;

	public LinhaProducao() {
	}

	public LinhaProducao(Integer id, String setor, boolean ativa) {
		this.id = Math.max(0, id);
		this.setor = setor;
		setAtiva(ativa);
	}

	public Integer getId() {
		return id;
	}

	public String getSetor() {return setor;}
	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}

	@Override
	public String toString() {
		return "\nLinha:" + getSetor() + "\nID:" + getId();
	}
}
