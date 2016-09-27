package br.com.caelum.contas.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.contas.dao.ContaDAO;
import br.com.caelum.contas.modelo.Conta;

@Controller
public class ContaController {

	@RequestMapping("/form")
	public String formulario(){
		return "formulario";
	}
	
	@RequestMapping("/adicionaConta")
	public String adiciona(Conta conta){
		
		ContaDAO contaDAO = new ContaDAO();
		contaDAO.adiciona(conta);

		System.out.println("Conta adicionada é: " + conta.getDescricao());
		
		return "redirect:listaContas";
	}
	
	@RequestMapping("/listaContas")
	public ModelAndView lista(){
		ContaDAO dao = new ContaDAO();
		List<Conta> contas = dao.lista();
	
		ModelAndView mv = new ModelAndView("conta/lista");
		mv.addObject("todasContas", contas);
		return mv;
	}
	
	//Igual a lista de cima utilizando Model
	@RequestMapping("/listaContas2")
	public String listaContas(Model mv){
		ContaDAO dao = new ContaDAO();
		List<Conta> contas = dao.lista();
	
		mv.addAttribute("todasContas", contas);
		return "conta/lista";
	}
	
	@RequestMapping("/removeConta")
	public String remove(Conta conta){
		ContaDAO dao = new ContaDAO();
		dao.remove(conta);
		return "redirect:listaContas";
	}
}