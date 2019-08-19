package br.com.compasso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.compasso.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	Cliente findById(int id);
	
	@Query("select c from Cliente c where c.nome like %?1%") 
	List<Cliente> consultarClientePorNome(String nome);
	
	@Query("select c from Cliente c where c.nome like %?1% and c.id = ?2") 
	List<Cliente> consultarClientePorNomeEId(String nome, Integer id);	

}
