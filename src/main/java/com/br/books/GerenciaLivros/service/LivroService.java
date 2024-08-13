package com.br.books.GerenciaLivros.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.books.GerenciaLivros.dto.LivroDTO;
import com.br.books.GerenciaLivros.entities.LivroEntity;
import com.br.books.GerenciaLivros.exceptions.BadRequestException;
import com.br.books.GerenciaLivros.repository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;
	
	//Encontrar livro por ID
	
	public LivroDTO getLivrosById(Long id) {
		Optional<LivroEntity> livroE = livroRepository.findById(id);
		
		if(livroE.isEmpty()) {
			throw new ObjectNotFoundException(id, new LivroDTO().getClass().getName());
		}
		
		return new LivroDTO(livroE.get());
	}
	
	
	//Listar todos os livros
	public List<LivroDTO> getAllLivros(){
		List<LivroEntity> livroE = livroRepository.findAll();
		List<LivroDTO> livroD = new ArrayList<LivroDTO>();
		
		for (LivroEntity livro : livroE) {
			livroD.add(new LivroDTO(livro));
		}
		
		return livroD;
	}
	
	
	//Criar um livro
	
	public LivroDTO createLivro(LivroDTO livroDto) {
		LivroEntity livroEn = new LivroEntity();
		if(livroDto.getAutor().isEmpty()) {
			throw new BadRequestException("Não deixe o nome do Autor vaziu");
		}
		livroEn.setAutor(livroDto.getAutor());
		if(livroDto.getTitulo().isEmpty()) {
			throw new BadRequestException("Não deixe o titulo vaziu");
		}
		livroEn.setTitulo(livroDto.getTitulo());
	
		livroEn.setDataPublicacao(livroDto.getData());
		livroEn = livroRepository.save(livroEn);
		
		return new LivroDTO(livroEn);
	}
	
	
	//atualizar um livro
	public LivroDTO updateLivro(LivroDTO livroDto, Long id) {
		
		Optional<LivroEntity> livroEnt = livroRepository.findById(id);
		
		if(livroEnt.isEmpty()) {
			throw new ObjectNotFoundException(id, new LivroDTO().getClass().getName());
		}
		
		LivroEntity livroEnMod = livroEnt.get();
		
		if(livroDto.getAutor().isEmpty()) {
			throw new BadRequestException("Não deixe o nome do Autor vaziu");
		}
		livroEnMod.setAutor(livroDto.getAutor());
		livroEnMod.setDataPublicacao(livroDto.getData());
		
		if(livroDto.getTitulo().isEmpty()) {
			throw new BadRequestException("Não deixe o titulo vaziu");
		}
		livroEnMod.setTitulo(livroDto.getTitulo());
		livroEnMod = livroRepository.save(livroEnMod);
		
		return new LivroDTO(livroEnMod);
	}
	
	
	
	//Deletar um livro
	public String deleteLivro(Long id) {
		
		Optional<LivroEntity> livroEnt = livroRepository.findById(id);
		
		if(livroEnt.isEmpty()) {
			throw new ObjectNotFoundException(id, new LivroDTO().getClass().getName());

		}
			LivroDTO livroDto = new LivroDTO(livroEnt.get());
			livroRepository.deleteById(id);
			return "O Livro: "+ livroDto.getTitulo()+", foi deletado com sucesso!";
	}
	
	
	
	
	
	
}
