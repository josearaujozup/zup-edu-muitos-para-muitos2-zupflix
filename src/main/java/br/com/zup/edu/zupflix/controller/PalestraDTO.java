package br.com.zup.edu.zupflix.controller;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zup.edu.zupflix.model.Palestra;
import br.com.zup.edu.zupflix.model.Zupper;
import br.com.zup.edu.zupflix.repository.ZupperRepository;

public class PalestraDTO {
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	private String tema;
	
	@NotNull
    @Min(30)
	private Integer tempo;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoExibicao tipo;
	
	@Future
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	@NotNull
	private LocalDateTime dataHoraExibicao;
	
	@NotEmpty
    @Size(min = 1)
    private Set<Long> zupperIds;

	public PalestraDTO(@NotBlank String titulo, @NotBlank String tema, @NotNull @Min(30) Integer tempo,
			@NotNull TipoExibicao tipo, @Future @NotNull LocalDateTime dataHoraExibicao,
			@NotEmpty @Size(min = 1) Set<Long> zupperIds) {
		
		this.titulo = titulo;
		this.tema = tema;
		this.tempo = tempo;
		this.tipo = tipo;
		this.dataHoraExibicao = dataHoraExibicao;
		this.zupperIds = zupperIds;
	}
	
	public PalestraDTO() {
		
	}
	
	public Palestra toModel(ZupperRepository zupperRepository) {
        Set<Zupper> zuppers = this.zupperIds.stream()
                .map(idZupper -> zupperRepository.findById(idZupper).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Zupper não cadastrado")))
                .collect(Collectors.toSet());
        
        Palestra palestra = new Palestra(titulo, tema, tempo, tipo, dataHoraExibicao,zuppers);
    
        return palestra;
    }

	public String getTitulo() {
		return titulo;
	}

	public String getTema() {
		return tema;
	}

	public Integer getTempo() {
		return tempo;
	}

	public TipoExibicao getTipo() {
		return tipo;
	}

	public LocalDateTime getDataHoraExibicao() {
		return dataHoraExibicao;
	}

	public Set<Long> getZupperIds() {
		return zupperIds;
	}

}
