package ru.otus.service;

import org.junit.jupiter.api.Test;

import org.mockito.ArgumentMatchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestPropertySource;

import ru.otus.dao.UserDao;
import ru.otus.domain.Question;
import ru.otus.service.impl.MessageSourceWrapperService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource("classpath:application-test.yml")
class TestEngineTest {

    @MockBean
    private UserDao userDaoSpy;

    @MockBean
    private InteractionService interactionServiceSpy;

    @SpyBean
    private UserService userServiceSpy;

    @SpyBean
    private TestEngine engineSpy;

    @Autowired
    private MessageSourceWrapperService adapter;

    @Autowired
    private Parser<List<Question>> parser;

    @Test
    void test() {
        when(userDaoSpy.getPrettyUserName()).thenReturn("test test");

        List<Question> questions = parser.parse();

        engineSpy.test(questions);

        verify(userServiceSpy, times(1)).askUserName();

        verify(userServiceSpy, times(questions.size())).askUser(any(String.class), ArgumentMatchers.any());

        verify(userServiceSpy, times(1)).getUserName();

        verify(engineSpy, times(1)).sendFeedBack(any(String.class), ArgumentMatchers.any());
    }

}