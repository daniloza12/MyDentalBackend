package com.my.dental.service;

import com.my.dental.core.dto.ParametroDTO;
import com.my.dental.exception.MyDentalException;

import java.util.List;


public interface ParametroService {


	List<ParametroDTO> findByTipo(String tipo) throws MyDentalException;

	List<ParametroDTO> findByTipoIn(List<String> lstTipos);

}
