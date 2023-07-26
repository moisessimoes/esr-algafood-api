package com.algaworks.algafood.api.v1.controller.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.algaworks.algafood.api.v1.controller.EstatisticasController.EstatisticasModel;
import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Estatísticas")
public interface EstatisticasControllerOpenApi {
	
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "restauranteId", value = "ID do restaurante", example = "1", dataType = "int"),
//		@ApiImplicitParam(name = "dataCriacaoInicio", value = "Data/hora inicial da criação do pedido", example = "2019-12-01T00:00:00Z", dataType = "date-time"),
//		@ApiImplicitParam(name = "dataCriacaoFim", value = "Data/hora final da criação do pedido", example = "2019-12-02T23:59:59Z", dataType = "date-time")
//	})
	@Operation(summary = "Consulta as vendas diárias")
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, 
			@Parameter(description = "Deslocamento de horário a ser considerado na consulta em relação ao UTC") String timeOffset);
	
	@Operation(summary = "Consulta vendas em PDF")
	public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro, String timeOffset);
	
	//@ApiOperation(value = "Estatísticas", hidden = true)
	@Operation(summary = "Estatísticas")
	EstatisticasModel estatisticas();

}