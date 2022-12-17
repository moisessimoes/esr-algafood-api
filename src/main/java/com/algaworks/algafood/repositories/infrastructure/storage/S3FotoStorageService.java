package com.algaworks.algafood.repositories.infrastructure.storage;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;

import com.algaworks.algafood.core.storage.StorageProperties;
import com.algaworks.algafood.domain.service.FotoStorageService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class S3FotoStorageService implements FotoStorageService {
	
	//14.23. Implementando a inclusão de objetos no bucket da Amazon S3
	
	/* Como nap tenho conta na AWS, todo o codigo abaixo vai ficar comentado e servira
	 * apenas para registro, caso seja necessario no futuro
	 * 
	 * */
	
	@Autowired
	private AmazonS3 amazonS3;
	
	@Autowired
	private StorageProperties storageProps;

	@Override
	public FotoRecuperada recuperar(String nomeArquivo) {
		
		//14.25. Implementando a recuperação de foto no serviço de storage do S3
		
		String caminhoArquivo = getCaminhoArquivo(nomeArquivo);
		
		URL url = amazonS3.getUrl(storageProps.getS3().getBucket(), caminhoArquivo);
		
		var fotoRecuperada = new FotoRecuperada();
		fotoRecuperada.setUrl(url.toString());
		
		return fotoRecuperada;
	}

	@Override
	public void armazenar(NovaFoto novaFoto) {
		
		try {
			
			String caminhoArquivo = getCaminhoArquivo(novaFoto.getNomeArquivo());
			
			var objectMetaData = new ObjectMetadata();
			objectMetaData.setContentType(novaFoto.getContentType());
			
			var putObjectRequest = new PutObjectRequest(storageProps.getS3().getBucket(), 
														caminhoArquivo, 
														novaFoto.getInputStream(), 
														objectMetaData).withCannedAcl(CannedAccessControlList.PublicRead);
													
			amazonS3.putObject(putObjectRequest);
			
		} catch (Exception e) {
			throw new StorageException("Não foi possível enviar arquivo para Amazon S3.", e);
		}
	}


	@Override
	public void remover(String nomeArquivo) {
		
		try {
			
			String caminhoArquivo = getCaminhoArquivo(nomeArquivo);
			
			var deleteObjectRequest = new DeleteObjectRequest(storageProps.getS3().getBucket(), caminhoArquivo);
			
			amazonS3.deleteObject(deleteObjectRequest);
			
		} catch (Exception e) {
			throw new StorageException("Não foi possível excluir arquivo na Amazon S3.", e);
		}
	}
	
	private String getCaminhoArquivo(String nomeArquivo) {
		return String.format("%s/%s", storageProps.getS3().getDiretorioFotos(), nomeArquivo);
	}
}