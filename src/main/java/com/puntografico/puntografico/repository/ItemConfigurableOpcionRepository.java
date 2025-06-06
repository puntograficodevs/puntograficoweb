package com.puntografico.puntografico.repository;

import com.puntografico.puntografico.domain.ItemConfigurableOpcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemConfigurableOpcionRepository extends JpaRepository<ItemConfigurableOpcion, Long> {
}
