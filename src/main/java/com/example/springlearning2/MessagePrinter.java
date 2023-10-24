package com.example.springlearning2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessagePrinter {
   // @Autowired może być ale nie musi, raczej nie dawać i wstrzykiwać przez konstruktor
    private final MessageProducer messageProducer;

    public MessagePrinter(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    public void printMessage() {
        String message = messageProducer.getMessage();
        System.out.println(message);
    }
}
