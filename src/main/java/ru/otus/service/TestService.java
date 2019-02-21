package ru.otus.service;

import ru.otus.domain.Question;

import java.util.List;

public class TestService {

    private final Parser<List<Question>> csvParser;
    private final IUserService userService;

    public TestService(Parser<List<Question>> csvParser, IUserService userService) {
        this.csvParser = csvParser;
        this.userService = userService;
    }

    public void runTest() {

        userService.askUserName();

        List<Question> questions = csvParser.parse("/questions.csv");
        questions.forEach(x -> System.out.println(x.getText()));
    }

}
