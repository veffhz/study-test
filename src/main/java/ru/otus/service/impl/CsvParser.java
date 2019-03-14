package ru.otus.service.impl;

import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.domain.Question;
import ru.otus.exception.ParseException;
import ru.otus.service.Parser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Log
@Service
public class CsvParser implements Parser<List<Question>> {

    private final String separator;
    private final String csvFile;
    private final MessageSourceWrapperService messageSourceWrapper;

    @Autowired
    public CsvParser(@Value("${separator}") String separator,
                     @Value("${csv.file}") String csvFile,
                     MessageSourceWrapperService messageSourceWrapper) {
        this.separator = separator;
        this.csvFile = csvFile;
        this.messageSourceWrapper = messageSourceWrapper;
    }

    public List<Question> parse() {
        List<Question> questions = new ArrayList<>();
        List<String> fileLines;

        try {
            String localeCsvFile = csvFile.replace("_.",
                    String.format("_%s.", messageSourceWrapper.getLocale()));
            URI uri = this.getClass().getResource(localeCsvFile).toURI();
            fileLines = Files.readAllLines(Paths.get(uri));

            //skip first line
            for (int i = 1; i < fileLines.size(); i++) {
                String line = fileLines.get(i);
                String[] splitedText = line.split(separator);
                ArrayList<String> columnList = new ArrayList<>(Arrays.asList(splitedText));
                String text = columnList.get(0);
                String correctAnswer = columnList.get(1);
                String answer1 = columnList.get(2);
                String answer2 = columnList.get(3);
                String answer3 = columnList.get(4);
                String answer4 = columnList.get(5);
                String answer5 = columnList.get(6);
                Question question = new Question(text, correctAnswer,
                        Arrays.asList(answer1, answer2, answer3, answer4, answer5));
                questions.add(question);
            }
        } catch (IndexOutOfBoundsException e) {
            log.warning(e.getMessage());
            throw new ParseException(e);
        } catch (URISyntaxException | IOException e) {
            log.severe(e.getMessage());
            throw new ParseException(e);
        }
        return questions;
    }

}
