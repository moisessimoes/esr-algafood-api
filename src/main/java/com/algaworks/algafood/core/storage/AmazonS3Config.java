package com.algaworks.algafood.core.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AmazonS3Config {
	
	//14.22. Definindo bean do client da Amazon S3 e configurando credenciais
	
	/* Como nap tenho conta na AWS, todo o codigo abaixo vai ficar comentado e servira
	 * apenas para registro, caso seja necessario no futuro
	 * 
	 * */
	
	@Autowired
	private StorageProperties storageProps;
	
	@Bean
	public AmazonS3 amazonS3() {
		
		var credentials = new BasicAWSCredentials(storageProps.getS3().getIdChaveAcesso(), 
												  storageProps.getS3().getChaveAcessoSecreta());
		
		return AmazonS3ClientBuilder.standard()
									.withCredentials(new AWSStaticCredentialsProvider(credentials))
									.withRegion(storageProps.getS3().getRegiao())
									.build();
	}
}