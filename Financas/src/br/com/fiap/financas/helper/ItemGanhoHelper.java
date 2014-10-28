package br.com.fiap.financas.helper;

import android.widget.ImageView;
import android.widget.TextView;

public class ItemGanhoHelper {

	private ImageView itemGanhosImage;
	private TextView tituloText;
	private TextView dataText;
	private TextView valorText;
	private TextView saldoText;
	private ImageView saldoImage;

	public ImageView getItemGanhosImage() {
		return itemGanhosImage;
	}

	public void setItemGanhosImage(ImageView itemGanhosImage) {
		this.itemGanhosImage = itemGanhosImage;
	}

	public TextView getTituloText() {
		return tituloText;
	}

	public void setTituloText(TextView tituloText) {
		this.tituloText = tituloText;
	}

	public TextView getDataText() {
		return dataText;
	}

	public void setDataText(TextView dataText) {
		this.dataText = dataText;
	}

	public TextView getValorText() {
		return valorText;
	}

	public void setValorText(TextView valorText) {
		this.valorText = valorText;
	}

	public TextView getSaldoText() {
		return saldoText;
	}

	public void setSaldoText(TextView saldoText) {
		this.saldoText = saldoText;
	}

	public ImageView getSaldoImage() {
		return saldoImage;
	}

	public void setSaldoImage(ImageView saldoImage) {
		this.saldoImage = saldoImage;
	}

}
