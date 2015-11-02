package br.com.magno.casamentomm.apresentacao;

import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.jinstagram.auth.InstagramAuthService;
import org.jinstagram.auth.oauth.InstagramService;
import org.springframework.http.HttpRequest;

/**
 * Action relacionada à pagina inicial da aplicacao
 * 
 * @author Carlos
 *
 */
public class HomeAction extends AbstractAction {

	private String authorizationUrl;

	public String getAuthorizationUrl() {
		return authorizationUrl;
	}

	public void setAuthorizationUrl(String authorizationUrl) {
		this.authorizationUrl = authorizationUrl;
	}

	@SkipValidation
	public String exibir() {

		Object objInstagram = getSessaoDoUsuario().getAttribute(Constants.INSTAGRAM_OBJECT);
		if (objInstagram != null) {
			return "redirecionarParaPaginaDoUsuario";
		}

		Properties properties = InstagramUtils.getConfigProperties();

		String clientId = properties.getProperty(Constants.CLIENT_ID);
		String clientSecret = properties.getProperty(Constants.CLIENT_SECRET);
		String callbackUrl = properties.getProperty(Constants.REDIRECT_URI);

		InstagramService service = new InstagramAuthService().apiKey(clientId).apiSecret(clientSecret)
				.callback(callbackUrl).build();

		authorizationUrl = service.getAuthorizationUrl(null);
		getSessaoDoUsuario().setAttribute(Constants.INSTAGRAM_SERVICE, service);

		return SUCCESS;
	}
}
