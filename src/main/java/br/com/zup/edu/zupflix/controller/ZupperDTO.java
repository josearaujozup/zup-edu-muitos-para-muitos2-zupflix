package br.com.zup.edu.zupflix.controller;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zup.edu.zupflix.model.Zupper;

public class ZupperDTO {
	
	@NotBlank
	private String nome;
	
	@PastOrPresent
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull
	private LocalDate dataAdmissao;
	
	@Email
	private String email;

	public ZupperDTO(@NotBlank String nome, @PastOrPresent LocalDate dataAdmissao, @Email String email) {
		this.nome = nome;
		this.dataAdmissao = dataAdmissao;
		this.email = email;
	}
	
	public ZupperDTO() {
		
	}
	
	public Zupper toModel() {
		return new Zupper(nome, dataAdmissao, email);
	}
	
	public String getNome() {
		return nome;
	}

	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}

	public String getEmail() {
		return email;
	}
}
