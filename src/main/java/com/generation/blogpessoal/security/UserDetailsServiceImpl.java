package com.generation.blogpessoal.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.repository.UsuarioRepository;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository; 
	//injeção de dependencias para conseguir consultar no banco
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(username);
		if(usuario.isPresent())
			return new UserDetailsImpl(usuario.get());
			else 
				throw new ResponseStatusException(HttpStatus.FORBIDDEN); 
	}
}
