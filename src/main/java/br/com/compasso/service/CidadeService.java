package br.com.compasso.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compasso.model.Cidade;
import br.com.compasso.repository.CidadeRepository;

@Service
public class CidadeService {

	CidadeRepository cidadeRepository;
	
	@Autowired	
	public CidadeService(CidadeRepository cidRepository) {
		this.cidadeRepository = cidRepository;
	}
	
	public Cidade cadastrarCidade(Cidade cidade) {
		return cidadeRepository.save(cidade);
	}
	
	public Collection<Cidade> consultarCidades(){
		return cidadeRepository.findAll();
	}
	
	public void excluirCidade(Cidade cidade) {
		cidadeRepository.delete(cidade);
	}
	
	public Cidade consultarPorId(int id) {
		return cidadeRepository.findById(id);
	}
	
	public Cidade alterarCidade(Cidade cidade) {
		return cidadeRepository.save(cidade);
	}
	
	public List<Cidade> consultarCidadePorNome(String nome) {
		return cidadeRepository.consultarCidadePorNome(nome);
	}
	
	public List<Cidade> consultarCidadePorEstado(String estado) {
		return cidadeRepository.consultarCidadePorEstado(estado);
	}
	
	public List<Cidade> consultarCidadePorNomeEEstado(String nome, String estado) {
		return cidadeRepository.consultarCidadePorNomeEEstado(nome, estado);
	}
}
