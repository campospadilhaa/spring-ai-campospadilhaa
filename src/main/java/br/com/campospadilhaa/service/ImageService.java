package br.com.campospadilhaa.service;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

	private final OpenAiImageModel openAiImageModel;

	public ImageService(OpenAiImageModel openAiImageModel) {
		this.openAiImageModel = openAiImageModel;
	}

	public ImageResponse generateImage(String promptStr,
			String quality,
			Integer quantidade,
			Integer altura,
			Integer largura
		) {

		/* Substituída esta forma simplificada, que já funcionaria, mas o ideal é utilizar 'options' para melhorar a pesquisa 
		ImageResponse imageResponse = openAiImageModel.call(new ImagePrompt(promptStr)); /**/

		/* codigoa abaixo substituído para parametrizar os 'options'
		ImageResponse imageResponse = openAiImageModel.call(
				new ImagePrompt(promptStr,
						OpenAiImageOptions.builder()

							// qualidade da imagem
							.quality("hd")

							// quantidad de imagens a serem geradas
							.N(4)

							// tamanho da imagem
							.height(1024)
							.width(1024)
							.build()
						)
				); /**/

		/**/
		ImageResponse imageResponse = openAiImageModel.call(
				new ImagePrompt(promptStr,
						OpenAiImageOptions.builder()

							// qualidade da imagem
							.quality(quality)

							// quantidad de imagens a serem geradas
							.N(quantidade)

							// tamanho da imagem
							.height(altura)
							.width(largura)

							.build()
						)
				); /**/

		return imageResponse;
	}
}