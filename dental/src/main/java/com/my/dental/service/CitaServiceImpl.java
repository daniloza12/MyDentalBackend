package com.my.dental.service;

import com.my.dental.core.dto.CitaDTO;
import com.my.dental.core.dto.MensajeDTO;
import com.my.dental.core.entity.CitaEntity;
import com.my.dental.core.mapper.CitaMapper;
import com.my.dental.exception.MyDentalException;
import com.my.dental.repository.CitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class CitaServiceImpl implements CitaService {

	private final CitaRepository citaRepository;

	private final MessageService messageService;
	private final CitaMapper citaMapper;
	private final Executor apiExecutor;

	@Override
	public Optional<CitaDTO> findById(Long id) throws MyDentalException {
		String msg = String.format("No existe cita con id => %s", id);
		CitaEntity citaEntity = this.citaRepository.findById(id).orElseThrow(() -> new MyDentalException(msg));
		CitaDTO citaDTO = citaMapper.toDTO(citaEntity);
		return Optional.ofNullable(citaDTO);
	}

	@Override
	public List<CitaDTO> findAll() {
		return citaMapper.toLstDTO(citaRepository.findAll());
	}

	@Override
	public Long save(CitaDTO citaDTO) throws MyDentalException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime fechaHora = LocalDateTime.parse(citaDTO.getFechaCita(), formatter);
		CitaEntity cita = citaMapper.toEntity(citaDTO);
		cita.setFechaRegistro(LocalDateTime.now());
		cita.setFechaCita(fechaHora);
		CitaEntity citaSave = citaRepository.save(cita);
		if (isNull(citaSave)) {
			throw new MyDentalException("Error al resgitrar cita");
		}
		citaDTO.setId(citaSave.getId());
		CompletableFuture.runAsync(()-> notificarCita(citaDTO), apiExecutor);
		return citaSave.getId();
	}

	@Override
	public void modifyCita(CitaDTO citaDTO) throws MyDentalException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		Optional<CitaEntity> citaBd = citaRepository.findById(citaDTO.getId());
		if(citaBd.isEmpty()){
			throw new MyDentalException("La Cita Indicada No Existe");
		}
		CitaEntity citaEntityBd = citaBd.get();
		LocalDateTime fechaHora = LocalDateTime.parse(citaDTO.getFechaCita(), formatter);
		CitaEntity cita = citaMapper.toEntity(citaDTO);
		cita.setFechaCita(fechaHora);
		cita.setFechaRegistro(citaEntityBd.getFechaRegistro());
		cita.setUserRegister(citaEntityBd.getUserRegister());
		citaRepository.save(cita);
	}

	@Override
	public void delete(Long id) throws MyDentalException {
		String msg = String.format("No existe cita con id => %s", id);
		this.citaRepository.findById(id).orElseThrow(() -> new MyDentalException(msg));
		citaRepository.deleteById(id);
	}

	public void notificarCita(CitaDTO citaDTO) {
		List<MensajeDTO> listCitas = new ArrayList<>();
		listCitas.add(MensajeDTO.builder().idCita(citaDTO.getId()).build());
		messageService.envioCorreo(listCitas);
	}

}
