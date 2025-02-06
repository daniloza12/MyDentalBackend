package com.my.dental.repository;

import com.my.dental.core.entity.HistoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HistoriaRepository extends JpaRepository<HistoriaEntity, Long> {

}
