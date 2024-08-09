package com.br.books.GerenciaLivros.dto;

import java.time.LocalDate;

import com.br.books.GerenciaLivros.entities.LivroEntity;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

public class LivroDTO {
	
	private Long id;
	private String titulo;
	private String autor;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate data;
	
	
	public LivroDTO() {
		
	}
	
	
	public LivroDTO(Long id, String titulo, String autor, LocalDate data) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.data = data;
	}
	
	public LivroDTO(LivroEntity entity) {
		this.id = entity.getId();
		this.titulo = entity.getTitulo();
		this.autor = entity.getAutor();
		this.data = entity.getDataPublicacao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
	
	
	
}
