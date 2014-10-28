package br.com.fiap.financas.vo;

public class ItemGasto {

	private int iconeCor;
	private String titulo;
	private String valor;
	private String data;
	private String tipoGasto;
	private String gasto;
	private int iconeGasto;
	private int iconeTipoGasto;
	private int iconeData;

	public ItemGasto() {
		this(-1, "", "", "", "", "", -1, -1, -1);
	}

	public ItemGasto(int iconeCor, String titulo, String valor, String data,
			String tipoGasto, String gasto, int iconeGasto, int iconeTipoGasto,
			int iconeData) {
		this.iconeCor = iconeCor;
		this.titulo = titulo;
		this.valor = valor;
		this.data = data;
		this.tipoGasto = tipoGasto;
		this.gasto = gasto;
		this.iconeGasto = iconeGasto;
		this.iconeTipoGasto = iconeTipoGasto;
		this.iconeData = iconeData;
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

	public String getTipoGasto() {
		return tipoGasto;
	}

	public void setTipoGasto(String tipoGasto) {
		this.tipoGasto = tipoGasto;
	}

	public String getGasto() {
		return gasto;
	}

	public void setGasto(String gasto) {
		this.gasto = gasto;
	}

	public int getIconeGasto() {
		return iconeGasto;
	}

	public void setIconeGasto(int iconeGasto) {
		this.iconeGasto = iconeGasto;
	}

	public int getIconeTipoGasto() {
		return iconeTipoGasto;
	}

	public void setIconeTipoGasto(int iconeTipoGasto) {
		this.iconeTipoGasto = iconeTipoGasto;
	}

	public int getIconeData() {
		return iconeData;
	}

	public void setIconeData(int iconeData) {
		this.iconeData = iconeData;
	}

}
