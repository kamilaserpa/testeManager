package br.com.kamila.springpaises.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.kamila.springpaises.dto.ResponseDTO;
import br.com.kamila.springpaises.model.Pais;
import br.com.kamila.springpaises.model.Token;
import br.com.kamila.springpaises.service.PaisService;
import br.com.kamila.springpaises.service.TokenService;
import br.com.kamila.springpaises.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/*
 * @author kamilardg@gmail.com.br
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("pais")
@Api( tags = { "PAIS" })
public class PaisController {

	@Autowired
	PaisService paisService;

	@Autowired
	TokenService tokenService;

	@Autowired
	private TokenUtil tokenUtil;
	
	
	@ApiOperation(
			value = "LISTA TODOS OS PAÍSES", 
			notes = "Recupera lista de países caso o token seja válido"
		)
	@RequestMapping(value = "listar", method = RequestMethod.GET)
	public ResponseEntity<?> listarPaises(
			@ApiParam(value = "Token para validação de acesso", required = true) @RequestParam String token) {
		try {
			
			if (tokenUtil.validaToken(token)) {
				return new ResponseEntity<ResponseDTO>(new ResponseDTO("Sucesso", true, paisService.findAll()), HttpStatus.OK);
			} else {
				return new ResponseEntity<ResponseDTO>(new ResponseDTO("Ação não autorizada", false), HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(e.getMessage(), false, e), HttpStatus.BAD_REQUEST);
		}
	}

	
	@ApiOperation(
			value = "SALVA OU ATUALIZA PAÍS", 
			notes = "Caso o id já esteja presente no banco de dados será realizado o update. " +
					"Caso o id seja nulo, zero ou não persistido no banco o pais será salvo, " +
					"satisfazendo as exigências de negócio."
		)
	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public ResponseEntity<?> salvarPais(
			@ApiParam(value = "Objeto País", required = true) @RequestBody Pais pais,
			@ApiParam(value = "Token para validação de acesso", required = true) @RequestParam String token) {
		try {
			Token t = tokenService.getByCdToken(token);

			if (t.isAdministrador() && tokenUtil.validaToken(token)) {
				return new ResponseEntity<ResponseDTO>(new ResponseDTO("Sucesso", true, paisService.save(pais)), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<ResponseDTO>(new ResponseDTO("Ação não autorizada", false), HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(e.getMessage(), false, e), HttpStatus.BAD_REQUEST);
		}
	}

	
	@ApiOperation(
			value = "BUSCA PAÍS PELO NOME", 
			notes = "Busca países cujo nome contém a String 'nome' enviada, caso o token seja válido."
		)
	@RequestMapping(value = "pesquisar", method = RequestMethod.GET)
	public ResponseEntity<?> pesquisarPaises(
			@ApiParam(value = "Token para validação de acesso", required = true) @RequestParam String token,
			@ApiParam(value = "Nome ou parte do nome de um país", required = true) @RequestParam String nome) {
		try {

			if (tokenUtil.validaToken(token)) {
				return new ResponseEntity<ResponseDTO>(new ResponseDTO("Sucesso", true, paisService.pesquisarNome(nome)), HttpStatus.OK);
			} else {
				return new ResponseEntity<ResponseDTO>(new ResponseDTO("Ação não autorizada", false), HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(e.getMessage(), false, e), HttpStatus.BAD_REQUEST);
		}
	}

	
	@ApiOperation(
			value = "EXCLUI PAIS", 
			notes = "Exclui país que contém o id enviado casoo token seja válido."
		)
	@RequestMapping(value = "excluir", method = RequestMethod.GET)
	public ResponseEntity<?> excluirPais(
			@ApiParam(value = "ID do país", required = true) @RequestParam Long id,
			@ApiParam(value = "Token para validação de acesso", required = true)  @RequestParam String token) {
		try {
			Token t = tokenService.getByCdToken(token);

			if (t.isAdministrador() && tokenUtil.validaToken(token)) {
				return new ResponseEntity<ResponseDTO>(new ResponseDTO("Sucesso", true, paisService.delete(id)), HttpStatus.OK);
			} else {
				return new ResponseEntity<ResponseDTO>(new ResponseDTO("Ação não autorizada", false), HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseDTO>(new ResponseDTO(e.getMessage(), false, e), HttpStatus.BAD_REQUEST);
		}
	}

}
