package ru.otus.service.impl;

import lombok.Setter;
import lombok.extern.java.Log;

import ru.otus.domain.Question;
import ru.otus.exception.ParseException;
import ru.otus.service.Parser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log
public class CsvParser implements Parser<List<Question>> {

    private String separator;

    public CsvParser(String separator) {
        this.separator = separator;
    }

    public List<Question> parse(String filePath) {
        List<Question> questions = new ArrayList<>();
        List<String> fileLines;

        try {
            URI uri = this.getClass().getResource(filePath).toURI();
            fileLines = Files.readAllLines(Paths.get(uri));
        } catch (URISyntaxException | IOException e) {
            log.severe(e.getMessage());
            throw new ParseException(e);
        }

        //skip first line
        for (int i = 1; i < fileLines.size(); i++) {
            String line = fileLines.get(i);
            String[] splitedText = line.split(separator);
            ArrayList<String> columnList = new ArrayList<>(Arrays.asList(splitedText));
            String text = columnList.get(0);
            int correctAnswerNo = Integer.parseInt(columnList.get(1));
            String answer1 = columnList.get(2);
            String answer2 = columnList.get(3);
            String answer3 = columnList.get(4);
            String answer4 = columnList.get(5);
            String answer5 = columnList.get(6);
            Question question = new Question(text, correctAnswerNo,
                    Arrays.asList(answer1, answer2, answer3,
                    answer4, answer5));
            questions.add(question);
        }
        return questions;
    }

}
