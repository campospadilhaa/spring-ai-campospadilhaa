package br.com.campospadilhaa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.campospadilhaa.service.ChatService;

@RestController
public class GenerativeIAController {

	private final ChatService chatService;

	public GenerativeIAController(ChatService chatService) {
		this.chatService = chatService;
	}

	@GetMapping("ask-ai")
	public String getResponse(@RequestParam String prompt) {

		return chatService.getResponse(prompt);
	}

	@GetMapping("ask-ai-options")
	public String getResponseWithOptions(@RequestParam String prompt) {

		return chatService.getResponseWithOptions(prompt);
	}
}