package br.com.magno.casamentomm.excecao;

public class ExcecaoDaAplicacao extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mensagem;

	public ExcecaoDaAplicacao() {
		super();
	}

	public ExcecaoDaAplicacao(String mensagem) {
		super();
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
