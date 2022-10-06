package com.algaworks.algafood;

import static org.hamcrest.CoreMatchers.equalTo;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.repositories.CozinhaRepository;
import com.algaworks.algafood.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(value = "/application-test.properties")
public class CadastroCozinhaIT {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@BeforeEach
	void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		
		databaseCleaner.clearTables();
		prepararDados();
	}
	
	@Test
	void deveRetornarStatus200_QuandoConsultarCozinhas() {
		
		RestAssured.given()
				   .accept(ContentType.JSON)
				   .when()
				   .get()
				   .then()
				   .statusCode(HttpStatus.OK.value());
	}
	
	
	@Test
	void deveConter4Cozinhas_QuandoConsultarCozinhas() {
		
		RestAssured.given()
				   .accept(ContentType.JSON)
				   .when()
				   .get()
				   .then()
				   .body("", Matchers.hasSize(4))
				   .body("nome", Matchers.hasItems("Brasileira", "Portuguesa"));
	}
	
	
	@Test
	void deveRetornarStatus201_QuandoCadastrarCozinha() {
		
		RestAssured.given()
				   .body("{ \"nome\": \"Árabe\" }")
				   .contentType(ContentType.JSON)
				   .accept(ContentType.JSON)
				   .when()
				   .post()
				   .then()
				   .statusCode(HttpStatus.CREATED.value());
	}
	
	
	@Test
	void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {
		
		RestAssured.given()
				   .pathParam("cozinhaId", 2)
				   .accept(ContentType.JSON)
				   .when()
				   .get("/{cozinhaId}")
				   .then()
				   .statusCode(HttpStatus.OK.value())
				   .body("nome", equalTo("Americana"));
	}
	
	
	@Test
	void deveRetornarStatus404_QuandoConsultarCozinhaInexistente() {
		
		RestAssured.given()
				   .pathParam("cozinhaId", 100)
				   .accept(ContentType.JSON)
				   .when()
				   .get("/{cozinhaId}")
				   .then()
				   .statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	private void prepararDados() {
		
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Tailandesa");
		cozinhaRepository.save(cozinha1);

		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Americana");
		cozinhaRepository.save(cozinha2);
	}
}