package ru.gnidenko.chatservice.repo;

import org.springframework.stereotype.Repository;
import ru.gnidenko.chatservice.entity.Message;

import java.util.List;

@Repository
public interface MessageRepo {
    List<Message> findByChatId(String chatId);
}
