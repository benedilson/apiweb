package br.com.fabricadeprogramador.ws.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fabricadeprogramador.ws.model.Cliente;
import br.com.fabricadeprogramador.ws.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	//private Map<Integer, Cliente> clientes = new HashMap<>();
	//private Integer proximoId = 1;
	
	public Cliente cadastrar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Collection<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}
	
	public void excluir(Cliente cliente) {
		clienteRepository.delete(cliente);
	}
	
	public Cliente buscaPorId(Integer id) {
		return clienteRepository.findOne(id);
	}
	
	public Cliente alterar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
}
