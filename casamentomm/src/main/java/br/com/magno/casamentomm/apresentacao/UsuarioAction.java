package br.com.magno.casamentomm.apresentacao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.jinstagram.Instagram;
import org.jinstagram.auth.model.Token;
import org.jinstagram.auth.model.Verifier;
import org.jinstagram.auth.oauth.InstagramService;
import org.jinstagram.entity.tags.TagMediaFeed;
import org.jinstagram.entity.users.basicinfo.UserInfoData;
import org.jinstagram.entity.users.feed.MediaFeed;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.springframework.http.HttpRequest;

import br.com.magno.casamentomm.servicos.ServicosDoInstagram;

/**
 * Action relacionada ao usuario logado
 * 
 * @author Carlos
 *
 */
public class UsuarioAction extends AbstractAction {

	private static Instagram instagram;

	String nome;

	String imagemDoPerfil;

	String bio;

	int seguidoPor;

	int contagemDeImagens;

	String tag;

	ServicosDoInstagram servicoInstagram;

	List<String> imagensBaixaResolucao;

	public Instagram getInstagram() {
		return instagram;
	}

	public void setInstagram(Instagram instagram) {
		this.instagram = instagram;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImagemDoPerfil() {
		return imagemDoPerfil;
	}

	public void setImagemDoPerfil(String imagemDoPerfil) {
		this.imagemDoPerfil = imagemDoPerfil;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public int getSeguidoPor() {
		return seguidoPor;
	}

	public void setSeguidoPor(int seguidoPor) {
		this.seguidoPor = seguidoPor;
	}

	public int getContagemDeImagens() {
		return contagemDeImagens;
	}

	public void setContagemDeImagens(int contagemDeImagens) {
		this.contagemDeImagens = contagemDeImagens;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@SkipValidation
	public String login() throws javax.servlet.ServletException, IOException {

		String code = getHttpRequest().getParameter("code");
		// adicionarCodigo(code);
		obterInstagramAPartirDaSessao(code);
		// response.sendRedirect(request.getContextPath() + "/profile.jsp");

		return prepararPaginaDoUsuario();

	}

	private void obterInstagramAPartirDaSessao(String code) {
		InstagramService service = (InstagramService) getHttpRequest().getSession()
				.getAttribute(Constants.INSTAGRAM_SERVICE);
		Verifier verifier = new Verifier(code);

		Token accessToken = service.getAccessToken(null, verifier);
		instagram = new Instagram(accessToken);
	}

	@SkipValidation
	public String prepararPaginaDoUsuario() {
		if (instagram != null) {
			try {
				UserInfoData userInfoData = instagram.getCurrentUserInfo().getData();
				nome = userInfoData.getUsername();
				seguidoPor = userInfoData.getCounts().getFollowedBy();
				contagemDeImagens = userInfoData.getCounts().getMedia();
				imagemDoPerfil = userInfoData.getProfilePicture();
				// getSessaoDoUsuario().setAttribute(Constants.INSTAGRAM_OBJECT,
				// instagram);
				return SUCESSO;
			} catch (Exception e) {
				return ERRO;
			}

		} else {
			return ERRO;
		}

	}

	@SkipValidation
	public String prepararTag() {
		tag = Constants.HASHTAG;

		return SUCESSO;

	}

	@SkipValidation
	public String carregarImagens() {
		if (instagram != null) {
			try {
				servicoInstagram = new ServicosDoInstagram(instagram);
				servicoInstagram.obterImagensMarcadasComTag(tag);
				return SUCESSO;

			} catch (Exception ex)

			{
				return ERRO;
			}
		}
		return ERRO;
	}

	public ServicosDoInstagram getServicoInstagram() {
		return servicoInstagram;
	}

	public void setServicoInstagram(ServicosDoInstagram servicoInstagram) {
		this.servicoInstagram = servicoInstagram;
	}

	public List<String> getImagensBaixaResolucao() {
		return imagensBaixaResolucao;
	}

	public void setImagensBaixaResolucao(List<String> imagensBaixaResolucao) {
		this.imagensBaixaResolucao = imagensBaixaResolucao;
	}

}
