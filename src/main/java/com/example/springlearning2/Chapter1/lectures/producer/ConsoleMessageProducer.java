package com.example.springlearning2.Chapter1.lectures.producer;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Scanner;
@Service
@Profile("console")
public class ConsoleMessageProducer implements com.example.springlearning2.Chapter1.lectures.producer.MessageProducer {

    private final Scanner scanner;

    public ConsoleMessageProducer(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String getMessage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text to print:");
        return scanner.nextLine();
    }

}
