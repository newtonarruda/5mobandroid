package br.com.fiap.financas.vo;

import java.io.Serializable;

public class Categoria implements Serializable {

	private static final long serialVersionUID = -3520198485095340639L;
	private Integer idCategoria;
	private String descricao;

	public Categoria(Integer idCategoria, String descricao) {
		super();
		this.idCategoria = idCategoria;
		this.descricao = descricao;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
