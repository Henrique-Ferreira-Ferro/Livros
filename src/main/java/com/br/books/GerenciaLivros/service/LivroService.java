package com.br.books.GerenciaLivros.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.books.GerenciaLivros.dto.LivroDTO;
import com.br.books.GerenciaLivros.entities.LivroEntity;
import com.br.books.GerenciaLivros.repository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;
	
	//Encontrar livro por ID
	
	public LivroDTO getLivrosById(Long id) {
		Optional<LivroEntity> livroE = livroRepository.findById(id);
		
		if(livroE.isEmpty()) {
			throw new RuntimeException("Não foi possivel encontrar o Livro");
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
		livroEn.setAutor(livroDto.getAutor());
		livroEn.setTitulo(livroDto.getTitulo());
		livroEn.setDataPublicacao(livroDto.getData());
		livroEn = livroRepository.save(livroEn);
		
		return new LivroDTO(livroEn);
	}
	
	
	//atualizar um livro
	public LivroDTO updateLivro(LivroDTO livroDto, Long id) {
		
		Optional<LivroEntity> livroEnt = livroRepository.findById(id);
		
		if(livroEnt.isEmpty()) {
			throw new RuntimeException("Não foi encontrado nenhum livro");
		}
		
		LivroEntity livroEnMod = livroEnt.get();
		
		livroEnMod.setAutor(livroDto.getAutor());
		livroEnMod.setDataPublicacao(livroDto.getData());
		livroEnMod.setTitulo(livroDto.getTitulo());
		livroEnMod = livroRepository.save(livroEnMod);
		
		return new LivroDTO(livroEnMod);
	}
	
	
	
	//Deletar um livro
	public String deleteLivro(Long id) {
		
		Optional<LivroEntity> livro = livroRepository.findById(id);
		
		if(livro.isEmpty()) {
			throw new RuntimeException("Não foi possivel encontrar nenhum livro");
		}else {
			LivroDTO livroDto = new LivroDTO(livro.get());
			livroRepository.deleteById(id);
			return "O Livro :"+ livroDto.getTitulo()+", foi deletado com sucesso!";
		}
		
	}
	
	
	
	
	
	
}
