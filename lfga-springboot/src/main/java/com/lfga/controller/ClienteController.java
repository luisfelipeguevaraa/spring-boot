package com.lfga.controller;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lfga.dto.ClienteDetailsDto;
import com.lfga.dto.ClienteDto;
import com.lfga.dto.ClienteKpiDto;
import com.lfga.functions.Utilitario;
import com.lfga.model.Cliente;
import com.lfga.service.ClienteService;

@RestController
@RequestMapping("/rest/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@RequestMapping(method=RequestMethod.GET, value ="/listclientes")
	public List<ClienteDetailsDto> getAllClientes() {
		List<Cliente> clientes = clienteService.getAllClientes();
		List<ClienteDetailsDto> clientesDetailsDto = clientes.stream()
				.map(cliente -> convertToClienteDetailsDto(cliente))
				.collect(Collectors.toList());
		clientesDetailsDto.stream().forEach(u -> u.setFechaProbableMuerte(Utilitario.fechaProbMuerte()));
		return clientesDetailsDto;
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/creacliente")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ClienteDto addCliente(@RequestBody ClienteDto clienteDto) throws ParseException {
		Cliente cliente = convertToEntity(clienteDto);
		clienteService.addCliente(cliente);
		return clienteDto;
	}
	
	@RequestMapping(method=RequestMethod.GET, value = "/kpideclientes")
	public ClienteKpiDto getDesviacionStandar() {
		Collection<Cliente> clientes = clienteService.getAllClientes();
		List<Integer> edades = clientes.stream()
				                   .map(Cliente::getEdad).collect(Collectors.toList());
		int promedio = Utilitario.promedio(edades.stream().mapToInt(i->i).toArray());
		double desviacion = Utilitario.desviacion(edades.stream().mapToInt(i->i).toArray());
		ClienteKpiDto respuesta = new ClienteKpiDto() {{
			setPromedioEdad(promedio);
			setDesviacionEstandarEdad(desviacion);
		}};
		return respuesta;
	}	
	
	private Cliente convertToEntity(ClienteDto clienteDto) throws ParseException{
		Cliente cliente = modelMapper.map(clienteDto, Cliente.class);
		return cliente;
	}
	
	private ClienteDetailsDto convertToClienteDetailsDto(Cliente cliente) {
		ClienteDetailsDto clienteDetailsDto = modelMapper.map(cliente, ClienteDetailsDto.class);
		return clienteDetailsDto;
	}
	
}
