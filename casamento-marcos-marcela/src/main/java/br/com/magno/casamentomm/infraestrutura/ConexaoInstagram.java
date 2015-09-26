package br.com.magno.casamentomm.infraestrutura;

import java.util.Properties;

import org.jinstagram.auth.InstagramAuthService;
import org.jinstagram.auth.oauth.InstagramService;

import br.com.magno.casamentomm.excecao.ExcecaoDaAplicacao;

public class ConexaoInstagram implements IConexaoInstagram {

	private String clientId;
	private String clientSecret;
	private String validCallback;

	public ConexaoInstagram() {
		popularPropriedadesAPartirDeArquivo();
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getValidCallback() {
		return validCallback;
	}

	public void setValidCallback(String validCallback) {
		this.validCallback = validCallback;
	}

	public InstagramService obterServicoInstagram() throws ExcecaoDaAplicacao {

		try {
			InstagramService servico = new InstagramAuthService().apiKey(clientId).apiSecret(clientSecret)
					.callback(validCallback).build();
			return servico;

		} catch (Exception e) {
			throw new ExcecaoDaAplicacao("Falha ao estabelecer conexao com Instagram");

		}

	}

	private void popularPropriedadesAPartirDeArquivo() {

		Properties prop = new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream("conexao.properties"));
			clientId = prop.getProperty(Constantes.CLIENT_ID);
			clientSecret = prop.getProperty(Constantes.CLIENT_SECRET);
			validCallback = prop.getProperty(Constantes.CALLBACK);

		} catch (Exception e) {
			System.out.println("Erro ao obter propriedades de conexão");
		}
	}

}
