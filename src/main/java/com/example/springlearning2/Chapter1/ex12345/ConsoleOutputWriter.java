package com.example.springlearning2.Chapter1.ex12345;

import com.example.springlearning2.Chapter1.ex12345.formatter.TextFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsoleOutputWriter {
    private final TextFormatter textFormatter;

    @Autowired
    public ConsoleOutputWriter(TextFormatter textFormatter) {
        this.textFormatter = textFormatter;
    }

    void println(String text) {
        String formattedText = textFormatter.format(text);
        System.out.println(formattedText);
    }


}
