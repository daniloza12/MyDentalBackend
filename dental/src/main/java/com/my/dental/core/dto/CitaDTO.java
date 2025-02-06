package com.my.dental.core.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CitaDTO {

	private Long id;
	private Long idCliente;
	private Long idMedico;
	private Long idParametroSede;
	private String motivo;
	private String estado;
	private String antecedentes;
	private String medicinaActual;
	private String observaciones;
	private String fechaCita;
	private LocalDateTime fechaRegistro;
	private String userRegister;

}


