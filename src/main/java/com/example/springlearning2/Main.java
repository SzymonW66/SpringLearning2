package com.example.springlearning2;

public class Main {
    SimpleMessageProducer messageProducer = new SimpleMessageProducer();
    MessagePrinter firstMessagePrinter = new MessagePrinter(messageProducer);
    MessagePrinter secondMessagePrinter = new MessagePrinter(messageProducer);
        firstMessagePrinter.printMessage();
        secondMessagePrinter.printMessage();
}
