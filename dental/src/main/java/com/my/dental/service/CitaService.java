package com.my.dental.service;

import com.my.dental.core.dto.CitaDTO;
import com.my.dental.exception.MyDentalException;

import java.util.List;
import java.util.Optional;


public interface CitaService {

	Optional<CitaDTO> findById(Long id) throws MyDentalException;

	List<CitaDTO> findAll();

	Long save(CitaDTO citaDTO)throws MyDentalException;

	void modifyCita(CitaDTO citaDTO)throws MyDentalException;

	void delete(Long id) throws MyDentalException;

}
