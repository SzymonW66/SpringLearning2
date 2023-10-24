package com.example.springlearning2;

public class MessagePrinter {
    SimpleMessageProducer simpleMessageProducer = new SimpleMessageProducer();


    //wstrzykiwanie przez konstruktor
    public MessagePrinter(SimpleMessageProducer simpleMessageProducer) {
        this.simpleMessageProducer = simpleMessageProducer;
    }

    public void printMessage(){
        String message = simpleMessageProducer.getMessage();
        System.out.println(message);
    }
}
