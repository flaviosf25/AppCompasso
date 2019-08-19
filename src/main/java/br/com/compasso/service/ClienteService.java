package br.com.compasso.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.model.Cliente;
import br.com.compasso.repository.ClienteRepository;

@Service
public class ClienteService {
	
	ClienteRepository clienteRepository; 
	
	@Autowired
	public ClienteService(ClienteRepository respository) {
		this.clienteRepository = respository;
	}
	
	public Cliente cadastrarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Collection<Cliente> consultarClientes(){
		return clienteRepository.findAll();
	}
	
	public void excluirCliente(Cliente cliente) {
		clienteRepository.delete(cliente);
	}
	
	public Cliente consultarClientePorId(int id) {
		return clienteRepository.findById(id);
	}
	
	public Cliente alterarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public List<Cliente> consultarClientePorNome(String nome) {
		return clienteRepository.consultarClientePorNome(nome);
	}
	
	public List<Cliente> consultarClientePorNomeEId(String nome, Integer id) {
		return clienteRepository.consultarClientePorNomeEId(nome, id);
	}

}
