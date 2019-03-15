package ru.otus.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import ru.otus.domain.Question;
import ru.otus.service.Parser;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class CsvParserTest {

    @Value("${separator}")
    private String separator;

    @Value("${csv.file}")
    private String csvFile;

    @Value("${locale}")
    private String locale;

    @Test
    void parse() {
        MessageSourceWrapperService adapter = Mockito.mock(MessageSourceWrapperService.class);
        when(adapter.getMessage(any(String.class))).thenReturn("message");
        when(adapter.getLocale()).thenReturn(locale);

        Parser<List<Question>> parser = new CsvParser(separator, csvFile, adapter);
        List<Question> questions = parser.parse();

        assertEquals(questions.size(),5);
    }

}