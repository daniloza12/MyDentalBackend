package com.my.dental.core.mapper;

import com.my.dental.core.dto.HistoriaDTO;
import com.my.dental.core.entity.HistoriaEntity;

import java.util.List;


public interface HistoriaMapper {

	HistoriaDTO toDTO(HistoriaEntity e);

	HistoriaEntity toEntity(HistoriaDTO d);

	List<HistoriaDTO> toLstDTO(List<HistoriaEntity> lstE);

}
