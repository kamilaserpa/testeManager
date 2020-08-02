package br.com.kamila.springpaises.dto;

public class UsuarioAutenticadoDTO {
	
	private String login;
	private String nome;
	private String token;
	private boolean administrador;
	private boolean autenticado;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public boolean isAdministrador() {
		return administrador;
	}
	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}
	public boolean isAutenticado() {
		return autenticado;
	}
	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}
	
	

}
