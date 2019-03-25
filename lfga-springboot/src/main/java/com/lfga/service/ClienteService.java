package com.lfga.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lfga.model.Cliente;
import com.lfga.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> getAllClientes(){
		List<Cliente> clientes = new ArrayList<>();
		clienteRepository.findAll()
		.forEach(clientes::add);
		return clientes;
	}
	
	public void addCliente(Cliente cliente)
	{
		clienteRepository.save(cliente);
	}
	
	
}
