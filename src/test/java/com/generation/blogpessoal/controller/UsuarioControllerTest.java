package com.generation.blogpessoal.controller;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.repository.UsuarioRepository;
import com.generation.blogpessoal.service.UsuarioService;

//define porta utilizada para os testes
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

//ciclo de vida do teste definido por classe
@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
public class UsuarioControllerTest {

	@Autowired
	private UsuarioService usuarioService; 
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	//requisições http testes (pq estamos trabalhando com api) 
	@Autowired
	private TestRestTemplate testRestTemplate; 
	
	@BeforeAll
	void start() {
		usuarioRepository.deleteAll(); // deleta todos os registros da tabela usuário. 
	
		// tens o que é preciso para cadastrar outro usuário? (executa o método em um ambiente de teste).
		usuarioService.cadastrarUsuario(new Usuario (0L, "Root", "root@root.com", "rootroot", "")); 
	}
	
	// pode ser void já que é teste e não necessita retorno 
	// colocar NOME FACILMENTE IDENTIFICÁVEL 
	// "JAYUNIT" Não JUNITE/JUDITE -- Aqui teste que cadastra usuário. 
	
	@Test
	@DisplayName("Cadastra um usuário.")
	public void deveCriarUmUruario() {
		HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>
		(new Usuario(0L, "Paulo César", "paulinho@teste.com", "paulinho123", "")); 
		
		ResponseEntity<Usuario> corpoResposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, Usuario.class); 
		
		Assertions.assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());
	}

	@Test
	@DisplayName("Não deve permitir duplicação do Usuário")
	public void naoDeveDuplicarUsuario() {

		usuarioService.cadastrarUsuario(new Usuario(0L, 
			"Maria da Silva", "maria_silva@email.com.br", "13465278", "-"));

		HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(new Usuario(0L, 
			"Maria da Silva", "maria_silva@email.com.br", "13465278", "-"));

		ResponseEntity<Usuario> corpoResposta = testRestTemplate
			.exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, Usuario.class);

		Assertions.assertEquals(HttpStatus.BAD_REQUEST, corpoResposta.getStatusCode());
	}
	
	@Test
	@DisplayName("Atualizar um Usuário")
	public void deveAtualizarUmUsuario() {

		Optional<Usuario> usuarioCadastrado = usuarioService.cadastrarUsuario(new Usuario(0L, 
			"Ana Beatriz Gomes", "anabeatriz@teste.com.br", "anabia1234", "-"));

		Usuario usuarioUpdate = new Usuario(usuarioCadastrado.get().getId(), 
			"Ana Beatriz Gomes", "anabeatriz@teste.com.r", "anabia1234" , "-");
		
		HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(usuarioUpdate);

		ResponseEntity<Usuario> corpoResposta = testRestTemplate
			.withBasicAuth("root@root.com", "rootroot")
			.exchange("/usuarios/atualizar", HttpMethod.PUT, corpoRequisicao, Usuario.class);

		Assertions.assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
		
	}

	@Test
	@DisplayName("Listar todos os Usuários")
	public void deveMostrarTodosUsuarios() {

		usuarioService.cadastrarUsuario(new Usuario(0L, 
			"Sabrina Carpenter", "sabrina_carpenter@teste.com.br", "sabrina123", "-"));
		
		usuarioService.cadastrarUsuario(new Usuario(0L, 
			"João Danyel", "joao_danyel@teste.com.br", "joaozes123", "-"));

		ResponseEntity<String> resposta = testRestTemplate
		.withBasicAuth("root@root.com", "rootroot")
			.exchange("/usuarios/all", HttpMethod.GET, null, String.class);

		Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());

	}
}
