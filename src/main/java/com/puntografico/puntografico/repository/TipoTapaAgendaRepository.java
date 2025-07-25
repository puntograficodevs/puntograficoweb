package com.puntografico.puntografico.repository;

import com.puntografico.puntografico.domain.TipoTapaAgenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoTapaAgendaRepository extends JpaRepository<TipoTapaAgenda, Long> {
}
