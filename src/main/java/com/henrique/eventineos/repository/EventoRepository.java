package com.henrique.eventineos.repository;

import com.henrique.eventineos.model.Evento;
import org.springframework.data.repository.CrudRepository;

public interface EventoRepository extends CrudRepository<Evento, String> {
    Evento findById(long codigo);
}
