package com.generation.blogpessoal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.blogpessoal.model.Usuario;

// Herda da JpaRepository <Usuario(indica que pega de usuário), Long (tipo do id)>
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{

	// assinatura (achar por usuário). 
	public Optional<Usuario> findByUsuario(String usuario);
}
