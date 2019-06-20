package br.com.kamila.Teste.controller;

import java.util.Calendar;
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
						
			if(validaToken(token)) {
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
									
			if(t.isAdministrador() && validaToken(token)) {
				return new ResponseEntity<Pais>(paisService.save(pais), HttpStatus.OK);
			} else {
				return new ResponseEntity<Pais>(HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Pais>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value="pesquisar/{token}/{nome}", method = RequestMethod.GET)
	public ResponseEntity<List<Pais>> pesquisar(@PathVariable String token, @PathVariable String nome){
		try {
			
			if(validaToken(token)) {
				return new ResponseEntity<List<Pais>>(paisService.pesquisarNome(nome), HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Pais>>(HttpStatus.UNAUTHORIZED);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<List<Pais>>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value="excluir/{id}/{token}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> excluir(@PathVariable Long id, @PathVariable String token) {
		try {
			Token t = tokenService.getByToken(token);
						
			if(t.isAdministrador() && validaToken(token)) {
				return new ResponseEntity<Boolean>(paisService.delete(id), HttpStatus.OK);
			} else {
				return new ResponseEntity<Boolean>(HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<Boolean>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	public boolean validaToken (String token) {
		try {
			Token t = tokenService.getByToken(token);
			
			Calendar expiracao = Calendar.getInstance();
			expiracao.setTime(t.getExpiracao());
			
			if(expiracao.after(Calendar.getInstance())) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		
	}
	
}
