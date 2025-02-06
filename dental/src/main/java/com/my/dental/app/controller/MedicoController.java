package com.my.dental.app.controller;

import com.my.dental.core.dto.MedicoDTO;
import com.my.dental.exception.MyDentalException;
import com.my.dental.service.MedicoService;
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
@RequestMapping("/v1/medico")
public class MedicoController {

	private final MedicoService medicoService;

	@GetMapping
	public ResponseEntity<List<MedicoDTO>> findAll() {

		try {
			List<MedicoDTO> lstMedicoDTO = medicoService.findAll();

			if (isNull(lstMedicoDTO) || lstMedicoDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstMedicoDTO);
		} catch (Exception e) {
			if(e instanceof MyDentalException)
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable(value = "id", required = true) Long id) {
		try {
			Optional<MedicoDTO> optMedicoDTO = medicoService.findById(id);

			if (isNull(optMedicoDTO) || optMedicoDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(optMedicoDTO.get());
		} catch (Exception e) {
			if(e instanceof MyDentalException)
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody MedicoDTO medicoDTO) {
		try {
			Long id = medicoService.save(medicoDTO);
			if (isNull(id)) {
				return ResponseEntity.badRequest().build();
			}
			Map<String, Object> resp = new HashMap<>();
			resp.put("message", "El medico fue registrado con exito");
			resp.put("ide", id);
			return ResponseEntity.ok(resp);
		} catch (Exception e) {
			if(e instanceof MyDentalException)
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping
	public ResponseEntity<?> modificar(@RequestBody MedicoDTO medicoDTO) {
		try {
			medicoService.modificar(medicoDTO);
			Map<String, Object> resp = new HashMap<>();
			resp.put("message", "El medico fue modificado con exito");
			resp.put("ide", medicoDTO.getId());
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
			medicoService.delete(id);
			Map<String, Object> resp = new HashMap<>();
			resp.put("message", "El Medico se elimino con exito");
			resp.put("ide", id);
			return ResponseEntity.ok(resp);
		} catch (Exception e) {
			if(e instanceof MyDentalException)
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

			return ResponseEntity.internalServerError().build();
		}
	}

}
