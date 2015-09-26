package br.com.magno.casamentomm.aplicacao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import org.jinstagram.exceptions.InstagramException;

import br.com.magno.casamentomm.infraestrutura.Constantes;
import br.com.magno.casamentomm.servicos.ServicosInstagram;

public class CasamentoMM {

	private static int contador = 0;

	private static Set<String> urlsJaAdicionadas = new HashSet<String>();

	public static void main(String[] args) {
		ServicosInstagram servico = new ServicosInstagram();
		try {
			long numero = servico.obterNumeroDeMidiasAssociadasAHashtag(Constantes.HASHTAG);
			System.out.println("Nome da Tag pesquisada : " + Constantes.HASHTAG);
			System.out.println("Quantidade de mídias associadas : " + numero);

			while (true) {
				Thread.sleep(5000);
				fazerDownloadDeImagens(servico.obterUrlDeImagensAssociadasAHashTag(Constantes.HASHTAG));

			}
		} catch (InstagramException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private static void fazerDownloadDeImagens(List<String> urlsDeImagens) {
		BufferedImage image = null;
		try {

			for (String urlDaImagem : urlsDeImagens) {
				if (!urlsJaAdicionadas.contains(urlDaImagem)) {
					URL url = new URL(urlDaImagem);
					image = ImageIO.read(url);

					ImageIO.write(image, "jpg", new File(Constantes.LOCAL_IMAGENS + Constantes.PREFIXO_FOTO
							+ String.valueOf(contador++) + Constantes.FORMATO));
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
