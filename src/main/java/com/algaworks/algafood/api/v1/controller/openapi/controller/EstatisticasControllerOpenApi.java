package com.algaworks.algafood.api.v1.controller.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.v1.controller.EstatisticasController.EstatisticasModel;
import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Estatisticas")
public interface EstatisticasControllerOpenApi {
	
	@ApiOperation("Consulta vendas diárias")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "restauranteId", value = "ID do restaurante", example = "1", dataType = "int"),
		@ApiImplicitParam(name = "dataCriacaoInicio", value = "Data/hora inicial da criação do pedido", example = "2019-12-01T00:00:00Z", dataType = "date-time"),
		@ApiImplicitParam(name = "dataCriacaoFim", value = "Data/hora final da criação do pedido", example = "2019-12-02T23:59:59Z", dataType = "date-time")
	})
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
	
	@ApiOperation("Consulta vendas diárias em PDF")
	public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro, String timeOffset);
	
	@ApiOperation(value = "Estatísticas", hidden = true)
	EstatisticasModel estatisticas();

}