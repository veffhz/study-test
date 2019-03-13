package ru.otus.service;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import ru.otus.TestUtils;
import ru.otus.dao.UserDao;
import ru.otus.domain.Question;
import ru.otus.service.impl.CsvParser;
import ru.otus.service.impl.UserServiceImpl;

import java.util.List;
import java.util.Properties;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TestEngineTest {

    @Test
    void test1() throws Exception {
        UserDao userDaoSpy = Mockito.mock(UserDao.class);
        InteractionService interactionServiceSpy = Mockito.mock(InteractionService.class);

        doNothing().when(interactionServiceSpy).write(any(String.class));
        when(interactionServiceSpy.read()).thenReturn("test");

        doNothing().when(userDaoSpy).setNewUser(any(String.class), any(String.class));
        when(userDaoSpy.getPrettyUserName()).thenReturn("test test");

        UserService userServiceSpy = spy(new UserServiceImpl(userDaoSpy, interactionServiceSpy));

        TestEngine engineSpy = spy(new TestEngine(userServiceSpy));

        Properties properties = TestUtils.getProperties();
        Parser<List<Question>> parser = new CsvParser(properties.getProperty("separator"),
                properties.getProperty("csv.file"));
        List<Question> questions = parser.parse();

        engineSpy.test(questions);

        verify(userServiceSpy, times(1)).askUserName();

        verify(userServiceSpy, times(questions.size())).askUser(any(String.class), ArgumentMatchers.any());

        verify(userServiceSpy, times(1)).getUserName();

        verify(engineSpy, times(1)).sendFeedBack(any(String.class), ArgumentMatchers.any());
    }

}