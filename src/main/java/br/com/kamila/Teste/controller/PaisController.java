package br.com.kamila.Teste.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.kamila.Teste.model.Pais;
import br.com.kamila.Teste.model.Token;
import br.com.kamila.Teste.service.PaisService;
import br.com.kamila.Teste.service.TokenService;

@Controller
@RequestMapping("pais/")
public class PaisController {

	@Autowired
	PaisService paisService;
	
	@Autowired
	TokenService tokenService;
	
	@RequestMapping(value="listar/{token}", method = RequestMethod.GET)
	public ResponseEntity<List<Pais>> listar(@PathVariable String token){
		try {
			Token t = tokenService.getByToken(token);
			if(t.getExpiracao().compareTo(new Date(System.currentTimeMillis())) < 0) {
				return new ResponseEntity<List<Pais>>(paisService.findAll(), HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Pais>>(HttpStatus.UNAUTHORIZED);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<List<Pais>>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value="salvar/{token}", method = RequestMethod.POST)
	public ResponseEntity<Pais> salvar(@RequestBody Pais pais, @PathVariable String token) {
		try {
			Token t = tokenService.getByToken(token);
			
			Calendar expiracao = Calendar.getInstance();
			expiracao.setTime(t.getExpiracao());
						
			if(t.isAdministrador() && expiracao.after(Calendar.getInstance())) {
				return new ResponseEntity<Pais>(paisService.save(pais), HttpStatus.OK);
			} else {
				return new ResponseEntity<Pais>(HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Pais>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	
}
