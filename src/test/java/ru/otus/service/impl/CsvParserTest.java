package ru.otus.service.impl;

import org.junit.jupiter.api.Test;
import ru.otus.domain.Question;
import ru.otus.service.Parser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvParserTest {

    @Test
    void parse() {
        Parser<List<Question>> parser = new CsvParser(";", "/questions.csv");
        List<Question> questions = parser.parse();

        assertEquals(questions.size(),5);
    }
}