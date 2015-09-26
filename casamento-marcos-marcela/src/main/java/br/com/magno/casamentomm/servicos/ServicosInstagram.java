package br.com.magno.casamentomm.servicos;

import java.util.ArrayList;
import java.util.List;

import org.jinstagram.Instagram;
import org.jinstagram.auth.model.Token;
import org.jinstagram.auth.model.Verifier;
import org.jinstagram.auth.oauth.InstagramService;
import org.jinstagram.entity.tags.TagInfoData;
import org.jinstagram.entity.tags.TagInfoFeed;
import org.jinstagram.entity.tags.TagMediaFeed;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.jinstagram.exceptions.InstagramException;

import br.com.magno.casamentomm.infraestrutura.ConexaoHttp;
import br.com.magno.casamentomm.infraestrutura.ConexaoInstagram;
import br.com.magno.casamentomm.infraestrutura.IConexaoInstagram;

public class ServicosInstagram {

	private static final Token EMPTY_TOKEN = null;

	private Token tokenDeAcesso;

	private IConexaoInstagram conexaoDoInstagram;

	private InstagramService service;

	private ConexaoHttp conexaoHttp;

	private Instagram instagram;

	public ServicosInstagram() {
		conexaoDoInstagram = new ConexaoInstagram();
		conexaoHttp = new ConexaoHttp();
		acessarInstagram();

	}

	private void acessarInstagram() {
		try {
			service = conexaoDoInstagram.obterServicoInstagram();
			if (service != null) {
				obterTokenDeAcesso();
				instagram = new Instagram(tokenDeAcesso);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void obterTokenDeAcesso() throws Exception {
		String authorizationUrl = service.getAuthorizationUrl(EMPTY_TOKEN);

		// conexaoHttp.obterCodigoDoUsuario(authorizationUrl);
		Verifier verifier = new Verifier("76099efb96154ae6ad62ccfcae657fc7");
		tokenDeAcesso = service.getAccessToken(EMPTY_TOKEN, verifier);

	}

	public long obterNumeroDeMidiasAssociadasAHashtag(String tag) throws InstagramException {
		TagInfoFeed feed = instagram.getTagInfo(tag);

		TagInfoData tagData = feed.getTagInfo();

		return tagData.getMediaCount();

	}

	public List<String> obterUrlDeImagensAssociadasAHashTag(String tag) throws InstagramException {
		List<String> urlsDeImagens = new ArrayList<String>();
		TagMediaFeed mediaFeed = instagram.getRecentMediaTags(tag);

		List<MediaFeedData> mediaFeeds = mediaFeed.getData();
		for (MediaFeedData mediaFeedData : mediaFeeds) {
			// System.out.println("Link: " + mediaFeedData.getLink());
			// System.out.println("Imagem : " +
			// mediaFeedData.getImages().getStandardResolution().getImageUrl());
			urlsDeImagens.add(mediaFeedData.getImages().getStandardResolution().getImageUrl());
		}
		return urlsDeImagens;

	}

}
