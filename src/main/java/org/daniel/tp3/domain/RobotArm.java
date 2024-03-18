package org.daniel.tp3.domain;



import jakarta.persistence.*;

import javax.vecmath.Vector3d;
import java.awt.*;

@Entity
public class RobotArm {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Vector3d posicao;
    private double angulo;
    private boolean estado_garra;
    private double tamanho_m; //tamanho do braço em metros

    @ManyToOne
    @JoinColumn(name = "linha_producao_id", referencedColumnName = "id")
    private LinhaProducao linha;


    public RobotArm() {
    }

    public RobotArm(Integer id, Vector3d posicao, double angulo, boolean estado_garra, double tamanho_m) {
        this.id = id;
        mover(posicao);
        setAngulo(angulo);
        this.estado_garra = estado_garra;
        this.tamanho_m = tamanho_m;
    }
    
    // encapsulação

    public LinhaProducao getLinha() {
        return linha;
    }

    public void setLinha(LinhaProducao linha) {
        this.linha = linha;
    }
    public Integer getId() {
        return id;
    }

    public Vector3d getPosicao() {
        return posicao;
    }

    public double getAngulo() {
        return angulo;
    }

    public boolean getEstadoGarra() {
        return estado_garra;
    }

    public void setAngulo(double novo_angulo) {
        this.angulo = novo_angulo;
    }

    public void abrirGarra() {
        this.estado_garra = true;
    }

    public void fecharGarra() {
        this.estado_garra = false;
    }

    public void mover(Vector3d nova_posicao) {
        this.posicao = nova_posicao;
    }
    
    public double getTamanho() {
    	return this.tamanho_m;
    }


    // metodos


    public boolean verificarManutencao() {
        // verificar se precisa de manutenção
        return angulo > 90.0;
    }

    public double obterInclinacao() {
        return angulo / 10.0f;
    }

    public String verificarEstado() {
        String estado = "Normal";

        if (angulo > 120.0) {
            estado = "Angulo elevado demais!";
        }

        return estado;
    }
    
    public double obterAlcance() {
    	return Math.cos(angulo) * tamanho_m + obterInclinacao();
    }

    @Override
    public String toString() {
        return "\nBraço robótico\nID: " + id + "\nPosição: " + posicao + "\nAngulo: " + angulo +
                "\nGarra: " + (estado_garra ? "Aberta" : "Fechada");
    }
}
