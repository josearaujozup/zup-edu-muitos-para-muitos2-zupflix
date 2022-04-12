package br.com.zup.edu.zupflix.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.edu.zupflix.model.Palestra;
import br.com.zup.edu.zupflix.model.Zupper;
import br.com.zup.edu.zupflix.repository.PalestraRepository;
import br.com.zup.edu.zupflix.repository.ZupperRepository;

@RestController
@RequestMapping("/palestras")
public class PalestraController {
	
	private final ZupperRepository zupperRepository;
	private final PalestraRepository repository;
	
	public PalestraController(ZupperRepository zupperRepository, PalestraRepository repository) {
		this.zupperRepository = zupperRepository;
		this.repository = repository;
	}
	
	@PostMapping
	ResponseEntity<Void> cadastrar(@RequestBody @Valid PalestraDTO request,UriComponentsBuilder uriComponentsBuilder){
		
		Palestra palestra = request.toModel(zupperRepository);
		
		repository.save(palestra);
		URI location = uriComponentsBuilder.path("/palestras/{id}").buildAndExpand(palestra.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
}
