package com.algaworks.algafood.repositories.infrastructure.storage;

import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import com.algaworks.algafood.core.storage.StorageProperties;
import com.algaworks.algafood.domain.service.FotoStorageService;

public class DiscoLocalFotoStorageService implements FotoStorageService {
	
	//14.8. Implementando o serviço de armazenagem de fotos no disco local
	
//	@Value("${algafood.storage.local.diretorio-fotos}")
//	private Path diretorioFotos;
	
	@Autowired
	private StorageProperties storageProperties;
	
	@Override
	public void armazenar(NovaFoto novaFoto) {
		
		try {
			
			Path filePath = getFilePath(novaFoto.getNomeArquivo());
//			String filePath = getFilePath(novaFoto.getNomeArquivo());
//			Path path = Path.of(filePath);
			
			System.out.println("PATH: " + getFilePath(novaFoto.getNomeArquivo()));
			
			FileCopyUtils.copy(novaFoto.getInputStream(), Files.newOutputStream(filePath));
			
		} catch (Exception e) {
			throw new StorageException("Não foi possível armazenar o arquivo.", e);
		}
	}
	
	
	@Override
	public FotoRecuperada recuperar(String nomeArquivo) {
		
		try {
			
			Path filePath = getFilePath(nomeArquivo);
			
			FotoRecuperada fotoRecuperada = new FotoRecuperada();
			fotoRecuperada.setInputStream(Files.newInputStream(filePath));
			
			return fotoRecuperada;
			
		} catch (Exception e) {
			throw new StorageException("Não foi possível recuperar o arquivo.", e);
		}
	}
	
	
	@Override
	public void remover(String nomeArquivo) {
		
		try {
			
			Path filePath = getFilePath(nomeArquivo);
			Files.deleteIfExists(filePath);
			
		} catch (Exception e) {
			throw new StorageException("Não foi possível excluir o arquivo.", e);
		}
	}
	
	//Retorna o caminho completo do arquivo: pasta + nome arquivo
	private Path getFilePath(String fileName) {
		return storageProperties.getLocal().getDiretorioFotos().resolve(Path.of(fileName));
		//return diretorioFotos.resolve(Path.of(fileName));
	}
//	private String getFilePath(String fileName) {
//		return DIRETORIO_FOTOS.concat(fileName);
//	}
}