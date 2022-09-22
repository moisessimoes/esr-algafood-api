package com.algaworks.algafood.api.exceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final String MSG_ERRO_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir, entre em contato com o administrador";

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {
		
		var problem = problemBuilder(HttpStatus.NOT_FOUND, ProblemType.RECURSO_NAO_ENCONTRADO, ex.getMessage());
		return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {
		
		var problem = problemBuilder(HttpStatus.BAD_REQUEST, ProblemType.ERRO_NEGOCIO, ex.getMessage());
		return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {
		
		var problem = problemBuilder(HttpStatus.CONFLICT, ProblemType.ENTIDADE_EM_USO, ex.getMessage());
		return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleAnyException(Exception ex, WebRequest request) {
		
		String detail = MSG_ERRO_USUARIO_FINAL;
		
		// Importante colocar o printStackTrace (pelo menos por enquanto, que não estamos
		// fazendo logging) para mostrar a stacktrace no console
		// Se não fizer isso, você não vai ver a stacktrace de exceptions que seriam importantes
		// para você durante, especialmente na fase de desenvolvimento
		ex.printStackTrace();
		
		var problem = problemBuilder(HttpStatus.INTERNAL_SERVER_ERROR, ProblemType.ERRO_DE_SISTEMA, detail);
		return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
			
		} else if (rootCause instanceof PropertyBindingException) {
			return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
		} 
		
		var problem = problemBuilder(status, ProblemType.MENSAGEM_INCOMPREENSIVEL, "O corpo da requisição está inválido! Verifique erro de sintaxe.");
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		if (body == null) {
			body = new Problem(status.value(), null, status.getReasonPhrase(), null, MSG_ERRO_USUARIO_FINAL, LocalDateTime.now());
			
		} else if (body instanceof String) {
			body = new Problem(status.value(), null, (String) body, null, MSG_ERRO_USUARIO_FINAL, LocalDateTime.now());
			
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	
	// ================================== TRATAR ERRO DE CAMPOS INEXISTENTES NO CORPO DA REQUISICAO (JSON) ===========================================================
	private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String path = joinPath(ex.getPath());
		
	    ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
	    
	    String detail = String.format("A propriedade '%s' não existe. Corrija ou remova essa propriedade e tente novamente.", path);
	    
	    var problem = problemBuilder(status, problemType, detail);
	    problem.setUserMessage(MSG_ERRO_USUARIO_FINAL);
	    
	    return handleExceptionInternal(ex, problem, headers, status, request);
	}
	//======================================================================================================================================
	
	
	// ================================== TRATAR ERRO DE URL NAO MAPEADA ===========================================================
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String detail = String.format("O recurso %s acessado não existe.", ex.getRequestURL());
		
		var problem = problemBuilder(status, problemType, detail);
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
   //===================================================================================================================================

	
	// ================================== TRATAR ERRO DE PARAMETROS DE URL ===========================================================
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	    
	    if (ex instanceof MethodArgumentTypeMismatchException) {
	        return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
	    }

	    return super.handleTypeMismatch(ex, headers, status, request);
	}
	
	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		
		ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
		String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', "
				+ "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
				ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
		
		var problem = problemBuilder(status, problemType, detail);
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	//===================================================================================================================================
	
	
	// ================================== TRATAR ERRO DE TIPOS DE DADOS INCOMPATIVEIS NO CORPO DA REQUISICAO (JSON) ===========================================================
	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String path = joinPath(ex.getPath());
	    
	    ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
	    String detail = String.format("A propriedade '%s' recebeu o valor '%s', "
	            + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
	            path, ex.getValue(), ex.getTargetType().getSimpleName());
	    
	    var problem = problemBuilder(status, problemType, detail);
	    problem.setUserMessage(MSG_ERRO_USUARIO_FINAL);
	    
	    return handleExceptionInternal(ex, problem, headers, status, request);
	}
	//================================================================================================================================
	
	
	// ================================== METODO AUXILIAR PARA PEGAR OS CAMPOS DO JSON NO CORPO DA REQUISICAO ===========================================================
	private String joinPath(List<Reference> references) {
		return references.stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));
	}


	private Problem problemBuilder(HttpStatus status, ProblemType problemType, String detail) {
		
		return new Problem(status.value(), problemType.getUri(), problemType.getTitle(), detail, detail, LocalDateTime.now());
	}
}