package org.daniel.tp2.domain;

public class LinhaProducao {
	private final Integer id;
	private boolean ativa;
	private final String setor;
	
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
