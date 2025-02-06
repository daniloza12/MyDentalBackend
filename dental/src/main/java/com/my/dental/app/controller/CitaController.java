package com.my.dental.app.controller;

import com.my.dental.core.dto.CitaDTO;
import com.my.dental.exception.MyDentalException;
import com.my.dental.service.CitaService;
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
@RequestMapping("/v1/cita")
public class CitaController {

	private final CitaService citaService;

	@GetMapping
	public ResponseEntity<List<CitaDTO>> findAll() {

		try {
			List<CitaDTO> lstCitaDTO = citaService.findAll();
			if (isNull(lstCitaDTO) || lstCitaDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstCitaDTO);
		} catch (Exception e) {
			if(e instanceof MyDentalException)
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable(value = "id", required = true) Long id) {
		try {
			Optional<CitaDTO> optCitaDTO = citaService.findById(id);

			if (isNull(optCitaDTO) || optCitaDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(optCitaDTO.get());
		} catch (Exception e) {
			if(e instanceof MyDentalException)
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody CitaDTO citaDTO) {
		try {
			Long id = citaService.save(citaDTO);
			if (isNull(id)) {
				return ResponseEntity.badRequest().build();
			}
			Map<String, Object> resp = new HashMap<>();
			resp.put("message", "La cita fue registrado con exito");
			resp.put("ide", id);
			return ResponseEntity.ok(resp);
		} catch (Exception e) {
			if(e instanceof MyDentalException)
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping
	public ResponseEntity<?> modificar(@RequestBody CitaDTO citaDTO) {
		try {
			citaService.modifyCita(citaDTO);
			Map<String, Object> resp = new HashMap<>();
			resp.put("message", "La cita fue modificada con exito");
			resp.put("ide", citaDTO.getId());
			return ResponseEntity.ok(resp);
		} catch (Exception e) {
			if(e instanceof MyDentalException)
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

			return ResponseEntity.internalServerError().build();
		}
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id", required = true) Long id) {
		try {
			citaService.delete(id);
			Map<String, Object> resp = new HashMap<>();
			resp.put("message", "La Cita se elimino con exito");
			resp.put("ide", id);
			return ResponseEntity.ok(resp);
		} catch (Exception e) {
			if(e instanceof MyDentalException)
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

			return ResponseEntity.internalServerError().build();
		}
	}

}
