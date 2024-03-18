package org.daniel.tp3.domain;

import jakarta.persistence.*;
import org.daniel.tp3.Application;
import org.daniel.tp3.service.LinhaProdService;
import org.springframework.data.annotation.Transient;

import javax.vecmath.Vector3d;

@Entity
public class Caixa {
	@Id
	private String UID; // produto dentro
	private Vector3d tamanho;
	private Vector3d posicao;
	private boolean fragil;

	private String cardId = null;
	@ManyToOne
	@JoinColumn(name = "linha_producao_id", referencedColumnName = "id")
	private LinhaProducao linha;

	public Caixa() {
	}

	public Caixa(String uid, double base_tamanho, double altura, Vector3d posicao, boolean fragil) {
		super();
		this.UID = uid.replace(' ', '_');
		this.tamanho = new Vector3d(base_tamanho, altura, base_tamanho);
		this.posicao = posicao;
		this.fragil = fragil;

	}

	public Caixa(String uid, double base_tamanho, double altura, Vector3d posicao, boolean fragil, String cardId) {
		this(uid,base_tamanho,altura,posicao,fragil);
		this.cardId = cardId;

	}

	

	public String getUID() {
		return UID;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
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

	public int getQuantCartas() {
		return (int)(getDimensoes().length() / Application.CARD_SIZE);
	}

	public LinhaProducao getLinha() {
		return linha;
	}

	public void setLinha(LinhaProducao linha) {
		this.linha = linha;
	}

	@Override
	public String toString() {
		return "\nUID:" + getUID();
	}
}
