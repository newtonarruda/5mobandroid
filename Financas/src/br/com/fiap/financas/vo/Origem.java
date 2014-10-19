package br.com.fiap.financas.vo;

import java.io.Serializable;

public class Origem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -937891652866786251L;
	private Integer idOrigem;
	private String descricao;

	public Origem(Integer idOrigem, String descricao) {
		super();
		this.idOrigem = idOrigem;
		this.descricao = descricao;
	}

	public Integer getIdOrigem() {
		return idOrigem;
	}

	public void setIdOrigem(Integer idOrigem) {
		this.idOrigem = idOrigem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
