package br.com.fiap.financas.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Movimentacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1578418401304728690L;

	private Integer idMovimentacao;
	private String titulo;
	private Integer tipoMovimentacao;
	private Date data;
	private BigDecimal valorTotal;
	private BigDecimal valorParcial;
	private String latLong;
	private boolean flag_efetivada;

	private Origem origem;
	private Usuario usuario;

	public Movimentacao(Integer idMovimentacao, String titulo,
			Integer tipoMovimentacao, Date data, BigDecimal valorTotal,
			BigDecimal valorParcial, String latLong, boolean flag_efetivada,
			Origem origem, Usuario usuario) {
		super();
		this.idMovimentacao = idMovimentacao;
		this.titulo = titulo;
		this.tipoMovimentacao = tipoMovimentacao;
		this.data = data;
		this.valorTotal = valorTotal;
		this.valorParcial = valorParcial;
		this.latLong = latLong;
		this.flag_efetivada = flag_efetivada;
		this.origem = origem;
		this.usuario = usuario;
	}

	public Integer getIdMovimentacao() {
		return idMovimentacao;
	}

	public void setIdMovimentacao(Integer idMovimentacao) {
		this.idMovimentacao = idMovimentacao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(Integer tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorParcial() {
		return valorParcial;
	}

	public void setValorParcial(BigDecimal valorParcial) {
		this.valorParcial = valorParcial;
	}

	public String getLatLong() {
		return latLong;
	}

	public void setLatLong(String latLong) {
		this.latLong = latLong;
	}

	public boolean isFlag_efetivada() {
		return flag_efetivada;
	}

	public void setFlag_efetivada(boolean flag_efetivada) {
		this.flag_efetivada = flag_efetivada;
	}

	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
