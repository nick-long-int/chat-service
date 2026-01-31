package ru.gnidenko.chatservice.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.gnidenko.chatservice.entity.Message;

import java.util.List;

@Repository
public interface MessageRepo extends MongoRepository<Message, String> {
    List<Message> findByChatId(String chatId);
}
