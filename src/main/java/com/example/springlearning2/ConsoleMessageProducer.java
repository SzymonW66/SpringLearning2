package com.example.springlearning2;

import java.util.Scanner;

public class ConsoleMessageProducer implements MessageProducer{

    //wstrzyknięcie za pomocą @Override?
    @Override
    public String getMessage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text to print:");
        return scanner.nextLine();
    }

}
