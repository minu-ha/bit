package kr.kro.faitmain.live.domain;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;

@Getter @Setter
public class ChatRoom {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    public static ChatRoom create(String name){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.name = name;
        return chatRoom;
    }

    public void handleMessage(WebSocketSession session, ChatMessage chatMessage,
                              ObjectMapper objectMapper) throws IOException {
        if(chatMessage.getType() == MessageType.ENTER){
            sessions.add(session);
            chatMessage.setMessage(chatMessage.getWriter() + "¥‘¿Ã ¿‘¿Â«œºÃΩ¿¥œ¥Ÿ");
        }
        else if(chatMessage.getType() == MessageType.LEAVE){
            sessions.remove(session);
            chatMessage.setMessage(chatMessage.getWriter() + "¥‘¿Ã ≥™∞°ºÃΩ¿¥œ¥Ÿ");
        }
        else{
            chatMessage.setMessage(chatMessage.getWriter() + " : " + chatMessage.getMessage());
        }
        send(chatMessage,objectMapper);
    }

    private void send(ChatMessage chatMessage, ObjectMapper objectMapper) throws IOException {
        TextMessage textMessage = new TextMessage(objectMapper.
                                    writeValueAsString(chatMessage.getMessage()));
        for(WebSocketSession sess : sessions){
            sess.sendMessage(textMessage);
        }
    }
}