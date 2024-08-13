package com.br.books.GerenciaLivros.exceptions;

public class ForbiddenException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ForbiddenException(String mensage) {
		super(mensage);
	}
	
	
}
