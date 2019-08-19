package br.com.compasso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.compasso.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{
	Cidade findById(int id);
	
	@Query("select c from Cidade c where c.nome like %?1%") 
	List<Cidade> consultarCidadePorNome(String nome);
	
	@Query("select c from Cidade c where c.estado = ?1") 
	List<Cidade> consultarCidadePorEstado(String estado);
	
	@Query("select c from Cidade c where c.nome like %?1% and c.estado = ?2") 
	List<Cidade> consultarCidadePorNomeEEstado(String nome, String estado);	
	

}
