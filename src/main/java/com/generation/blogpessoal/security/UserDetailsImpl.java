package com.generation.blogpessoal.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.generation.blogpessoal.model.Usuario;

// Essa classe vai ter correlação com uma classe, por isso "implements"
public class UserDetailsImpl implements UserDetails {
// Fala para a segurança quais infos vamos usar para fazer logins. 
	
	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private List<GrantedAuthority> authorities;

	// método construtor passa as informações. 
	public UserDetailsImpl(Usuario user) {
		this.userName = user.getUsuario(); //usuario(aqui vai ser email)
		this.password = user.getSenha(); // aqui senha. 
	}

	// método construtor para garantir que não dê erro na aplicação.
	public UserDetailsImpl() {	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
