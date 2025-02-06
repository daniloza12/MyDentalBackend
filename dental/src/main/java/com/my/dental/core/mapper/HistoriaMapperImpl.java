package com.my.dental.core.mapper;

import com.my.dental.core.dto.HistoriaDTO;
import com.my.dental.core.entity.ClienteEntity;
import com.my.dental.core.entity.HistoriaEntity;
import com.my.dental.core.entity.MedicoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistoriaMapperImpl implements HistoriaMapper {

	public final ModelMapper modelMapper;

	public HistoriaMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public HistoriaDTO toDTO(HistoriaEntity e) {
		HistoriaDTO dto = modelMapper.map(e, HistoriaDTO.class);
		dto.setIdCliente(e.getCliente().getId());
		dto.setIdMedico(e.getMedico().getId());
		return dto;
	}

	@Override
	public List<HistoriaDTO> toLstDTO(List<HistoriaEntity> lstE) {
		return lstE.stream().map(e-> this.toDTO(e)).toList();
	}

	@Override
	public HistoriaEntity toEntity(HistoriaDTO d) {
		HistoriaEntity entity = modelMapper.map(d, HistoriaEntity.class);
		entity.setCliente(new ClienteEntity());
		entity.getCliente().setId(d.getIdCliente());
		entity.setMedico(new MedicoEntity());
		entity.getMedico().setId(d.getIdMedico());
		return entity;
	}

}
