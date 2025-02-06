package com.my.dental.app.controller;

import com.my.dental.core.dto.NotificacionDTO;
import com.my.dental.exception.MyDentalException;
import com.my.dental.service.NotificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/notificacion")
public class NotificacionController {

	private final NotificacionService notificacionService;

	@GetMapping
	public ResponseEntity<List<NotificacionDTO>> findAll() {

		try {
			List<NotificacionDTO> lstNotificacionDTO = notificacionService.findAll();
			if (isNull(lstNotificacionDTO) || lstNotificacionDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstNotificacionDTO);
		} catch (Exception e) {
			if(e instanceof MyDentalException)
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable(value = "id", required = true) Long id) {
		try {
			Optional<NotificacionDTO> optNotificacionDTO = notificacionService.findById(id);

			if (isNull(optNotificacionDTO) || optNotificacionDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(optNotificacionDTO.get());
		} catch (Exception e) {
			if(e instanceof MyDentalException)
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody NotificacionDTO notificacionDTO) {
		try {
			Long id = notificacionService.save(notificacionDTO);
			if (isNull(id)) {
				return ResponseEntity.badRequest().build();
			}
			Map<String, Object> resp = new HashMap<>();
			resp.put("message", "La notificacion fue registrado con exito");
			resp.put("ide", id);
			return ResponseEntity.ok(resp);
		} catch (Exception e) {
			if(e instanceof MyDentalException)
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

			return ResponseEntity.internalServerError().build();
		}
	}

//	@PutMapping
//	public ResponseEntity<?> modificar(@RequestBody NotificacionDTO notificacionDTO) {
//		try {
//			notificacionService.modifyNotificacion(notificacionDTO);
//			Map<String, Object> resp = new HashMap<>();
//			resp.put("message", "La notificacion fue modificada con exito");
//			resp.put("ide", notificacionDTO.getId());
//			return ResponseEntity.ok(resp);
//		} catch (Exception e) {
//			if(e instanceof MyDentalException)
//				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//
//			return ResponseEntity.internalServerError().build();
//		}
//	}


//	@DeleteMapping("/{id}")
//	public ResponseEntity<?> delete(@PathVariable(value = "id", required = true) Long id) {
//		try {
//			notificacionService.delete(id);
//			Map<String, Object> resp = new HashMap<>();
//			resp.put("message", "La Notificacion se elimino con exito");
//			resp.put("ide", id);
//			return ResponseEntity.ok(resp);
//		} catch (Exception e) {
//			if(e instanceof MyDentalException)
//				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//
//			return ResponseEntity.internalServerError().build();
//		}
//	}

}
