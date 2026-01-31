package ru.gnidenko.chatservice.repo;

import ru.gnidenko.chatservice.entity.Message;

import java.util.List;

public interface MessageRepo {
    List<Message> findByChatId(String chatId);
}
