package com.my.dental.repository;

import com.my.dental.core.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

}
