package br.com.kamila.Teste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kamila.Teste.model.Token;
import br.com.kamila.Teste.repository.TokenRepository;

@Service
public class TokenService {

	@Autowired
	private TokenRepository tokenRepository;
	
	public Token save(Token token) {
		return tokenRepository.save(token);
	}

	public Token getByToken(String token) {
		return tokenRepository.getByToken(token);
	}
}
