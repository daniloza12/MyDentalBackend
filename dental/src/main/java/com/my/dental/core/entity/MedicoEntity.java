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
@NoArgsConstructor
@AllArgsConstructor
@Entity(name ="MedicoEntity" )
@Table(name =  "medico")
public class MedicoEntity {

	@Id
	@Column(name = "id_medico")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombres")
	private String nombres;

	@Column(name = "apellidos")
	private String apellidos;

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "celular")
	private String celular;
	
	@Column(name = "correo")
	private String correo;
	
	@Column(name = "estado")
	private Integer estado;

	@Column(name = "fecha_registro")
	private LocalDateTime fechaRegistro;

	@Column(name = "user_register")
	private String userRegister;

	@OneToMany(mappedBy = "medico")
	private List<CitaEntity> citas;

	@OneToMany(mappedBy = "medico")
	private List<HistoriaEntity> historia;

}


