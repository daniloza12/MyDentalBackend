package com.my.dental.service;

import com.my.dental.core.dto.MedicoDTO;
import com.my.dental.core.entity.ClienteEntity;
import com.my.dental.core.entity.MedicoEntity;
import com.my.dental.core.mapper.MedicoMapper;
import com.my.dental.exception.MyDentalException;
import com.my.dental.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class MedicoServiceImpl implements MedicoService {

	private final MedicoRepository medicoRepository;
	private final MedicoMapper medicoMapper;

    @Override
	public Optional<MedicoDTO> findById(Long id) throws MyDentalException {
		String msg = String.format("No existe Medico con id => %s", id);
		MedicoEntity medicoEntity = this.medicoRepository.findById(id).orElseThrow(() -> new MyDentalException(msg));
		MedicoDTO medicoDTO = medicoMapper.toDTO(medicoEntity);
		return Optional.ofNullable(medicoDTO);
	}

	@Override
	public List<MedicoDTO> findAll() {
		return medicoMapper.toLstDTO(medicoRepository.findAll());
	}

	@Override
	public Long save(MedicoDTO medicoDTO) throws MyDentalException {
		MedicoEntity medicoEntity = medicoMapper.toEntity(medicoDTO);
		medicoEntity.setEstado(1);
 		medicoEntity.setFechaRegistro(LocalDateTime.now());
		MedicoEntity medicoEntitySave = medicoRepository.save(medicoEntity);
		if (isNull(medicoEntitySave)) {
			throw new MyDentalException("Error al resgitrar medico");
		}
		return medicoEntitySave.getId();
	}

	@Override
	public void modificar(MedicoDTO medicoDTO) throws MyDentalException {
		Optional<MedicoEntity> medicoBd = medicoRepository.findById(medicoDTO.getId());
		if(medicoBd.isEmpty()){
			throw new MyDentalException("El Medico Indicada No Existe");
		}
		MedicoEntity medicoEntityBd = medicoBd.get();
		MedicoEntity medicoEntity = medicoMapper.toEntity(medicoDTO);
		medicoEntity.setFechaRegistro(medicoEntityBd.getFechaRegistro());
		medicoEntity.setUserRegister(medicoEntityBd.getUserRegister());
		medicoRepository.save(medicoEntity);
	}

	@Override
	public void delete(Long id) throws MyDentalException {
		String msg = String.format("No existe medico con id => %s", id);
		this.medicoRepository.findById(id).orElseThrow(() -> new MyDentalException(msg));
		medicoRepository.deleteById(id);
	}
}
