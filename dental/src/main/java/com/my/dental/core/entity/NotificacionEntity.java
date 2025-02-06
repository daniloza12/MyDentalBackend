package com.my.dental.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@Entity(name ="Notificacion" )
@Table(name =  "notificacion")
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionEntity {

	@Id
	@Column(name = "id_notificacion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_cita", nullable = false)
	private CitaEntity cita;

	@Column(name = "id_parametro")
	private Long idParametro;

	@Column(name = "fecha_envio")
	private LocalDateTime fechaEnvio;

}


