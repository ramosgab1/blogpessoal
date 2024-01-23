package com.generation.blogpessoal.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Essa classe será focada no CADASTRO! 

@Entity
@Table(name="tb_usuario")
public class Usuario {

	@Id // id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto incremento
	private Long id;

	//Pode-se usar NotBlank (não deixa colocar espaços vazio) ou NotNull (permite espaços vazios)
	
	@NotBlank(message = "O nome é obrigatório.") // Não pode ser nulo + mensagem. 
	private String nome; // nome

	@NotBlank(message = "O usuário é obrigatório.")
	@Email(message ="O email deve ser válido!")
	private String usuario; // usuario
	
	@NotBlank(message = "A senha é obrigatória.")
	@Size(min = 8, message = "A senha precisa conter no mínimo oito caracteres.")
	private String senha; // senha
	
	@Size(max=5000, message = "O link da foto deve ter no máximo 5000 caracteres.")
	private String foto; // foto 
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.REMOVE) //um usuário para muitas postagens
	@JsonIgnoreProperties("usuario")
	private List<Postagem> postagem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	} 
}
