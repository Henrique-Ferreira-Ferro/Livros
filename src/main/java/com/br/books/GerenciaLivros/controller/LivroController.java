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

@RestController
@RequestMapping("/livro")
public class LivroController {

	
	@Primary @Bean
	  public ObjectMapper objectMapper() {
	      JavaTimeModule javaTimeModule = new JavaTimeModule();
	      final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	      javaTimeModule.addSerializer(new LocalDateTimeSerializer(dateTimeFormatter));
	      javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
	      final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	      javaTimeModule.addSerializer(new LocalDateSerializer(dateFormatter));
	      javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateTimeFormatter));
	      return JsonMapper.builder()
	          // mimic default ObjectMapper's disabled features, according to Baeldung article.
	          .disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
	          .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
	          .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
	          .addModule(javaTimeModule)
	          .build();
	  }
	
	@Autowired
	private LivroService livroService;

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
	public String deleteLivro(Long id) {
		return livroService.deleteLivro(id);
	}

	
	
}
