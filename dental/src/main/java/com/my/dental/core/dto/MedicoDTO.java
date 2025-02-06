package com.my.dental.core.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MedicoDTO {

	private Long id;
	private String nombres;
	private String apellidos;
	private String telefono;
	private String celular;
	private String correo;
	private Integer estado;
	private LocalDateTime fechaRegistro;
	private String userRegister;
}


