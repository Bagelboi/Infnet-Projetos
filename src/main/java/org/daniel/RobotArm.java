package org.daniel;

import javax.vecmath.Vector3d;

public class RobotArm {
    private final int id;
    private Vector3d posicao;
    private double angulo;
    private boolean estado_garra;

    public RobotArm(int id, Vector3d posicao, double angulo, boolean estado_garra) {
        this.id = id;
        this.posicao = posicao;
        this.angulo = angulo;
        this.estado_garra = estado_garra;
    }

    // encapsulação

    public int getId() {
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

    // metodos

    public String status() {
        return "\nBraço robótico\nID: " + id + "\nPosição: " + posicao + "\nAngulo: " + angulo +
                "\nGarra: " + (estado_garra ? "Aberta" : "Fechada");
    }

    public boolean verificarManutencao() {
        // verificar se precisa de manutenção
        return angulo > 90.0;
    }

    public double obterInclinacao() {
        return angulo / 10.0;
    }

    public String verificarEstado() {
        String estado = "Normal";

        if (angulo > 120.0) {
            estado = "Angulo elevado demais!";
        }

        return estado;
    }


}
