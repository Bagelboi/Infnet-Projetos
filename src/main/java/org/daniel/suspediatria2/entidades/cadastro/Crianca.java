package org.daniel.suspediatria2.entidades.cadastro;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Calendar;

@Entity
public class Crianca extends PessoaFisica {
    @ManyToOne
    @JoinColumn(name = "pai_cpf")
    private Responsavel pai;
    @ManyToOne
    @JoinColumn(name = "mae_cpf")
    private Responsavel mae;

    @ManyToOne
    @JoinColumn(name = "local_atendimento_id")
    private UBS local_atendimento;

    public Crianca() {}

    public Responsavel getPai() {
        return pai;
    }

    public void setPai(Responsavel pai) {
        this.pai = pai;
    }

    public Responsavel getMae() {
        return mae;
    }

    public void setMae(Responsavel mae) {
        this.mae = mae;
    }

    public UBS getLocal_atendimento() {
        return local_atendimento;
    }

    public void setLocal_atendimento(UBS local_atendimento) {
        this.local_atendimento = local_atendimento;
    }
}
