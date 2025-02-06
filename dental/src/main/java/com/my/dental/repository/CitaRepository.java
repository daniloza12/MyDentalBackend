package com.my.dental.repository;

import com.my.dental.core.entity.CitaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CitaRepository extends JpaRepository<CitaEntity, Long> {

}
