package br.com.kamila.springpaises.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.kamila.springpaises.dto.UsuarioAutenticadoDTO;
import br.com.kamila.springpaises.model.Usuario;
import br.com.kamila.springpaises.service.UsuarioService;
import br.com.kamila.springpaises.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("usuario")
@Api(tags = { "USUARIO" })
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private TokenUtil tokenUtil;

	@ApiOperation(
			value = "AUTENTICA USUÁRIO", 
			notes = "Autentica usuário por login e senha", 
			nickname = "autenticar"
		)
	@RequestMapping(value = "autenticar", method = RequestMethod.POST)
	public ResponseEntity<UsuarioAutenticadoDTO> autenticar(@RequestParam String login, @RequestParam String senha) {
		try {
			Usuario u = usuarioService.getByLogin(login);
			UsuarioAutenticadoDTO ua = new UsuarioAutenticadoDTO();

			if (u.getSenha().equals(senha)) {
				ua.setToken(tokenUtil.geraToken(u));
				ua.setAutenticado(true);
				ua.setAdministrador(u.isAdministrador());
				return new ResponseEntity<UsuarioAutenticadoDTO>(ua, HttpStatus.OK);
			} else {
				ua.setAutenticado(false);
				return new ResponseEntity<UsuarioAutenticadoDTO>(ua, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UsuarioAutenticadoDTO>(HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(
			value = "RENOVA TOKEN DE USUÁRIO", 
			notes = "Renova o token por mais 5 minutos", 
			nickname = "renovaToken"
		)
	@RequestMapping(value = "renovar-ticket", method = RequestMethod.GET)
	public ResponseEntity<Boolean> renovarToken(@ApiParam(value = "Token para validação de acesso", required = true) @RequestParam String token) {
		try {

			if (tokenUtil.renovaToken(token)) {
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			} else {
				return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		}
	}
}
