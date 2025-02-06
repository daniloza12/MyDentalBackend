package com.my.dental.app.controller;

import com.my.dental.core.dto.MensajeDTO;
import com.my.dental.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/notify")
public class NotifyController {

 	private final MessageService messageService;
	private final Executor apiExecutor;

	@PostMapping("/email")
	public void notifyEmail(@RequestBody List<MensajeDTO> listCitas) {
		try {
			CompletableFuture.runAsync(()-> messageService.envioCorreo(listCitas), apiExecutor);
		} catch (Exception e) {
			System.out.println("Error notificar notifyEmail" + e.getMessage());
		}
	}


}
