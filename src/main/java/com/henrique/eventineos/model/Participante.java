package com.henrique.eventineos.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class Participante {
    @Id
    @NotEmpty
    private String rg;

    @NotEmpty
    private String nomeParticipante;

    @ManyToOne
    private Evento evento;

    public String getNomeParticipante() {
        return nomeParticipante;
    }

    public void setNomeParticipante(String nomeParticipante) {
        this.nomeParticipante = nomeParticipante;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
}
