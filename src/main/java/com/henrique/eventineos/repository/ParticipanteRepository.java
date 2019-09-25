package com.henrique.eventineos.repository;

import com.henrique.eventineos.model.Evento;
import com.henrique.eventineos.model.Participante;
import org.springframework.data.repository.CrudRepository;

import java.awt.*;

public interface ParticipanteRepository extends CrudRepository<Participante, String> {
    Iterable<Participante> findByEvento(Evento evento);
}
