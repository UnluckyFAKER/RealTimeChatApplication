package com.moulik.ChatServer.Controller;

import com.moulik.ChatServer.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/message") // app/message
    @SendTo("/chatroom/public")
    public Message receiveMessage(@Payload Message message) {

        return message;
    }

    @MessageMapping("/private-message")
    public Message receivePrivateMessage(@Payload Message message) {
       template.convertAndSendToUser(message.getReceiverName(), "/private", message); // user/Davis/private
        return message;

    }

}
