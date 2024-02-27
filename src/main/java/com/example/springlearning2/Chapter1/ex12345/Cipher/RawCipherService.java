package com.example.springlearning2.Chapter1.ex12345.Cipher;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
@Profile("dev")
@Service
public class RawCipherService implements CipherService {
    @Override
    public String encrypt(String text) {
        return text;
    }

    @Override
    public String decrypt(String cipher) {
        return cipher;
    }
}
