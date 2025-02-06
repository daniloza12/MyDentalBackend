package com.my.dental.service;

import com.my.dental.core.dto.HistoriaDTO;
import com.my.dental.core.entity.HistoriaEntity;
import com.my.dental.core.entity.MedicoEntity;
import com.my.dental.core.mapper.HistoriaMapper;
import com.my.dental.exception.MyDentalException;
import com.my.dental.repository.CitaRepository;
import com.my.dental.repository.HistoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class HistoriaServiceImpl implements HistoriaService {

	private final HistoriaRepository historiaRepository;
 	private final CitaRepository citaRepository;
	private final HistoriaMapper historiaMapper;

	@Override
	public Optional<HistoriaDTO> findById(Long id) throws MyDentalException {
		String msg = String.format("No existe historia con id => %s", id);
		HistoriaEntity historiaEntity = this.historiaRepository.findById(id).orElseThrow(() -> new MyDentalException(msg));
		HistoriaDTO historiaDTO = historiaMapper.toDTO(historiaEntity);
		return Optional.ofNullable(historiaDTO);
	}

	@Override
	public List<HistoriaDTO> findAll() {
		return historiaMapper.toLstDTO(historiaRepository.findAll());
	}

	@Override
	public Long save(HistoriaDTO historiaDTO) throws MyDentalException {
		HistoriaEntity historia = historiaMapper.toEntity(historiaDTO);
		historia.setFechaRegistro(LocalDateTime.now());
		HistoriaEntity historiaEntity = historiaRepository.save(historia);
		if (isNull(historiaEntity)) {
			throw new MyDentalException("Error al resgitrar historia");
		}
		return historiaEntity.getId();
	}

	@Override
	public void modificar(HistoriaDTO historiaDTO) throws MyDentalException {
		Optional<HistoriaEntity> historiaDb = historiaRepository.findById(historiaDTO.getId());
		if(historiaDb.isEmpty()){
			throw new MyDentalException("La Historia Indicada No Existe");
		}
		HistoriaEntity historiaEntityBd = historiaDb.get();
		HistoriaEntity historia = historiaMapper.toEntity(historiaDTO);
		historia.setFechaRegistro(historiaEntityBd.getFechaRegistro());
		historia.setUserRegister(historiaEntityBd.getUserRegister());
		historiaRepository.save(historia);
	}

	@Override
	public void delete(Long id) throws MyDentalException {
		String msg = String.format("No existe historia con id => %s", id);
		this.historiaRepository.findById(id).orElseThrow(() -> new MyDentalException(msg));
		historiaRepository.deleteById(id);

	}
}
