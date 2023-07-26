package com.algaworks.algafood.domain.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.swagger.v3.oas.annotations.media.Schema;

public class VendaDiariaFilter {
	
	@Schema(example = "1", description = "ID do restaurante")
	private Long restauranteId;
	
	@Schema(example = "2023-07-26T00:00:00Z", description = "Data inicial da criação do pedido")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataCriacaoInicio;
	
	@Schema(example = "2023-07-26T00:00:00Z", description = "Data final da criação do pedido")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataCriacaoFim;

	public Long getRestauranteId() {
		return restauranteId;
	}

	public void setRestauranteId(Long restauranteId) {
		this.restauranteId = restauranteId;
	}

	public OffsetDateTime getDataCriacaoInicio() {
		return dataCriacaoInicio;
	}

	public void setDataCriacaoInicio(OffsetDateTime dataCriacaoInicio) {
		this.dataCriacaoInicio = dataCriacaoInicio;
	}

	public OffsetDateTime getDataCriacaoFim() {
		return dataCriacaoFim;
	}

	public void setDataCriacaoFim(OffsetDateTime dataCriacaoFim) {
		this.dataCriacaoFim = dataCriacaoFim;
	}
}