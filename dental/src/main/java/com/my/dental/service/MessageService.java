package com.my.dental.service;


import com.my.dental.core.dto.MensajeDTO;

import java.util.List;

public interface MessageService {
	
	void envioCorreo(List<MensajeDTO> lstMensajes);

}
