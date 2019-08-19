package br.com.compasso.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.compasso.model.Cidade;
import br.com.compasso.service.CidadeService;

@Controller
@RequestMapping("/")
public class CidadeController {
	
	CidadeService cidadeService;
	
	@Autowired
	public CidadeController(CidadeService cidService) {
		this.cidadeService = cidService;
	}
	
	public String viewCidadeIncluir = "mantercidade/incluir-cidade"; 
	public String viewCidadeManter = "mantercidade/manter-cidade";
	
	// End points de Cidade
	
	@RequestMapping("/mantercidade")
	public String inicio (Model model) {
		model.addAttribute("listaCidades", cidadeService.consultarCidades());
		model.addAttribute("cidade", new Cidade());
		return viewCidadeManter;
	}
	
	@RequestMapping("/incluirCidade")
	public String incluirCidade(Model model) {
		model.addAttribute("cidade", new Cidade());
		return viewCidadeIncluir;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/salvarCidade")
	public String cadastrarCidade(Cidade cidade, Model model){
		cidadeService.cadastrarCidade(cidade);
		model.addAttribute("listaCidades", cidadeService.consultarCidades());
		model.addAttribute("cidade", new Cidade());
		return viewCidadeManter;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/excluirCidade/{id}")
	public String excluirCidade(@PathVariable("id") Integer id, Cidade cidade, Model model){
		Cidade cid = cidadeService.consultarPorId(id);
		cidadeService.excluirCidade(cid);
		model.addAttribute("listaCidades", cidadeService.consultarCidades());
		model.addAttribute("cidade", new Cidade());
		return viewCidadeManter;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listarCidades")
	public String consultarCidades(Model model){
		model.addAttribute("listaCidades", cidadeService.consultarCidades());
		model.addAttribute("cidade", new Cidade());
		return viewCidadeManter;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/pesquisarCidade")
	public String pesquisarCidade(Cidade cidade, Model model){
		
		List<Cidade> lista = new ArrayList<Cidade>();
		if(cidade.getNome() != null && !cidade.getNome().equals("") 
				&& cidade.getEstado() != null && !cidade.getEstado().equals("")) {
			lista = cidadeService.consultarCidadePorNomeEEstado(cidade.getNome(), cidade.getEstado());
		}else {
			if(cidade.getNome() != null && !cidade.getNome().equals("")) {
				lista = cidadeService.consultarCidadePorNome(cidade.getNome());
			}else {
				if(cidade.getEstado() != null && !cidade.getEstado().equals("")) {
					lista = cidadeService.consultarCidadePorEstado(cidade.getEstado());				
				}
	
			}
		}
		
		model.addAttribute("listaCidades", lista);
		model.addAttribute("cidade", new Cidade());
		return viewCidadeManter;
	}
	

}
