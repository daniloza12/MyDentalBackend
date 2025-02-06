package com.my.dental.core.mapper;

import com.my.dental.core.dto.MedicoDTO;
import com.my.dental.core.entity.MedicoEntity;

import java.util.List;


public interface MedicoMapper {

	MedicoDTO toDTO(MedicoEntity e);

	MedicoEntity toEntity(MedicoDTO d);
	
	List<MedicoDTO> toLstDTO(List<MedicoEntity> lstE);

}
