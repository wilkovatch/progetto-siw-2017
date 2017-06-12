package it.uniroma3.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.spring.model.Autore;
import it.uniroma3.spring.model.Opera;
import it.uniroma3.spring.service.AutoreService;
import it.uniroma3.spring.service.OperaService;

@Controller
public class NuovaOperaController {

	@Autowired
	private OperaService operaservice;
	@Autowired
	private AutoreService autoreservice;

	@PostMapping("/scegliAutore")
	public String mostraForm(@RequestParam("idAutore") long idAutore, Model model, Opera opera) {
		Autore a = autoreservice.findbyId(idAutore);
		opera.setAutore(a);
		return "formNuovaOpera";
	}

	@PostMapping("/nuovaOpera")
	public String controllaDatiOpera(@Valid @ModelAttribute Opera opera, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "formNuovaOpera";
		} else {
			model.addAttribute(opera);
			opera.getAutore().addOpera(opera);
			operaservice.add(opera);
		}
		return "riepilogoNuovaOpera";
	}
}
