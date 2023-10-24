package com.example.springlearning2;

public class Main {
    public static void main(String[] args) {
        //poniżej jest utworzenie obiektu ConsoleMessageProducer, który implemetuje interface
        //MessageProducer. Klasy ConsoleMessProducer oraz SimpleMessageProducer są bardzo podobne dlatego
        //mają wspólny interface który implementuje tą samą metodę, ale z różną logiką w środku tej metody
        //
        MessageProducer messageProducer = new ConsoleMessageProducer();
        //Wykorzystujemy logikę z ConsoleMessageProducer w MessagePrinterze
        MessageProducer messageProducer1 = new SimpleMessageProducer();
        //mogę wykorzystać jedną z dwóch implementacji interfejsu MessagePrinter
        MessagePrinter messagePrinter = new MessagePrinter(messageProducer);
        MessagePrinter messagePrinter1 = new MessagePrinter(messageProducer1);
        messagePrinter.printMessage();
        messagePrinter1.printMessage();
    }
}
