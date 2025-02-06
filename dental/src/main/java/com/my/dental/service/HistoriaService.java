package com.my.dental.service;

import com.my.dental.core.dto.HistoriaDTO;
import com.my.dental.exception.MyDentalException;

import java.util.List;
import java.util.Optional;


public interface HistoriaService {

	Optional<HistoriaDTO> findById(Long id) throws MyDentalException;

	List<HistoriaDTO> findAll();

	Long save(HistoriaDTO historiaDTO)throws MyDentalException;

	void modificar(HistoriaDTO historiaDTO)throws MyDentalException;

	void delete(Long id) throws MyDentalException;

}
