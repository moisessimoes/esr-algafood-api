package com.algaworks.algafood.api.exceptionHandler;

public enum ProblemType {
	
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
	DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
	ACESSO_NEGADO("/acesso-negado", "Acesso negado"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido");
	
	private String title;
	private String uri;
	
	private ProblemType(String path, String title) {
		this.uri = "http://localhost:8080" + path;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public String getUri() {
		return uri;
	}
}