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
import it.uniroma3.spring.model.Opera;
import it.uniroma3.spring.service.AutoreService;
import it.uniroma3.spring.service.OperaService;

@Controller
public class GestioneElencoOpereController {
	@Autowired
	private AutoreService autoreservice;
	
	@Autowired
	private OperaService operaservice;
	
	@GetMapping("/visualizzaElencoOpere")
	public String visualizzaElenco(Model model) {
	    model.addAttribute("opere", operaservice.findAll());
	    model.addAttribute("postMode","/visualizzaOpereAutore");
	    model.addAttribute("selectText","Elenco opere dell'autore");
	    model.addAttribute("onClickSelect","");
	    model.addAttribute("backPage","location.href='/'");
	    model.addAttribute("mostraAzioni",true);
	    return "elencoOpere";
	}
	
	@PostMapping("/visualizzaOpereAutore")
	public String visualizzaElencoOpereAutore(@RequestParam("idAutore") long idAutore, Model model) {
	    model.addAttribute("opere", operaservice.findByAutore(autoreservice.findbyId(idAutore)));
	    model.addAttribute("mostraAzioni",false);
	    model.addAttribute("backPage","history.go(-1);");
	    return "elencoOpere";
	}
	
	@GetMapping("/gestisciElencoOpere")
	public String gestisciElenco(Model model) {
	    model.addAttribute("opere", operaservice.findAll());
	    model.addAttribute("postMode","/rimuoviOpera");
	    model.addAttribute("selectText","Rimuovi");
	    model.addAttribute("onClickSelect","return confirm('Confermare la rimozione?')");
	    model.addAttribute("backPage","location.href='/areaRiservata.html'");
	    model.addAttribute("mostraAzioni",true);
	    return "elencoOpere";
	}
	
	@PostMapping("/rimuoviOpera")
	public String rimuoviOpera(@RequestParam("idOpera") long idOpera, Model model) {
		Opera o = operaservice.findbyId(idOpera);
		o.getAutore().removeOpera(o);
	    operaservice.remove(idOpera);
	    return gestisciElenco(model);
	}
}
