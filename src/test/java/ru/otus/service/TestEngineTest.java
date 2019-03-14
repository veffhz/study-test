package ru.otus.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.otus.TestUtils;
import ru.otus.dao.UserDao;
import ru.otus.domain.Question;
import ru.otus.service.impl.CsvParser;
import ru.otus.service.impl.MessageSourceWrapperService;
import ru.otus.service.impl.UserServiceImpl;

import java.util.List;
import java.util.Properties;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestEngineTest {

    @Mock
    UserDao userDaoSpy;

    @Mock
    InteractionService interactionServiceSpy;

    @Mock
    MessageSourceWrapperService adapter;

    @Test
    void test1() throws Exception {
        doNothing().when(interactionServiceSpy).write(any(String.class));
        when(interactionServiceSpy.read()).thenReturn("test");
        doNothing().when(userDaoSpy).setNewUser(any(String.class), any(String.class));
        when(userDaoSpy.getPrettyUserName()).thenReturn("test test");
        when(adapter.getMessage(any(String.class))).thenReturn("message");
        when(adapter.getLocale()).thenReturn("en_US");

        UserService userServiceSpy = spy(new UserServiceImpl(userDaoSpy, interactionServiceSpy, adapter));

        TestEngine engineSpy = spy(new TestEngine(userServiceSpy, adapter));

        Properties properties = TestUtils.getProperties();
        Parser<List<Question>> parser = new CsvParser(properties.getProperty("separator"),
                properties.getProperty("csv.file"), adapter);
        List<Question> questions = parser.parse();

        engineSpy.test(questions);

        verify(userServiceSpy, times(1)).askUserName();

        verify(userServiceSpy, times(questions.size())).askUser(any(String.class), ArgumentMatchers.any());

        verify(userServiceSpy, times(1)).getUserName();

        verify(engineSpy, times(1)).sendFeedBack(any(String.class), ArgumentMatchers.any());
    }

}