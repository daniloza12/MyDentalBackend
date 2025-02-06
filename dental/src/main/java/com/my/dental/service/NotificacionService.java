package com.my.dental.service;

import com.my.dental.core.dto.NotificacionDTO;
import com.my.dental.exception.MyDentalException;

import java.util.List;
import java.util.Optional;


public interface NotificacionService {

	Optional<NotificacionDTO> findById(Long id) throws MyDentalException;

	List<NotificacionDTO> findAll();

	Long save(NotificacionDTO notificacionDTO)throws MyDentalException;

//	void modifyNotificacion(NotificacionDTO notificacionDTO)throws MyDentalException;

//	void delete(Long id) throws MyDentalException;

}
