package br.com.kamila.Teste.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kamila.Teste.model.Usuario;
import br.com.kamila.Teste.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired 
	private UsuarioRepository usuarioRepository;

	public Usuario getByLogin(String login) {
		return usuarioRepository.getByLogin(login);
	}
	
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

}
