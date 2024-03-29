package com.generation.blogpessoal.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="tb_postagens")
public class Postagem {

	@Id // Torna o atributo uma chave primátia. 
	@GeneratedValue(strategy=GenerationType.IDENTITY) // Adiciona o ID como auto_increment. 
	private Long id;
	
	@Size(min=5, max=100, message= "O título deve ter no mínimo 5 caracteres e no máximo 100.")
	@NotBlank(message="Atributo título é obrigatório.")
	private String titulo; 
	
	@Size(min=10, max=1000, message= "O texto deve ter no mínimo 10 caracteres e no máximo 1000.")
	@NotBlank(message="Atributo texto é obrigatório.")
	private String texto;
	
	@UpdateTimestamp
	private LocalDateTime data;

	@ManyToOne // relação com tema
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	
	@ManyToOne // relação com usuário
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	} 
	
	
	
}
