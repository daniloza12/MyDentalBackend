package com.my.dental.core.mapper;

import com.my.dental.core.dto.CitaDTO;
import com.my.dental.core.entity.CitaEntity;

import java.util.List;


public interface CitaMapper {

	CitaDTO toDTO(CitaEntity e);

	CitaEntity toEntity(CitaDTO d);

	List<CitaDTO> toLstDTO(List<CitaEntity> lstE);

}
