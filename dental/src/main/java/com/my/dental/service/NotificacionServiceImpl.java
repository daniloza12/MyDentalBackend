package com.my.dental.service;

import com.my.dental.core.dto.NotificacionDTO;
import com.my.dental.core.entity.NotificacionEntity;
import com.my.dental.core.mapper.NotificacionMapper;
import com.my.dental.exception.MyDentalException;
import com.my.dental.repository.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class NotificacionServiceImpl implements NotificacionService {

	private final NotificacionRepository notificacionRepository;
	private final NotificacionMapper notificacionMapper;

	@Override
	public Optional<NotificacionDTO> findById(Long id) throws MyDentalException {
		String msg = String.format("No existe notificacion con id => %s", id);
		NotificacionEntity notificacionEntity = this.notificacionRepository.findById(id).orElseThrow(() -> new MyDentalException(msg));
		NotificacionDTO notificacionDTO = notificacionMapper.toDTO(notificacionEntity);
		return Optional.ofNullable(notificacionDTO);
	}

	@Override
	public List<NotificacionDTO> findAll() {
		return notificacionMapper.toLstDTO(notificacionRepository.findAll());
	}

	@Override
	public Long save(NotificacionDTO notificacionDTO) throws MyDentalException {
		NotificacionEntity notificacion = notificacionMapper.toEntity(notificacionDTO);
		notificacion.setFechaEnvio(LocalDateTime.now());
		NotificacionEntity notificacionSave = notificacionRepository.save(notificacion);
		if (isNull(notificacionSave)) {
			throw new MyDentalException("Error al resgitrar notificacion");
		}
		return notificacionSave.getId();
	}

//	@Override
//	public void modifyNotificacion(NotificacionDTO notificacionDTO) throws MyDentalException {
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
//		Optional<NotificacionEntity> notificacionBd = notificacionRepository.findById(notificacionDTO.getId());
//		if(notificacionBd.isEmpty()){
//			throw new MyDentalException("La Notificacion Indicada No Existe");
//		}
//		NotificacionEntity notificacionEntityBd = notificacionBd.get();
//		NotificacionEntity notificacion = notificacionMapper.toEntity(notificacionDTO);
//		notificacion.setFechaRegistro(notificacionEntityBd.getFechaRegistro());
//		notificacionRepository.save(notificacion);
//	}
//
//	@Override
//	public void delete(Long id) throws MyDentalException {
//		String msg = String.format("No existe notificacion con id => %s", id);
//		this.notificacionRepository.findById(id).orElseThrow(() -> new MyDentalException(msg));
//		notificacionRepository.deleteById(id);
//
//	}
}
