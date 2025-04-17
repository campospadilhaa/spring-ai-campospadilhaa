package br.com.campospadilhaa.controller;

import java.util.List;

import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.campospadilhaa.service.ChatService;
import br.com.campospadilhaa.service.ImageService;
import br.com.campospadilhaa.service.RecipeService;

@RestController
@RequestMapping("ai")
public class GenerativeIAController {

	private final ChatService chatService;
	private final RecipeService recipeService;
	private final ImageService imageService;

	public GenerativeIAController(ChatService chatService, RecipeService recipeService, ImageService imageService) {
		this.chatService = chatService;
		this.recipeService = recipeService;
		this.imageService = imageService;
	}

	@GetMapping("ask-ai")
	public String getResponse(@RequestParam String prompt) {

		return chatService.getResponse(prompt);
	}

	@GetMapping("ask-ai-options")
	public String getResponseWithOptions(@RequestParam String prompt) {

		return chatService.getResponseWithOptions(prompt);
	}

	@GetMapping("recipe-creator")
	public String getRecipeCreator(@RequestParam String ingredients,

								   // any: qualquer cosinha do mundo
								   @RequestParam(defaultValue = "any") String cuisine,

								   // none: não há restrição
								   @RequestParam(defaultValue = "none") String dietaryRestrictions
			) {

		return recipeService.createRecipe(ingredients, cuisine, dietaryRestrictions);
	}

	@GetMapping("generate-image")
	public List<String> getGenerateImages(@RequestParam String prompt,

								   @RequestParam(defaultValue = "hd") String quality,
								   @RequestParam(defaultValue = "1") Integer quantidade,
								   @RequestParam(defaultValue = "1024") Integer altura,
								   @RequestParam(defaultValue = "1024") Integer largura
			) {

		// obtém o ImageResponse contendo a URL das imagens
		ImageResponse imageResponse = imageService.generateImage(prompt, quality, quantidade, altura, largura);

		// carrega a lista de imagens
		List<String> listaImageUrls = imageResponse.getResults().stream()

					.map(image -> image.getOutput().getUrl())

				.toList();

		return listaImageUrls;
	}
}