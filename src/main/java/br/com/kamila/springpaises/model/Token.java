package br.com.kamila.springpaises.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_token")
public class Token implements Serializable {

	private static final long serialVersionUID = 1000951433307276123L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "cd_token")
	private String cdToken;
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "dt_expiracao")
	private Date dtExpiracao;
	
	@Column(name = "is_administrador")
	private boolean isAdministrador;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCdToken() {
		return cdToken;
	}

	public void setCdToken(String cdToken) {
		this.cdToken = cdToken;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Date getDtExpiracao() {
		return dtExpiracao;
	}

	public void setDtExpiracao(Date dtExpiracao) {
		this.dtExpiracao = dtExpiracao;
	}

	public boolean isAdministrador() {
		return isAdministrador;
	}

	public void setAdministrador(boolean isAdministrador) {
		this.isAdministrador = isAdministrador;
	}	
	
}
