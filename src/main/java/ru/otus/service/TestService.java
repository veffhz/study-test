package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.domain.Question;

import java.util.List;

@Service
public class TestService {

    private final Parser<List<Question>> csvParser;
    private final TestEngine testEngine;

    @Autowired
    public TestService(Parser<List<Question>> csvParser, TestEngine testEngine) {
        this.csvParser = csvParser;
        this.testEngine = testEngine;
    }

    public void runTest() {
        List<Question> questions = prepare();
        testEngine.test(questions);
    }

    private List<Question> prepare() {
        return csvParser.parse();
    }

}
