package com.my.dental.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name ="ParametroEntity" )
@Table(name =  "parametro")
public class ParametroEntity {

	@Id
	@Column(name = "id_parametro")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tipo")
	private String tipo;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "formato")
	private String formato;


	@Column(name = "fecha_registro")
	private LocalDateTime fechaRegistro;

	@Column(name = "user_register")
	private String userRegister;

}


