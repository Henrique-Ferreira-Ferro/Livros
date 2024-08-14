package com.br.books.GerenciaLivros.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
					@ApiResponse(responseCode = "404", description = "Não foi possivel encontrar: ")
	})
	@GetMapping("/{id}")
	private LivroDTO getLivrosById(@PathVariable Long id) {
		return livroService.getLivrosById(id);
	}
	
	@Operation(summary= "Resgatar todos os livros", description = "Essa funcionalidade é responsavel por buscar todos os livros ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Livro encontrados",
				content = @Content(mediaType = "application/json",
				schema = @Schema(implementation = LivroEntity.class)))
	})
	@GetMapping
	public List<LivroDTO> getAllLivros(){
		return livroService.getAllLivros();
	}

	
	@Operation(summary = "Criar um livro", description = "Essa funcionalidade é responsavel pela criação dos livros ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Livro criado",
				content = @Content(mediaType = "application/json",
				schema = @Schema(implementation = LivroEntity.class))),
			@ApiResponse(responseCode = "400", description = "Não deixe de inserir autor e titulo da obra")
		
	})
	@PostMapping("/create")
	public LivroDTO createLivro(@RequestBody LivroDTO livroDto) {
		return livroService.createLivro(livroDto);
	}

	@Operation(summary = "Atualizar um livro por id", description = "Essa funcionalidade é responsavel pela alteração de um determinado livro passando seu id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Livro alterado com sucesso",
					content = @Content(mediaType = "application/json",
					schema = @Schema(implementation = LivroEntity.class))),
			@ApiResponse(responseCode = "404", description = "Não foi possivel encontrar: "),
			@ApiResponse(responseCode = "400", description = "Não deixe de inserir autor e titulo da obra")
	})
	@PutMapping("/update/{id}")
	public LivroDTO updateLivro(@RequestBody LivroDTO livroDto, @PathVariable Long id) {
		return livroService.updateLivro(livroDto, id);
	}
	
	@Operation(summary = "Remover um livro", description = "Essa funcionalidade é reponsavel por deletar um livro pelo seu ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Livro deletado com sucesso",
					content = @Content(mediaType = "application/json",
					schema = @Schema(implementation = LivroEntity.class))),
			@ApiResponse(responseCode = "404", description = "Não foi possivel encontrar: ")
	})
	@DeleteMapping("/{id}")
	public String deleteLivro(@PathVariable Long id) {
		return livroService.deleteLivro(id);
	}

	
	
}
