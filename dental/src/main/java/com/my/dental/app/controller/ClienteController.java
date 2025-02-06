package com.my.dental.app.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.my.dental.core.dto.ClienteDTO;
import com.my.dental.exception.MyDentalException;
import com.my.dental.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;
import java.util.HashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/cliente")
public class ClienteController {

	private final ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		try {
			List<ClienteDTO> lstClienteDTO = clienteService.findAll();

			if (isNull(lstClienteDTO) || lstClienteDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstClienteDTO);
		} catch (Exception e) {
			if(e instanceof MyDentalException)
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable(value = "id", required = true) Long id) {
		try {
			Optional<ClienteDTO> optClienteDTO = clienteService.findById(id);

			if (isNull(optClienteDTO) || optClienteDTO.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(optClienteDTO.get());
		} catch (Exception e) {
			if(e instanceof MyDentalException)
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody ClienteDTO clienteDTO) {
		try {
			Long id = clienteService.save(clienteDTO);
			if (isNull(id)) {
				return ResponseEntity.badRequest().build();
			}
			Map<String, Object> resp = new HashMap<>();
			resp.put("message", "El cliente fue registrado con exito");
			resp.put("ide", id);
			return ResponseEntity.ok(resp);
		} catch (Exception e) {
			if(e instanceof MyDentalException)
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping
	public ResponseEntity<?> modificar(@RequestBody ClienteDTO clienteDTO) {
		try {
			clienteService.modificaCliente(clienteDTO);
			Map<String, Object> resp = new HashMap<>();
			resp.put("message", "El cliente fue modificado con exito");
			resp.put("ide", clienteDTO.getId());
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
			clienteService.delete(id);
			Map<String, Object> resp = new HashMap<>();
			resp.put("message", "El cliente se elimino con exito");
			resp.put("ide", id);
			return ResponseEntity.ok(resp);
		} catch (Exception e) {
			if(e instanceof MyDentalException)
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

			return ResponseEntity.internalServerError().build();
		}
	}

}
