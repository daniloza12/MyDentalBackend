package com.my.dental.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.my.dental.core.dto.ClienteDTO;
import com.my.dental.core.entity.ClienteEntity;
import com.my.dental.core.mapper.ClienteMapper;
import com.my.dental.exception.MyDentalException;
import com.my.dental.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class ClienteServiceImpl implements ClienteService {

	private final ClienteRepository clienteRepository;
	private final ClienteMapper clienteMapper;

    @Override
	public Optional<ClienteDTO> findById(Long id) throws MyDentalException {
		String msg = String.format("No existe cliente con id => %s", id);
		ClienteEntity clienteEntity = this.clienteRepository.findById(id).orElseThrow(() -> new MyDentalException(msg));
		ClienteDTO clienteDTO = clienteMapper.toDTO(clienteEntity);
		return Optional.ofNullable(clienteDTO);
	}

	@Override
	public List<ClienteDTO> findAll() {
		return clienteMapper.toLstDTO(clienteRepository.findAll());
	}

	@Override
	public Long save(ClienteDTO clienteDTO) throws MyDentalException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaNacimiento = LocalDate.parse(clienteDTO.getFechaNacimiento(), formatter);
		ClienteEntity clienteEntity = clienteMapper.toEntity(clienteDTO);
		clienteEntity.setEstado(1);
		clienteEntity.setFechaNacimiento(fechaNacimiento);
		clienteEntity.setFechaRegistro(LocalDateTime.now());
		ClienteEntity clienteEntitySave = clienteRepository.save(clienteEntity);
		if (isNull(clienteEntitySave)) {
			throw new MyDentalException("Error al resgitrar cliente");
		}
		return clienteEntitySave.getId();
	}

	@Override
	public void modificaCliente(ClienteDTO clienteDTO) throws MyDentalException {
		Optional<ClienteEntity> clienteBd = clienteRepository.findById(clienteDTO.getId());
		if(clienteBd.isEmpty()){
			throw new MyDentalException("El Cliente Indicada No Existe");
		}
		ClienteEntity clienteEntityBd = clienteBd.get();
 		ClienteEntity clienteEntity = clienteMapper.toEntity(clienteDTO);
		clienteEntity.setEstado(clienteDTO.getEstado());
		clienteEntity.setFechaNacimiento(LocalDate.parse(clienteDTO.getFechaNacimiento()));
		clienteEntity.setFechaRegistro(clienteEntityBd.getFechaRegistro());
		clienteEntity.setUserRegister(clienteEntityBd.getUserRegister());
		clienteRepository.save(clienteEntity);
 	}

	@Override
	public void delete(Long id) throws MyDentalException {
		String msg = String.format("No existe cliente con id => %s", id);
		this.clienteRepository.findById(id).orElseThrow(() -> new MyDentalException(msg));
		clienteRepository.deleteById(id);
	}

}
