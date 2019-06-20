package br.com.kamila.Teste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.kamila.Teste.model.Usuario;
import br.com.kamila.Teste.model.UsuarioAutenticado;
import br.com.kamila.Teste.service.TokenService;
import br.com.kamila.Teste.service.UsuarioService;
import br.com.kamila.Teste.util.TokenUtil;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private TokenUtil tokenUtil;
		
	@RequestMapping(value="autenticar", method = RequestMethod.POST)
	public ResponseEntity<UsuarioAutenticado> autenticar(@RequestBody Usuario usuario) {
		try {
			Usuario u = usuarioService.getByLogin(usuario.getLogin());
			UsuarioAutenticado ua = new UsuarioAutenticado();
			
			if(u.getSenha().equals(usuario.getSenha())) {
				ua.setToken(tokenUtil.gerarToken(u));
				ua.setAutenticado(true);
				ua.setAdministrador(u.isAdministrador());
				return new ResponseEntity<UsuarioAutenticado>(ua, HttpStatus.OK);
			} else {
				ua.setAutenticado(false);
				return new ResponseEntity<UsuarioAutenticado>(ua, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UsuarioAutenticado>(HttpStatus.BAD_REQUEST);
		}
	
	}
	
	@RequestMapping(value="renovar-ticket/{token}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> renovaToken (@PathVariable String token) {
		try {
			
			if(tokenUtil.renova(token)) {
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			} else {
				return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		}
	}
}
