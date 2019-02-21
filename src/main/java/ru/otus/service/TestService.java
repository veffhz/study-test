package ru.otus.service;

import ru.otus.domain.Question;

import java.util.List;
import java.util.Map;

public class TestService {

    private final Parser<List<Question>> csvParser;
    private final TestEngine testEngine;

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
