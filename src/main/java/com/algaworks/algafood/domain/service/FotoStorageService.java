package com.algaworks.algafood.domain.service;

import java.io.InputStream;
import java.util.UUID;

public interface FotoStorageService {
	
	//14.8. Implementando o serviço de armazenagem de fotos no disco local
	
	void armazenar(NovaFoto novaFoto);
	
	void remover(String nomeArquivo);
	
	default void substituirArquivo(String nomeArquivoAntigo, NovaFoto novaFoto) {
		
		this.armazenar(novaFoto);
		
		if (nomeArquivoAntigo != null) {
			this.remover(nomeArquivoAntigo);
		}
	}
	
	default String generateFileName(String originalName) {
		return UUID.randomUUID().toString().concat("_").concat(originalName);
	}
	
	class NovaFoto {
		
		private String nomeArquivo;
		private InputStream inputStream;

		public NovaFoto(String nomeArquivo, InputStream inputStream) {
			super();
			this.nomeArquivo = nomeArquivo;
			this.inputStream = inputStream;
		}

		public String getNomeArquivo() {
			return nomeArquivo;
		}

		public InputStream getInputStream() {
			return inputStream;
		}

		public void setNomeArquivo(String nomeArquivo) {
			this.nomeArquivo = nomeArquivo;
		}

		public void setInputStream(InputStream inputStream) {
			this.inputStream = inputStream;
		}
	}
}