package it.uniroma3.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.spring.model.Autore;
import it.uniroma3.spring.service.AutoreService;

@Controller
public class GestioneElencoAutoriController {
	@Autowired
	private AutoreService autoreService;
	
	@GetMapping("/visualizzaElencoAutori")
	public String visualizzaElenco(Model model) {
	    model.addAttribute("autori", autoreService.findAll());
	    model.addAttribute("postMode","/visualizzaOpereAutore");
	    model.addAttribute("selectText","Lista opere");
	    model.addAttribute("onClickSelect","");
	    model.addAttribute("backPage","location.href='/'");
	    return "elencoAutori";
	}
	
	@GetMapping("/gestisciElencoAutori")
	public String gestisciElenco(Model model) {
	    model.addAttribute("autori", autoreService.findAll());
	    model.addAttribute("postMode","/rimuoviAutore");
	    model.addAttribute("selectText","Rimuovi");
	    model.addAttribute("onClickSelect","return confirm('Tutte le opere associate verranno rimosse. Procedere?')");
	    model.addAttribute("backPage","location.href='/areaRiservata.html'");
	    return "elencoAutori";
	}
	
	@GetMapping("/scegliAutore")
	public String scegliAutore(Model model) {
	    model.addAttribute("autori", autoreService.findAll());
	    model.addAttribute("postMode","/scegliAutore");
	    model.addAttribute("selectText","Seleziona");
	    model.addAttribute("onClickSelect","");
	    model.addAttribute("backPage","location.href='/areaRiservata.html'");
	    return "elencoAutori";
	}
	
	@PostMapping("/rimuoviAutore")
	public String rimuoviAutore(@RequestParam("idAutore") long idAutore, Model model) {
	    autoreService.remove(idAutore);
	    return gestisciElenco(model);
	}
}
