package com.my.dental.core.mapper;

import com.my.dental.core.dto.ParametroDTO;
import com.my.dental.core.entity.ParametroEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class ParametroMapperImpl implements ParametroMapper {

	public final ModelMapper modelMapper;

	public ParametroMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public ParametroDTO toDTO(ParametroEntity e) {
		ParametroDTO dto = modelMapper.map(e, ParametroDTO.class);
		return dto;
	}

}
