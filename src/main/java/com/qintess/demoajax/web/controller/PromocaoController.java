package com.qintess.demoajax.web.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qintess.demoajax.domain.Categoria;
import com.qintess.demoajax.domain.Promocao;
import com.qintess.demoajax.repository.CategoriaRepository;
import com.qintess.demoajax.repository.PromocaoRepository;

@Controller
@RequestMapping("/promocao")
public class PromocaoController {
	
	private static Logger log = LoggerFactory.getLogger(PromocaoController.class);
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private PromocaoRepository promocaoRepository; 


	@ModelAttribute("categorias")
	private List<Categoria> getCategorias(){
		
		return categoriaRepository.findAll();
	}
	
	//adicionar ofertas
	@PostMapping("/save")
	public ResponseEntity<?> salvarPromocao(@Valid Promocao promocao, BindingResult result){
		
		if (result.hasErrors()) {
			
			Map<String, String> errors = new HashMap<>();
			for (FieldError error : result.getFieldErrors() ) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			
			return ResponseEntity.unprocessableEntity().body(errors);
			
		}
		
		log.info("Promocao {} " , promocao.toString());
		promocao.setDtCadastro(LocalDateTime.now());
		promocaoRepository.save(promocao);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/add")
	public String abrirCadastro() {
		
		return "promo-add";
	}
	
	// listar ofertas
	
	@GetMapping("/list")
	public String listarOfertas(ModelMap model) {
		Sort sort = Sort.by(Sort.Direction.DESC, "dtCadastro");
		PageRequest pageRequest = PageRequest.of(0, 8, sort);		
		model.addAttribute("promocoes", promocaoRepository.findAll(pageRequest));
		return "promo-list";
	}
	
	@GetMapping("/list/ajax")
	public String listarCards(@RequestParam(name = "page", defaultValue = "1") int page,  ModelMap model) {
		Sort sort = Sort.by(Sort.Direction.DESC, "dtCadastro");
		PageRequest pageRequest = PageRequest.of(page, 8, sort);		
		model.addAttribute("promocoes", promocaoRepository.findAll(pageRequest));
		return "promo-card";
	}
}
