package com.my.dental.core.mapper;

import java.util.List;

import com.my.dental.core.dto.ClienteDTO;
import com.my.dental.core.entity.ClienteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapperImpl implements ClienteMapper {
	
	public final ModelMapper modelMapper;

     public ClienteMapperImpl(ModelMapper modelMapper) {
         this.modelMapper = modelMapper;
     }

     @Override
	public ClienteDTO toDTO(ClienteEntity e) {
		return modelMapper.map(e, ClienteDTO.class);
	}

	@Override
	public List<ClienteDTO> toLstDTO(List<ClienteEntity> lstE) {
		
		return lstE.stream().map(e-> this.toDTO(e)).toList();
	}

	@Override
	public ClienteEntity toEntity(ClienteDTO d) {
		return modelMapper.map(d, ClienteEntity.class);
	}

}
