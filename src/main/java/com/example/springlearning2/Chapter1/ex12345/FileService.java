package com.example.springlearning2.Chapter1.ex12345;

import com.example.springlearning2.Chapter1.ex12345.cipher.CipherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
@Service
class FileService {
    private final String fileName;
    private final CipherService cipherService;

    public FileService(@Value("${app.filename}") String fileName, CipherService cipherService) {
        this.fileName = fileName;
        this.cipherService = cipherService;
    }

    List<Entry> readAllFile() throws IOException {
        return Files.readAllLines(Paths.get(fileName))
                .stream()
                .map(cipherService::decrypt)
                .map(CsvEntryConverter::parse)
                .collect(Collectors.toList());
    }

    void saveEntries(List<Entry> entries) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (Entry entry : entries) {
            writer.write(cipherService.encrypt(entry.toString()));
            writer.newLine();
        }
        writer.close();
    }

    private static class CsvEntryConverter {
        static Entry parse(String text) {
            String[] split = text.split(";");
            return new Entry(split[0], split[1]);
        }
    }
}
