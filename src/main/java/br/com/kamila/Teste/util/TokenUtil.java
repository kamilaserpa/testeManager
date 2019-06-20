package br.com.kamila.Teste.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.kamila.Teste.model.Token;
import br.com.kamila.Teste.model.Usuario;
import br.com.kamila.Teste.service.TokenService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class TokenUtil {

	@Autowired
	TokenService tokenService;
	
	public String gerarToken(Usuario u){
		JwtBuilder builder = Jwts.builder();
		String token = builder.setId(u.getId().toString()).setSubject(u.getLogin())
				.signWith(SignatureAlgorithm.HS512, "teste")
				.setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 1000)).compact();
		saveToken(u, token);
		return token;
	}
	
	public void saveToken(Usuario u, String token) {
		Token tokenEntity = new Token();
		tokenEntity.setAdministrador(u.isAdministrador());
		tokenEntity.setExpiracao(new Date(System.currentTimeMillis() + 5 * 60 * 1000));
		tokenEntity.setLogin(u.getLogin());
		tokenEntity.setToken(token);
		try {
			tokenService.save(tokenEntity);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean renova(String token) {
		try {
			Token t = tokenService.getByToken(token);
			t.setExpiracao(new Date(System.currentTimeMillis() + 5 * 60 * 1000));
			tokenService.save(t);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
