package br.com.kamila.springpaises.util;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.kamila.springpaises.model.Token;
import br.com.kamila.springpaises.model.Usuario;
import br.com.kamila.springpaises.service.TokenService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class TokenUtil {

	@Autowired
	TokenService tokenService;
	
	public String geraToken(Usuario u){
		JwtBuilder builder = Jwts.builder();
		String token = builder.setId(u.getId().toString()).setSubject(u.getLogin())
				.signWith(SignatureAlgorithm.HS512, "teste")
				.setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 1000)).compact();
		salvaToken(u, token);
		return token;
	}
	
	public void salvaToken(Usuario u, String token) {
		Token tokenEntity = new Token();
		tokenEntity.setAdministrador(u.isAdministrador());
		tokenEntity.setDtExpiracao(new Date(System.currentTimeMillis() + 5 * 60 * 1000));
		tokenEntity.setLogin(u.getLogin());
		tokenEntity.setCdToken(token);
		try {
			tokenService.save(tokenEntity);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean renovaToken(String token) {
		try {
			Token t = tokenService.getByCdToken(token);
			t.setDtExpiracao(new Date(System.currentTimeMillis() + 5 * 60 * 1000));
			tokenService.save(t);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean validaToken(String cdToken) {
		try {
			Token t = tokenService.getByCdToken(cdToken);

			Calendar dtExpiracao = Calendar.getInstance();
			dtExpiracao.setTime(t.getDtExpiracao());

			if (dtExpiracao.after(Calendar.getInstance())) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

	}

}
