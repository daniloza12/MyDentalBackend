package com.my.dental.core.mapper;

import com.my.dental.core.dto.ClienteDTO;
import com.my.dental.core.entity.ClienteEntity;

import java.util.List;


public interface ClienteMapper {

	ClienteDTO toDTO(ClienteEntity e);
	
	ClienteEntity toEntity(ClienteDTO d);
	
	List<ClienteDTO> toLstDTO(List<ClienteEntity> lstE);

}
