package com.my.dental.core.mapper;

import com.my.dental.core.dto.NotificacionDTO;
import com.my.dental.core.entity.NotificacionEntity;

import java.util.List;


public interface NotificacionMapper {

	NotificacionDTO toDTO(NotificacionEntity e);

	NotificacionEntity toEntity(NotificacionDTO d);

	List<NotificacionDTO> toLstDTO(List<NotificacionEntity> lstE);

}
