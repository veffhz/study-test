package ru.otus.service.impl;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import ru.otus.domain.Question;
import ru.otus.service.Parser;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
@TestPropertySource("classpath:application-test.yml")
class CsvParserTest {

    @Autowired
    private MessageSourceWrapperService adapter;

    @Autowired
    private Parser<List<Question>> parser;

    @Test
    void parse() {
        List<Question> questions = parser.parse();
        assertEquals(questions.size(),5);
    }

}