package com.my.dental.core.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name ="HistoriaEntity" )
@Table(name =  "historia")
public class HistoriaEntity {

	@Id
	@Column(name = "id_historia")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@ManyToOne
//	@JoinColumn(name = "id_cita", nullable = false)
//	private CitaEntity cita;

//	@ManyToOne
//	@JoinColumn(name = "id_cita", nullable = false)
//	private CitaEntity cita;

	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false)
	private ClienteEntity cliente;

	@ManyToOne
	@JoinColumn(name = "id_medico", nullable = false)
	private MedicoEntity medico;

	@Column(name = "tratamiento")
	private String tratamiento;

//	@Column(name = "total", precision = 10, scale = 2)
//	@Digits(integer = 8, fraction = 2)
//	private BigDecimal total;
//
//	@Column(name = "cuenta", precision = 10, scale = 2)
//	@Digits(integer = 8, fraction = 2)
//	private BigDecimal cuenta;
//
//	@Column(name = "saldo", precision = 10, scale = 2)
//	@Digits(integer = 8, fraction = 2)
//	private BigDecimal saldo;

	@Column(name = "fecha_registro")
	private LocalDateTime fechaRegistro;

	@Column(name = "user_register")
	private String userRegister;

}

