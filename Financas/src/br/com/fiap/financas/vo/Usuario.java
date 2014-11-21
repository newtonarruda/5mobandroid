package br.com.fiap.financas.vo;

import java.io.Serializable;

public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1578418401304728690L;
	private Integer idUsuario;
	private String nome;
	private String usuario;
	private String senha;
	private String email;
	private String telefone;
	private boolean flagAtivo;

	public Usuario(Integer idUsuario, String nome, String usuario,
			String senha, String email, String telefone, boolean flagAtivo) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
		this.email = email;
		this.telefone = telefone;
		this.flagAtivo = flagAtivo;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
