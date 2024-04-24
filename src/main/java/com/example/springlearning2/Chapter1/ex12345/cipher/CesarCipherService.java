package com.example.springlearning2.Chapter1.ex12345.cipher;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("prod")
@Service
public class CesarCipherService implements CipherService {
    private static final int SHIFT = 3;

    @Override
    public String encrypt(String text) {
        return text.chars()
                .map(CesarCipherService::shift)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Override
    public String decrypt(String cipher) {
        return cipher.chars()
                .map(CesarCipherService::shiftBack)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private static int shift(int character) {
        return character + SHIFT;
    }

    private static int shiftBack(int character) {
        return character - SHIFT;
    }
}
