package br.com.zup.edu.zupflix.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import br.com.zup.edu.zupflix.controller.TipoExibicao;

@Entity
public class Palestra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String titulo;
	
	@Column(nullable = false)
	private String tema;
	
	@Column(nullable = false)
	private Integer tempo;
	
	@Column(nullable = false)
	private TipoExibicao tipo;
	
	@Column(nullable = false)
	private LocalDateTime dataHoraExibicao;
	
	@Column(nullable = false)
    @Size(min = 1)
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "palestra_zupper", joinColumns = @JoinColumn(name = "palestra_id"), inverseJoinColumns = @JoinColumn(name = "zupper_id"))
    private Set<Zupper> zuppers = new HashSet<>();

	public Palestra(String titulo, String tema, Integer tempo, TipoExibicao tipo, LocalDateTime dataHoraExibicao,
			Set<Zupper> zuppers) {
		this.titulo = titulo;
		this.tema = tema;
		this.tempo = tempo;
		this.tipo = tipo;
		this.dataHoraExibicao = dataHoraExibicao;
		this.zuppers = zuppers;
	}
	
	/**
	 * @deprecated construtor para uso exclusivo do hibernate
	 */
	@Deprecated
	public Palestra() {
		
	}

	public Long getId() {
		return id;
	}
	
}
