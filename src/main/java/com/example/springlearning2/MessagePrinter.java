package com.example.springlearning2;

public class MessagePrinter {

    //wstrzykujemy poziom abstrakcji czyli w tym wypadku interfejs
    //wskazujemy że wykorzytujemy stopień abstrakcji, który gdzieś jest zaimplementowany
    private final MessageProducer messageProducer; //zmieniony typ

    public MessagePrinter(MessageProducer messageProducer) { //zmieniony typ
        this.messageProducer = messageProducer;
    }

    public void printMessage() {
        String message = messageProducer.getMessage();
        System.out.println(message);
    }
}
