package com.my.dental.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@Entity(name ="CitaEntity" )
@Table(name =  "cita")
@NoArgsConstructor
@AllArgsConstructor
public class CitaEntity {

	@Id
	@Column(name = "id_cita")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false)
	private ClienteEntity cliente;

	@ManyToOne
	@JoinColumn(name = "id_medico", nullable = false)
	private MedicoEntity medico;

	@Column(name = "motivo")
	private String motivo;

	@Column(name = "estado")
	private String estado;

	@Column(name = "antecedentes")
	private String antecedentes;

	@Column(name = "medicina_actual")
	private String medicinaActual;

	@Column(name = "observaciones")
	private String observaciones;



	@Column(name = "fecha_cita")
	private LocalDateTime fechaCita;

	@Column(name = "fecha_registro")
	private LocalDateTime fechaRegistro;

	@Column(name = "id_parametro_sede")
	private Long idParametroSede;


	@Column(name = "user_register")
	private String userRegister;

	@OneToMany(mappedBy = "cita")
	private List<NotificacionEntity> notificacions;

}


