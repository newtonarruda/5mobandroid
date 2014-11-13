package br.com.fiap.financas.vo;

public class MensagemBean {

	private String from, message;

	public MensagemBean(String from, String message) {
		super();
		this.from = from;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

}
