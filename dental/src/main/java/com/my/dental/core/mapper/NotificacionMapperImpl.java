package com.my.dental.core.mapper;

import com.my.dental.core.dto.NotificacionDTO;
import com.my.dental.core.entity.CitaEntity;
import com.my.dental.core.entity.NotificacionEntity;
import com.my.dental.core.entity.ClienteEntity;
import com.my.dental.core.entity.MedicoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificacionMapperImpl implements NotificacionMapper {

	public final ModelMapper modelMapper;

	public NotificacionMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public NotificacionDTO toDTO(NotificacionEntity e) {
		NotificacionDTO dto = modelMapper.map(e, NotificacionDTO.class);
		dto.setIdCita(e.getCita().getId());
		return dto;
	}

	@Override
	public List<NotificacionDTO> toLstDTO(List<NotificacionEntity> lstE) {
		return lstE.stream().map(e-> this.toDTO(e)).toList();
	}

	@Override
	public NotificacionEntity toEntity(NotificacionDTO d) {
		NotificacionEntity entity = modelMapper.map(d, NotificacionEntity.class);
		entity.setCita(new CitaEntity());
		entity.getCita().setId(d.getIdCita());
		return entity;
	}

}
