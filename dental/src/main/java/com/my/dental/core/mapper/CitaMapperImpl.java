package com.my.dental.core.mapper;

import com.my.dental.core.dto.CitaDTO;
import com.my.dental.core.entity.CitaEntity;
import com.my.dental.core.entity.ClienteEntity;
import com.my.dental.core.entity.MedicoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CitaMapperImpl implements CitaMapper {

	public final ModelMapper modelMapper;

	public CitaMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public CitaDTO toDTO(CitaEntity e) {
		CitaDTO dto = modelMapper.map(e, CitaDTO.class);
		dto.setIdCliente(e.getCliente().getId());
		dto.setIdMedico(e.getMedico().getId());
		return dto;
	}

	@Override
	public List<CitaDTO> toLstDTO(List<CitaEntity> lstE) {
		return lstE.stream().map(e-> this.toDTO(e)).toList();
	}

	@Override
	public CitaEntity toEntity(CitaDTO d) {
		CitaEntity entity = modelMapper.map(d, CitaEntity.class);
		entity.setCliente(new ClienteEntity());
		entity.getCliente().setId(d.getIdCliente());
		entity.setMedico(new MedicoEntity());
		entity.getMedico().setId(d.getIdMedico());
		return entity;
	}

}
