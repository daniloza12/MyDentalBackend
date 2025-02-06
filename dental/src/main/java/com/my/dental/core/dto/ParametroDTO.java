package com.my.dental.core.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParametroDTO {

	private Long id;
	private String tipo;
	private String descripcion;
	private String formato;
	private LocalDateTime fechaRegistro;
	private String userRegister;
}


