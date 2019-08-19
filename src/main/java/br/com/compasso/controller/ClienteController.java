package br.com.compasso.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.compasso.model.Cliente;
import br.com.compasso.service.CidadeService;
import br.com.compasso.service.ClienteService;

@Controller
@RequestMapping("/")
public class ClienteController {
	
	ClienteService clienteService;
	CidadeService cidadeService;
	
	@Autowired
	public ClienteController(ClienteService cliService, CidadeService cidService) {
		this.clienteService = cliService;
		this.cidadeService = cidService;
	}
	
	public String viewClienteIncluir = "mantercliente/incluir-cliente"; 
	public String viewClienteAlterar = "mantercliente/alterar-cliente"; 
	public String viewClienteManter = "mantercliente/manter-cliente"; 
	
	// End points do Cliente
	
	@RequestMapping("/mantercliente")
	public String inicio (Model model) {
		model.addAttribute("listaClientes", clienteService.consultarClientes());
		model.addAttribute("cliente", new Cliente());
		return viewClienteManter;
	}
	
	@RequestMapping("/incluirCliente")
	public String incluirCliente(Model model) {
		model.addAttribute("cliente", new Cliente());
		return viewClienteIncluir;
	}
	
	@RequestMapping("/editarCliente/{id}")
	public String editarCliente(@PathVariable("id") Integer id, Model model) {
		Cliente cli = clienteService.consultarClientePorId(id);
		model.addAttribute("cliente", cli);
		return viewClienteAlterar;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/salvarCliente")
	public String cadastrarCliente(Cliente cliente, Model model){
		clienteService.cadastrarCliente(cliente);
		model.addAttribute("listaClientes", clienteService.consultarClientes());
		model.addAttribute("cliente", new Cliente());
		return viewClienteManter;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/alterarCliente/{id}")
	public String alterarCliente(Cliente cliente, Model model){
		Cliente cli = clienteService.consultarClientePorId(cliente.getId());
		cliente.setCidade(cli.getCidade());
		clienteService.alterarCliente(cliente);
		model.addAttribute("listaClientes", clienteService.consultarClientes());
		model.addAttribute("cliente", new Cliente());
		return viewClienteManter;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/excluirCliente/{id}")
	public String excluirCliente(@PathVariable("id") Integer id, Cliente cliente, Model model){	
		Cliente cli = clienteService.consultarClientePorId(id);
		clienteService.excluirCliente(cli);
		model.addAttribute("listaClientes", clienteService.consultarClientes());
		model.addAttribute("cliente", new Cliente());
		return viewClienteManter;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listarClientes")
	public String consultarClientes(Model model){
		model.addAttribute("listaClientes", clienteService.consultarClientes());
		model.addAttribute("cliente", new Cliente());
		return viewClienteManter;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/pesquisarCliente")
	public String pesquisar(Cliente cliente, Model model){
		
		List<Cliente> lista = new ArrayList<Cliente>();
		if(cliente.getId() != null && cliente.getNome() != null && !cliente.getNome().equals("")) {
			lista = clienteService.consultarClientePorNomeEId(cliente.getNome(), cliente.getId());
		}else {
			if(cliente.getNome() != null && !cliente.getNome().equals("")) {
				lista = clienteService.consultarClientePorNome(cliente.getNome());
			}else {
				if(cliente.getId() != null) {
					cliente = clienteService.consultarClientePorId(cliente.getId());
					if(cliente != null) {
						lista.add(cliente);
					}
				}
			}
		}
		
		model.addAttribute("listaClientes", lista);
		model.addAttribute("cliente", new Cliente());
		return viewClienteManter;
	}
	
	

}
