package com.skool.zerotoprincipal.ThinkLikeAProgrammer.retrofit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private final LocalChatService chatService;

    @Autowired
    public ChatController(LocalChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/completion")
    public String getChatCompletion() {
        return chatService.getChatCompletion();
    }
}
