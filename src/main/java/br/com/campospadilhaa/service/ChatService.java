package br.com.campospadilhaa.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

	private final ChatModel chatModel;

	public ChatService(ChatModel chatModel) {
		this.chatModel = chatModel;
	}

	public String getResponse(String prompt) {

		return chatModel.call(prompt);
	}

	public String getResponseWithOptions(String promptStr) {

		ChatResponse chatResponse = chatModel.call(
				new Prompt(
						promptStr,
						OpenAiChatOptions.builder()

							// informa qual o modelo de dados que será utilizado
							.model("gpt-4o")

							// quanto maior este número mais criativo será a AI
							.temperature(0.4)

							.build()
						));

		return chatResponse.getResult().getOutput().getText();
	}
}