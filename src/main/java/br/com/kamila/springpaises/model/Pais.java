package br.com.kamila.springpaises.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_PAIS")
public class Pais implements Serializable {
	
	private static final long serialVersionUID = 6572874875868262909L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "sigla")
	private String sigla;
	
	@Column(name = "gentilico")
	private String gentilico;
	
	public Pais() {};
	
	public Pais(String nome, String sigla, String gentilico) {
		this.nome = nome;
		this.sigla = sigla;
		this.gentilico = gentilico;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getGentilico() {
		return gentilico;
	}
	public void setGentilico(String gentilico) {
		this.gentilico = gentilico;
	}

}
