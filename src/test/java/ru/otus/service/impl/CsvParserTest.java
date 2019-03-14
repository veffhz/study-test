package ru.otus.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.TestUtils;
import ru.otus.domain.Question;
import ru.otus.service.Parser;

import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CsvParserTest {

    @Test
    void parse() throws Exception {

        Properties properties = TestUtils.getProperties();

        MessageSourceWrapperService adapter = Mockito.mock(MessageSourceWrapperService.class);
        when(adapter.getMessage(any(String.class))).thenReturn("message");

        Parser<List<Question>> parser = new CsvParser(properties.getProperty("separator"),
                properties.getProperty("csv.file"), adapter);
        List<Question> questions = parser.parse();

        assertEquals(questions.size(),5);
    }
}