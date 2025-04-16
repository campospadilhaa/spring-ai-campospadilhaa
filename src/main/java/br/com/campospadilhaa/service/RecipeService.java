package br.com.campospadilhaa.service;

import java.util.Map;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

	private final ChatModel chatModel;

	public RecipeService(ChatModel chatModel) {
		this.chatModel = chatModel;
	}

	public String createRecipe(String ingredients, String cuisine, String dietaryRestrictions) {

		// criação do template que receberá os dados variáveis: ingredients, cuisine e dietaryRestrictions
		var template = """
					Quero criar uma receita usando os seguintes ingredientes: {ingredients}
					O tipo de culinária que prefiro é {cuisine}.
					Por favor, considere as seguintes restrições alimentares: {dietaryRestrictions}.
					E ainda, envie-me uma receita detalhada, incluindo título, lista de ingredientes e instruções de preparo.
				""";

		PromptTemplate promptTemplate = new PromptTemplate(template);

		// atribuição dos valores às variáveis
		Map<String, Object> listaParams = Map.of(
				"ingredients", ingredients,
				"cuisine", cuisine,
				"dietaryRestrictions", dietaryRestrictions);

		// criação do prompt que será pesquisado
		Prompt prompt = promptTemplate.create(listaParams);

		// execução da pesquisa
		ChatResponse chatResponse = chatModel.call(prompt);

		return chatResponse.getResult().getOutput().getText();
	}
}