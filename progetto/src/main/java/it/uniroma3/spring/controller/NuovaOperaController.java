package it.uniroma3.spring.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import it.uniroma3.spring.model.Autore;
import it.uniroma3.spring.model.Opera;
import it.uniroma3.spring.service.AutoreService;
import it.uniroma3.spring.service.OperaService;

@Controller
public class NuovaOperaController {

	@Autowired
	private OperaService operaService;
	@Autowired
	private AutoreService autoreService;

	@PostMapping("/scegliAutore")
	public String mostraForm(@RequestParam("idAutore") long idAutore, Model model, Opera opera) {
		Autore a = autoreService.findbyId(idAutore);
		opera.setAutore(a);
		return "formNuovaOpera";
	}
	
	@PostMapping("/nuovaOpera")
	public String controllaDatiOpera(@RequestParam("file") MultipartFile file, @Valid @ModelAttribute Opera opera,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "formNuovaOpera";
		} else {
			model.addAttribute(opera);
			opera.getAutore().addOpera(opera);
			try {
				byte[] fileBytes = file.getBytes();
				if (fileBytes.length > 0)
					opera.setImmagine(fileBytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
			operaService.add(opera);
		}
		return "riepilogoNuovaOpera";
	}

	@RequestMapping(value = "/image/{opera_id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getImage(@PathVariable("opera_id") Long imageId) throws IOException {

		byte[] imageContent = operaService.findbyId(imageId).getImmagine();
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
	}
	
}
