package ru.otus.service.impl;

import org.junit.jupiter.api.Test;
import ru.otus.TestUtils;
import ru.otus.domain.Question;
import ru.otus.service.Parser;

import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class CsvParserTest {

    @Test
    void parse() throws Exception {

        Properties properties = TestUtils.getProperties();

        Parser<List<Question>> parser = new CsvParser(properties.getProperty("separator"),
                properties.getProperty("csv.file"));
        List<Question> questions = parser.parse();

        assertEquals(questions.size(),5);
    }
}