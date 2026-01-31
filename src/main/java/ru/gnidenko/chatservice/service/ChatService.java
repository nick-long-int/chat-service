package ru.gnidenko.chatservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gnidenko.chatservice.dto.ChatDto;
import ru.gnidenko.chatservice.dto.MessageDto;
import ru.gnidenko.chatservice.entity.Chat;
import ru.gnidenko.chatservice.entity.Message;
import ru.gnidenko.chatservice.entity.MessageType;
import ru.gnidenko.chatservice.mapper.ChatMapper;
import ru.gnidenko.chatservice.mapper.MessageMapper;
import ru.gnidenko.chatservice.repo.ChatRepo;
import ru.gnidenko.chatservice.repo.MessageRepo;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepo chatRepo;
    private final MessageRepo messageRepo;
    private final ChatMapper chatMapper;
    private final MessageMapper messageMapper;

    @Transactional
    public ChatDto enterChat(String username) {
        Optional<Chat> chat = chatRepo.findByUsername(username);
        if (chat.isEmpty()) {
            Chat newChat = new Chat();
            newChat.setUsername(username);
            newChat.setCreatedAt(LocalDateTime.now());
            newChat.setUpdatedAt(LocalDateTime.now());
            return chatMapper.chatToChatDto(chatRepo.save(newChat));
        }

        return chatMapper.chatToChatDto(chat.get());
    }

    @Transactional(readOnly = true)
    public List<MessageDto> getAllMessages(String chatId) {
        List<Message> messages = messageRepo.findByChatId(chatId);
        if (messages.isEmpty()) {
            return new ArrayList<>();
        }
        return messages.stream()
            .map(messageMapper::messageToMessageDto)
            .toList();
    }

    @Transactional
    public void sendMessage(String chatId, MessageDto message) {
        Message newMessage = messageMapper.messageDtoToMessage(message);
        newMessage.setChatId(chatId);
        newMessage.setType(MessageType.TEXT);
        //todo через кафку обработку сообщений
        messageRepo.save(newMessage);
    }
}
