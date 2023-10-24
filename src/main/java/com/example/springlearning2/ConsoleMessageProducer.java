package com.example.springlearning2;

import org.springframework.stereotype.Service;

import java.util.Scanner;
@Service
public class ConsoleMessageProducer implements MessageProducer{

    @Override
    public String getMessage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text to print:");
        return scanner.nextLine();
    }

}
