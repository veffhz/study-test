package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.domain.Question;
import ru.otus.service.impl.MessageAdapter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class TestEngine {

    private final UserService userService;
    private final MessageAdapter adapter;

    @Autowired
    TestEngine(UserService userService, MessageAdapter adapter) {
        this.userService = userService;
        this.adapter = adapter;
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
        sb.append(String.format("\n%s %s!\n", adapter.getMessage("greeting"), userName));
        sb.append(String.format("\n%s:\n", adapter.getMessage("result")));

        for (Map.Entry<Question, Boolean> entry : result.entrySet()) {
            String ok = adapter.getMessage("ok.msg");
            String fail = adapter.getMessage("fail.msg");
            sb.append(String.format("%s %s - %s\n", adapter.getMessage("question"),
                    entry.getKey().getText(), entry.getValue() ? ok : fail));
        }

        sb.append(String.format("\n%s, %s!\n", adapter.getMessage("parting"),userName));

        userService.sayToUser(sb.toString());
    }

}
