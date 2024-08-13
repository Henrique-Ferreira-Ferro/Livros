package com.br.books.GerenciaLivros.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.books.GerenciaLivros.dto.LivroDTO;
import com.br.books.GerenciaLivros.entities.LivroEntity;
import com.br.books.GerenciaLivros.service.LivroService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/livro")
public class LivroController {
	
	@Autowired
	private LivroService livroService;
	
	@Operation(summary = "Busca livro por id", description = "Essa funcionalidade possui como finalidade a busca de um livro pelo id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Livro encontrado",
					content = @Content(mediaType = "application/json",
					schema = @Schema(implementation = LivroEntity.class))),
					@ApiResponse(responseCode = "400", description = "Livro não encontrado")
	})
	@GetMapping("/{id}")
	private LivroDTO getLivrosById(@PathVariable Long id) {
		return livroService.getLivrosById(id);
	}
	
	@GetMapping
	public List<LivroDTO> getAllLivros(){
		return livroService.getAllLivros();
	}

	@PostMapping("/create")
	public LivroDTO createLivro(@RequestBody LivroDTO livroDto) {
		return livroService.createLivro(livroDto);
	}

	
	@PutMapping("/update/{id}")
	public LivroDTO updateLivro(@RequestBody LivroDTO livroDto, @PathVariable Long id) {
		return livroService.updateLivro(livroDto, id);
	}

	@DeleteMapping("/{id}")
	public String deleteLivro(@PathVariable Long id) {
		return livroService.deleteLivro(id);
	}

	
	
}
