package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.domain.Question;
import ru.otus.service.impl.MessageSourceWrapperService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class TestEngine {

    private final UserService userService;
    private final MessageSourceWrapperService messageSourceWrapper;

    @Autowired
    TestEngine(UserService userService, MessageSourceWrapperService messageSourceWrapper) {
        this.userService = userService;
        this.messageSourceWrapper = messageSourceWrapper;
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

    void sendFeedBack(String userName, Map<Question, Boolean> result) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\n%s %s!\n", messageSourceWrapper.getMessage("greeting"), userName));
        sb.append(String.format("\n%s:\n", messageSourceWrapper.getMessage("result")));

        for (Map.Entry<Question, Boolean> entry : result.entrySet()) {
            String ok = messageSourceWrapper.getMessage("ok.msg");
            String fail = messageSourceWrapper.getMessage("fail.msg");
            sb.append(String.format("%s %s - %s\n", messageSourceWrapper.getMessage("question"),
                    entry.getKey().getText(), entry.getValue() ? ok : fail));
        }

        sb.append(String.format("\n%s, %s!\n", messageSourceWrapper.getMessage("parting"),userName));

        userService.sayToUser(sb.toString());
    }

}
