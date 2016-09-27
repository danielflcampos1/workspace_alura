package br.com.caelum.contas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OlaSpringController {

	@RequestMapping("/olaspring")
	public String execute(){
		return "ok";
	}
}
