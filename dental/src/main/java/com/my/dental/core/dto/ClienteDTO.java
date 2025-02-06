package com.my.dental.core.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClienteDTO{

	private Long id;
	private String tipoDocumento;
	private String numeroDocumento;

	private String telefono;
	private String correo;
	private Integer estado;
	private String nombres;
	private String apellidos;
	private String direccion;
	private String fechaNacimiento;
	private String sexo;
	private String pais;
	private String departamento;
	private String provincia;
	private String distrito;
	private String celular;
	private String ocupacion;
	private LocalDateTime fechaRegistro;
	private String userRegister;
}


