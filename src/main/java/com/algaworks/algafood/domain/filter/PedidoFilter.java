package com.algaworks.algafood.domain.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class PedidoFilter {
	
	//@ApiModelProperty(position = 0, example = "1")
	private Long clienteId;
	
	//@ApiModelProperty(position = 1, example = "1")
	private Long restauranteId;
	
	//@ApiModelProperty(position = 2, example = "2023-01-02T20:34:04Z")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataCriacaoInicio;
	
	//@ApiModelProperty(position = 3, example = "2023-01-02T20:34:04Z")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime dataCriacaoFim;
	
	public Long getClienteId() {
		return clienteId;
	}
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
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