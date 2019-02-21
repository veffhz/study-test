package ru.otus.service;

import ru.otus.domain.Question;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TestEngine {
    private final IUserService userService;

    public TestEngine(IUserService userService) {
        this.userService = userService;
    }

    void test(List<Question> questions) {
        userService.askUserName();

        Map<Question, Boolean> result = new LinkedHashMap<>();

        for (Question question : questions) {
            String answer = userService.askUser(question.getText(),
                    question.getAnswers());

            result.put(question, question.getCorrectAnswer().equalsIgnoreCase(answer));
        }

        String userName = userService.getUserName();

        sendFeedBack(userName, result);
    }

    private void sendFeedBack(String userName, Map<Question, Boolean> result) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\nDear user %s!\n", userName));
        sb.append("\nResult yor test:\n");

        for (Map.Entry<Question, Boolean> entry : result.entrySet()) {
            sb.append(String.format("Answer %s is %s\n",
                    entry.getKey().getText(), entry.getValue() ? "Ok! )" : "Fail ("));
        }

        sb.append(String.format("\nBye Bye, %s!\n", userName));

        userService.sayToUser(sb.toString());
    }

}
