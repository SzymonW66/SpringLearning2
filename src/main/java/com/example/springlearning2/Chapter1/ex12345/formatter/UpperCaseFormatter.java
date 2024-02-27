package com.example.springlearning2.Chapter1.ex12345.formatter;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary //oznaczenie że tą implemetacje ma wykorzystać jako pierwszą
public class UpperCaseFormatter implements TextFormatter{
    @Override
    public String format(String originalText) {
        return originalText.toUpperCase();
    }
}
