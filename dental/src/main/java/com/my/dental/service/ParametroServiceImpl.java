package com.my.dental.service;

import com.my.dental.core.dto.ParametroDTO;
import com.my.dental.core.entity.ParametroEntity;
import com.my.dental.core.mapper.ParametroMapper;
import com.my.dental.repository.ParametroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ParametroServiceImpl implements ParametroService {

	private final ParametroRepository parametroRepository;
	private final ParametroMapper parametroMapper;


	@Override
	public List<ParametroDTO> findByTipo(String tipo) {
		List<ParametroDTO> lstDto = new ArrayList<>();
		Optional<List<ParametroEntity>> lstParametroEntity = parametroRepository.findByTipo(tipo);
		if (!lstParametroEntity.isEmpty()){
			 List<ParametroEntity> lstBd = lstParametroEntity.get();
			 lstDto = lstBd.stream()
							.map(parametro -> parametroMapper.toDTO(parametro))
							.collect(Collectors.toList());
		}
		return lstDto;
	}

	@Override
	public List<ParametroDTO> findByTipoIn(List<String> lstTipos) {
		List<ParametroDTO> lstDto = new ArrayList<>();
		Optional<List<ParametroEntity>> lstParametroEntity = parametroRepository.findByTipoIn(lstTipos);
		if (!lstParametroEntity.isEmpty()){
			List<ParametroEntity> lstBd = lstParametroEntity.get();
			lstDto = lstBd.stream()
					.map(parametro -> parametroMapper.toDTO(parametro))
					.collect(Collectors.toList());
		}
		return lstDto;
	}

}
