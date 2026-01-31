package ru.gnidenko.chatservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gnidenko.chatservice.dto.ChatDto;
import ru.gnidenko.chatservice.dto.MessageDto;
import ru.gnidenko.chatservice.entity.Message;
import ru.gnidenko.chatservice.service.ChatService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chats")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<ChatDto> enterChat(@RequestBody String username){
        return ResponseEntity.ok(chatService.enterChat(username));
    }

    @GetMapping("/{chatId}/messages")
    public ResponseEntity<List<MessageDto>> getAllMessagesInChat(@PathVariable String chatId){
        return ResponseEntity.ok(chatService.getAllMessages(chatId));
    }

    @PostMapping("/{chatId}/messages")
    public ResponseEntity<Void> sendMessage(@PathVariable String chatId, @RequestBody MessageDto message){
        chatService.sendMessage(chatId, message);
        return ResponseEntity.ok().build();
    }
}
