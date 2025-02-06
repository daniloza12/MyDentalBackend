package com.my.dental.service;

import com.my.dental.core.dto.ClienteDTO;
import com.my.dental.exception.MyDentalException;

import java.util.List;
import java.util.Optional;


public interface ClienteService {
	
	Optional<ClienteDTO> findById(Long id) throws MyDentalException;

	List<ClienteDTO> findAll();

	Long save(ClienteDTO clienteDTO)throws MyDentalException;

	void modificaCliente(ClienteDTO clienteDTO) throws MyDentalException;

	void delete(Long id) throws MyDentalException;

}
