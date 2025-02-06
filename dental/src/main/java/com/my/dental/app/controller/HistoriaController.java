package com.my.dental.app.controller;

import com.my.dental.core.dto.HistoriaDTO;
import com.my.dental.exception.MyDentalException;
import com.my.dental.service.HistoriaService;
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
@RequestMapping("/v1/historia")
public class HistoriaController {

	private final HistoriaService historiaService;

	@GetMapping
	public ResponseEntity<List<HistoriaDTO>> findAll() {

		try {
			List<HistoriaDTO> lstHistoriaDTO = historiaService.findAll();

			if (isNull(lstHistoriaDTO) || lstHistoriaDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstHistoriaDTO);
		} catch (Exception e) {
			if(e instanceof MyDentalException)
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable(value = "id", required = true) Long id) {
		try {
			Optional<HistoriaDTO> optHistoriaDTO = historiaService.findById(id);

			if (isNull(optHistoriaDTO) || optHistoriaDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(optHistoriaDTO.get());
		} catch (Exception e) {
			if(e instanceof MyDentalException)
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody HistoriaDTO historiaDTO) {
		try {
			Long id = historiaService.save(historiaDTO);
			if (isNull(id)) {
				return ResponseEntity.badRequest().build();
			}
			Map<String, Object> resp = new HashMap<>();
			resp.put("message", "El historia fue registrado con exito");
			resp.put("ide", id);
			return ResponseEntity.ok(resp);
		} catch (Exception e) {
			if(e instanceof MyDentalException)
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping
	public ResponseEntity<?> modificar(@RequestBody HistoriaDTO historiaDTO) {
		try {
			historiaService.modificar(historiaDTO);
			Map<String, Object> resp = new HashMap<>();
			resp.put("message", "El historia fue modificado con exito");
			resp.put("ide", historiaDTO.getId());
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
			historiaService.delete(id);
			Map<String, Object> resp = new HashMap<>();
			resp.put("message", "El Historia se elimino con exito");
			resp.put("ide", id);
			return ResponseEntity.ok(resp);
		} catch (Exception e) {
			if(e instanceof MyDentalException)
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

			return ResponseEntity.internalServerError().build();
		}
	}

}
