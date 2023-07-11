package ru.meowhard.preproject23.controller;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;

@Controller
public class WebSocketController {

    @MessageMapping("/request")
    @SendTo("/topic/request")
    public String send() {
        System.out.println("Send message successful");
        return "Send message successful";
    }


}
