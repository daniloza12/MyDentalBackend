package com.my.dental.core.mapper;

import com.my.dental.core.dto.MedicoDTO;
import com.my.dental.core.entity.MedicoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicoMapperImpl implements MedicoMapper {

	public final ModelMapper modelMapper;

     public MedicoMapperImpl(ModelMapper modelMapper) {
         this.modelMapper = modelMapper;
     }

	 @Override
	 public MedicoDTO toDTO(MedicoEntity e) {
		return modelMapper.map(e, MedicoDTO.class);
	}

	@Override
	public List<MedicoDTO> toLstDTO(List<MedicoEntity> lstE) {
		
		return lstE.stream().map(e-> this.toDTO(e)).toList();
	}

	@Override
	public MedicoEntity toEntity(MedicoDTO d) {
		return modelMapper.map(d, MedicoEntity.class);
	}

}
