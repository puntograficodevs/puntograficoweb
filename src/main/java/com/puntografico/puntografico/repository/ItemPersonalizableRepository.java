package com.puntografico.puntografico.repository;

import com.puntografico.puntografico.domain.ItemPersonalizable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPersonalizableRepository extends JpaRepository<ItemPersonalizable, Long> {
}
