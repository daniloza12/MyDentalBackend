package com.my.dental.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name ="ClienteEntity" )
@Table(name =  "cliente")
public class ClienteEntity {

	@Id
	@Column(name = "id_cliente")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	  
	@Column(name = "tipo_documento")
	private String tipoDocumento;

	@Column(name = "numero_documento")
	private String numeroDocumento;
	
	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "correo")
	private String correo;
	
	@Column(name = "estado")
	private Integer estado;
	
	@Column(name = "nombres")
	private String nombres;
	
	@Column(name = "apellidos")
	private String apellidos;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;
	
	@Column(name = "sexo")
	private String sexo;
			
	@Column(name = "pais")
	private String pais;
	
	@Column(name = "departamento")
	private String departamento;
	
	@Column(name = "provincia")
	private String provincia;
	
	@Column(name = "distrito")
	private String distrito;

	@Column(name = "celular")
	private String celular;

	@Column(name = "ocupacion")
	private String ocupacion;

	@Column(name = "fecha_registro")
	private LocalDateTime fechaRegistro;

	@Column(name = "user_register")
	private String userRegister;

	@OneToMany(mappedBy = "cliente")
	private List<CitaEntity> citas;

	@OneToMany(mappedBy = "cliente")
	private List<HistoriaEntity> historia;

}


