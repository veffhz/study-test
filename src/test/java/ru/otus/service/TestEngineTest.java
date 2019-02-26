package ru.otus.service;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import ru.otus.dao.UserDao;
import ru.otus.domain.Question;
import ru.otus.service.impl.CsvParser;
import ru.otus.service.impl.UserService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TestEngineTest {

    @Test
    void test1() {
        UserDao userDaoSpy = Mockito.mock(UserDao.class);
        InteractionService interactionServiceSpy = Mockito.mock(InteractionService.class);

        doNothing().when(interactionServiceSpy).write(any(String.class));
        when(interactionServiceSpy.read()).thenReturn("test");

        doNothing().when(userDaoSpy).setNewUser(any(String.class), any(String.class));
        when(userDaoSpy.getPrettyUserName()).thenReturn("test test");

        IUserService userServiceSpy = spy(new UserService(userDaoSpy, interactionServiceSpy));

        TestEngine engineSpy = spy(new TestEngine(userServiceSpy));

        Parser<List<Question>> parser = new CsvParser(";", "/questions.csv");
        List<Question> questions = parser.parse();

        engineSpy.test(questions);

        verify(userServiceSpy, times(1)).askUserName();

        verify(userServiceSpy, times(questions.size())).askUser(any(String.class), ArgumentMatchers.any());

        verify(userServiceSpy, times(1)).getUserName();

        verify(engineSpy, times(1)).sendFeedBack(any(String.class), ArgumentMatchers.any());
    }
}