package com.my.dental.repository;

import com.my.dental.core.entity.NotificacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificacionRepository extends JpaRepository<NotificacionEntity, Long> {

}
