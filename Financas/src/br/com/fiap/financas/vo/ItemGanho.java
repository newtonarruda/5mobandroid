package br.com.fiap.financas.vo;

public class ItemGanho {

	private int iconeCor;
	private String titulo;
	private String valor;
	private String data;
	private String saldo;
	private int iconeSaldo;

	public ItemGanho() {
		this(-1, "", "", "", "", -1);
	}

	public ItemGanho(int iconeCor, String titulo, String valor, String data,
			String saldo, int iconeSaldo) {
		this.iconeCor = iconeCor;
		this.titulo = titulo;
		this.valor = valor;
		this.data = data;
		this.saldo = saldo;
		this.iconeSaldo = iconeSaldo;
	}

	public int getIconeCor() {
		return iconeCor;
	}

	public void setIconeCor(int iconeCor) {
		this.iconeCor = iconeCor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	public int getIconeSaldo() {
		return iconeSaldo;
	}

	public void setIconeSaldo(int iconeSaldo) {
		this.iconeSaldo = iconeSaldo;
	}

}
