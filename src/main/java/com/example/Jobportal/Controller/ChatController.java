package com.example.Jobportal.Controller;


import com.example.Jobportal.Model.MessageEntity;
import com.example.Jobportal.Repository.MessageRepository;
import com.example.Jobportal.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ChatController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate; // Add this line

    @MessageMapping("/chat")
    public void sendMessage(MessageEntity message) {
        message.setTimestamp(LocalDateTime.now());
        messageRepository.save(message); // Save the message in the database

        // Send message to both sender and receiver
        messagingTemplate.convertAndSend("/topic/messages/" + message.getReceiver(), message);
        messagingTemplate.convertAndSend("/topic/messages/" + message.getSender(), message);
    }

    @GetMapping("/api/messages")
    public List<MessageEntity> getMessages(
            @RequestParam String sender,
            @RequestParam String receiver) {
        return messageRepository.findMessages(sender, receiver);
    }
}
