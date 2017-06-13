package it.uniroma3.spring.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.spring.model.Autore;
import it.uniroma3.spring.service.AutoreService;

@Controller
public class NuovoAutoreController {

	@InitBinder
	private void dateBinder(WebDataBinder binder) {
	            //The date format to parse or output your dates
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	            //Create a new CustomDateEditor
	    CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
	            //Register it as custom editor for the Date type
	    binder.registerCustomEditor(Date.class, editor);
	}

	@Autowired
	private AutoreService autoreService;

	@GetMapping("/nuovoAutore")
	public String mostraForm(Autore autore) {
		return "formNuovoAutore";
	}

	@PostMapping("/nuovoAutore")
	public String controllaDatiAutore(@Valid @ModelAttribute Autore autore, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "formNuovoAutore";
		} else {
			model.addAttribute(autore);
			autoreService.add(autore);
		}
		return "riepilogoNuovoAutore";
	}
}
