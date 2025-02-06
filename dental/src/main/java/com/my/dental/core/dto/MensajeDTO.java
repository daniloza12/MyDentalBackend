package com.my.dental.core.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MensajeDTO {

 	private Long idCita;
 	private String paciente;
 	private String correo;
 	private String lugar;
 	private String fecha;
 	private String hora;
 	private String medico;
 	private String tratamiento;

 }
