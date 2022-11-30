package com.algaworks.algafood.repositories.infrastructure.jasper;

public class JasperReportException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public JasperReportException(String message) {
		super(message);
	}
	
	public JasperReportException(String message, Throwable cause) {
		super(message, cause);
	}
}	