package br.com.kamila.springpaises.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kamila.springpaises.model.Token;
import br.com.kamila.springpaises.repository.TokenRepository;

@Service
public class TokenService {

	@Autowired
	private TokenRepository tokenRepository;
	
	public Token save(Token token) {
		return tokenRepository.save(token);
	}

	public Token getByCdToken(String token) {
		return tokenRepository.getByCdToken(token);
	}
}
