package ru.gnidenko.chatservice.mapper;

import org.mapstruct.Mapper;
import ru.gnidenko.chatservice.dto.ChatDto;
import ru.gnidenko.chatservice.entity.Chat;

@Mapper(componentModel = "spring")
public interface ChatMapper {

    Chat chatDtoToChat(ChatDto chatDto);
    ChatDto chatToChatDto(Chat chat);

}
