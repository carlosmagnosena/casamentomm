package br.com.magno.casamentomm.servicos;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import org.jinstagram.Instagram;
import org.jinstagram.entity.tags.TagMediaFeed;
import org.jinstagram.entity.users.feed.MediaFeed;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.jinstagram.exceptions.InstagramException;
import org.springframework.stereotype.Service;

import br.com.magno.casamentomm.apresentacao.Constants;

@Service
public class ServicosDoInstagram {

	public ServicosDoInstagram(Instagram instagram) {
		urlsJaAdicionadas = new ArrayList<String>();
		this.instagram = instagram;
	}

	Instagram instagram;
	int contadorDeImagens;
	List<String> urlsJaAdicionadas;

	public void obterImagensMarcadasComTag(String tag) throws InterruptedException, InstagramException {

		List<String> urlsParaBaixar = new ArrayList<String>();
		while (true) {
			Thread.sleep(10000);

			System.out.println("Total de imagens baixadas até o momento: " + contadorDeImagens);
			List<MediaFeedData> mediaList = null;
			// int mediaCount = 0;
			// imagensBaixaResolucao = new ArrayList<String>();
			TagMediaFeed tagMediaFeed = null;
			if (tag != null) {
				tagMediaFeed = instagram.getRecentMediaTags(tag);

				mediaList = tagMediaFeed.getData();

				MediaFeed recentMediaNextPage = instagram.getRecentMediaNextPage(tagMediaFeed.getPagination());
				int counter = 0;
				while (recentMediaNextPage.getPagination() != null && counter < Constants.MAX_PAGE_SIZE) {
					mediaList.addAll(recentMediaNextPage.getData());

					recentMediaNextPage = instagram.getRecentMediaNextPage(recentMediaNextPage.getPagination());

					counter++;
				}

				// / mediaCount = mediaList.size();

				// Obtem as imagens

				for (MediaFeedData mediaFeedData : mediaList) {

					// imagensBaixaResolucao.add(mediaFeedData.getImages().getLowResolution().getImageUrl());
					urlsParaBaixar.add(mediaFeedData.getImages().getStandardResolution().getImageUrl());
				}
				fazerDownloadDeImagens(urlsParaBaixar);
			}
		}

	}

	public void fazerDownloadDeImagens(List<String> urlsDeImagens) throws InterruptedException {

		BufferedImage image = null;
		try {

			for (String urlDaImagem : urlsDeImagens) {
				if (!urlsJaAdicionadas.contains(urlDaImagem)) {
					urlsJaAdicionadas.add(urlDaImagem);
					URL url = new URL(urlDaImagem);
					image = ImageIO.read(url);

					ImageIO.write(image, "jpg", new File(Constants.LOCAL_IMAGENS + Constants.PREFIXO_FOTO
							+ String.valueOf(contadorDeImagens++) + Constants.FORMATO));
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
