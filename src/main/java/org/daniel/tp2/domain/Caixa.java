package org.daniel.tp2.domain;

import javax.vecmath.Vector3d;

public class Caixa {
	private final String UID; // produto dentro
	private final Vector3d tamanho;
	private Vector3d posicao;
	private final boolean fragil;
	
	public Caixa(String sku, double base_tamanho, double altura, Vector3d posicao, boolean fragil) {
		super();
		this.UID = sku.replace(' ', '_');
		this.tamanho = new Vector3d(base_tamanho, altura, base_tamanho);
		this.posicao = posicao;
		this.fragil = fragil;
	}

	

	public String getUID() {
		return UID;
	}



	public Vector3d getPosicao() {
		return posicao;
	}

	public void mover(Vector3d posicao) {
		this.posicao = posicao;
	}

	public boolean isFragil() {
		return fragil;
	}
	
	
	public double getAltura() {
		return tamanho.y;
	}
	
	public double getPerimetro() {
		return tamanho.x * tamanho.z;
	}
	
	public Vector3d getDimensoes() {
		return tamanho;
	}

	@Override
	public String toString() {
		return "\nUID:" + getUID();
	}
}
