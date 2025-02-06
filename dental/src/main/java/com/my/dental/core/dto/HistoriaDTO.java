package com.my.dental.core.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class HistoriaDTO {

	private Long id;
//	private Long idCita;
	private Long idCliente;
	private Long idMedico;
	private String tratamiento;
//	private BigDecimal total;
//	private BigDecimal cuenta;
//	private BigDecimal saldo;
	private LocalDateTime fechaRegistro;
	private String userRegister;

}


