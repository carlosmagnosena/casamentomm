package br.com.magno.casamentomm.infraestrutura;

import org.jinstagram.auth.oauth.InstagramService;

import br.com.magno.casamentomm.excecao.ExcecaoDaAplicacao;

public interface IConexaoInstagram {

	public InstagramService obterServicoInstagram() throws ExcecaoDaAplicacao;
}
