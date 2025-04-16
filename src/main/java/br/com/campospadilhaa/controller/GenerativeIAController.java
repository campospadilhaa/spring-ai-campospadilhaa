package br.com.campospadilhaa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.campospadilhaa.service.ChatService;
import br.com.campospadilhaa.service.RecipeService;

@RestController
public class GenerativeIAController {

	private final ChatService chatService;
	private final RecipeService recipeService;

	public GenerativeIAController(ChatService chatService, RecipeService recipeService) {
		this.chatService = chatService;
		this.recipeService = recipeService;
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
								   @RequestParam(defaultValue = "none") String dietaryRestrictions) {

		return recipeService.createRecipe(ingredients, cuisine, dietaryRestrictions);
	}
}