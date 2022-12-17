package com.algaworks.algafood.core.storage;

import java.nio.file.Path;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.amazonaws.regions.Regions;

@Component
@ConfigurationProperties("algafood.storage")
public class StorageProperties {
	
	//14.20. Criando bean de propriedades de configuração dos serviços de storage
	
	//Essa variavel vai guardar o caminho do disco local para salvar as fotos
	private Local local = new Local();
	
	//Essa variavel vai guardar o caminho do AWS para salvar as fotos
	private S3 s3 = new S3();
	
	private TipoStorage tipo = TipoStorage.LOCAL;
	
	public enum TipoStorage {
		
		LOCAL, S3;
	}
	
	public class Local {
		
		private Path diretorioFotos;

		public Path getDiretorioFotos() {
			return diretorioFotos;
		}

		public void setDiretorioFotos(Path diretorioFotos) {
			this.diretorioFotos = diretorioFotos;
		}
	}
	
	
	public class S3 {
		
		private String idChaveAcesso;
		private String idChaveSecreta;
		private String bucket;
		private Regions regiao;
		private String diretorioFotos;
		
		public String getIdChaveAcesso() {
			return idChaveAcesso;
		}
		public void setIdChaveAcesso(String idChaveAcesso) {
			this.idChaveAcesso = idChaveAcesso;
		}
		public String getIdChaveSecreta() {
			return idChaveSecreta;
		}
		public void setIdChaveSecreta(String idChaveSecreta) {
			this.idChaveSecreta = idChaveSecreta;
		}
		public String getBucket() {
			return bucket;
		}
		public void setBucket(String bucket) {
			this.bucket = bucket;
		}
		public Regions getRegiao() {
			return regiao;
		}
		public void setRegiao(Regions regiao) {
			this.regiao = regiao;
		}
		public String getDiretorioFotos() {
			return diretorioFotos;
		}
		public void setDiretorioFotos(String diretorioFotos) {
			this.diretorioFotos = diretorioFotos;
		}
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public S3 getS3() {
		return s3;
	}

	public void setS3(S3 s3) {
		this.s3 = s3;
	}

	public TipoStorage getTipo() {
		return tipo;
	}

	public void setTipo(TipoStorage tipo) {
		this.tipo = tipo;
	}
}