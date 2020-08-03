package br.com.kamila.springpaises.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ResponseDTO implements Serializable {

	private static final long serialVersionUID = -2043937770298024848L;

	private String mensagem;

	private Boolean sucesso;

	private Object conteudo;

	public ResponseDTO() {
		super();
	}

	public ResponseDTO(Boolean sucesso) {
		super();
		this.sucesso = sucesso;
	}

	public ResponseDTO(String mensagem) {
		super();
		this.mensagem = mensagem;
	}

	public ResponseDTO(String mensagem, Boolean sucesso) {
		super();
		this.mensagem = mensagem;
		this.sucesso = sucesso;
	}

	public ResponseDTO(String mensagem, Boolean sucesso, Object conteudo) {
		super();
		this.mensagem = mensagem;
		this.sucesso = sucesso;
		this.conteudo = conteudo;
	}

	public Object getConteudo() {
		return conteudo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public Boolean getSucesso() {
		return sucesso;
	}

	public void setConteudo(Object conteudo) {
		this.conteudo = conteudo;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public void setSucesso(Boolean sucesso) {
		this.sucesso = sucesso;
	}
}
