package br.com.zup.edu.zupflix.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.edu.zupflix.model.Zupper;
import br.com.zup.edu.zupflix.repository.ZupperRepository;

@RestController
@RequestMapping("/zuppers")
public class ZupperController {
	
	private final ZupperRepository repository;
	
	
	
	public ZupperController(ZupperRepository repository) {
		this.repository = repository;
	}

	@PostMapping
	ResponseEntity<Void> cadastrar(@RequestBody @Valid ZupperDTO request, UriComponentsBuilder uriComponentsBuilder){
		Zupper zupper = request.toModel();
		
		repository.save(zupper);
		URI location = uriComponentsBuilder.path("/zuppers/{id}").buildAndExpand(zupper.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
}
