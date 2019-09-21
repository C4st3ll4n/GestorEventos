package com.henrique.eventineos.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Participante {
    @Id
    private String RG;

    private String nomeParticipante;

    @ManyToOne
    private Evento evento;

}
