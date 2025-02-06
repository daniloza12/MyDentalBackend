package com.my.dental.service;

import com.my.dental.core.dto.MedicoDTO;
import com.my.dental.exception.MyDentalException;

import java.util.List;
import java.util.Optional;


public interface MedicoService {
	
	Optional<MedicoDTO> findById(Long id) throws MyDentalException;

	List<MedicoDTO> findAll();

	Long save(MedicoDTO medicoDTO) throws MyDentalException;

	void modificar(MedicoDTO medicoDTO) throws MyDentalException;

	void delete(Long id) throws MyDentalException;

}
