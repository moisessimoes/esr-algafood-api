package com.algaworks.algafood.core.validation;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;

public class ValorZeroIncluiDescricaoValidator implements ConstraintValidator<ValorZeroIncluiDescricao, Object> {
	
	private String valorField;
	private String descricaoField;
	private String descricaoObrigatoria;
	
	@Override
	public void initialize(ValorZeroIncluiDescricao constraintAnnotation) {
		
		this.valorField = constraintAnnotation.valorField();
		this.descricaoField = constraintAnnotation.descricaoField();
		this.descricaoObrigatoria = constraintAnnotation.descricaoObrigatoria();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		boolean valido = true;
		
		/*
		 * Como estamos recebendo um Object como parametro, nao da pra saber qual classe vai vir ali.
		 * 
		 * Nesse exemplo, está sendo usado o Restaurante.
		 * 
		 * Atraves do uso de Reflections com o BeanUtils é possivel acessar os metodos da classe que vem como parametro
		 * 
		 * */
		
		try {
			
			BigDecimal valor = (BigDecimal) BeanUtils.getPropertyDescriptor(value.getClass(), valorField).getReadMethod().invoke(value);
			String descricao = (String) BeanUtils.getPropertyDescriptor(value.getClass(), descricaoField).getReadMethod().invoke(value);
			
			if (valor != null && BigDecimal.ZERO.compareTo(valor) == 0 && descricao != null) {
				
				valido = descricao.toLowerCase().contains(this.descricaoObrigatoria.toLowerCase());
			}
			
			return valido;
			
		} catch (Exception e) {
			throw new ValidationException(e);
		}
	}
}