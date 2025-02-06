package com.my.dental.core.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificacionDTO {

	private Long id;
	private Long idCita;
	private Long idParametro;
	private LocalDateTime fechaEnvio;

}


