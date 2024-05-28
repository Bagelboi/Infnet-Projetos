package org.daniel.suspediatria2.entidades.cadastro;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pediatra extends PessoaFisica {
    @ManyToOne
    @JoinColumn(name = "local_trab_id")
    private UBS local_trab;

    public Pediatra() {}

    public UBS getLocal_trab() {
        return local_trab;
    }

    public void setLocal_trab(UBS local_trab) {
        this.local_trab = local_trab;
    }
}
