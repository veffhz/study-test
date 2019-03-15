package ru.otus.service;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;

import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import ru.otus.dao.UserDao;
import ru.otus.domain.Question;
import ru.otus.service.impl.CsvParser;
import ru.otus.service.impl.MessageSourceWrapperService;
import ru.otus.service.impl.UserServiceImpl;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class TestEngineTest {

    @Mock
    UserDao userDaoSpy;

    @Mock
    InteractionService interactionServiceSpy;

    @Mock
    MessageSourceWrapperService adapter;

    @Value("${separator}")
    private String separator;

    @Value("${csv.file}")
    private String csvFile;

    @Value("${locale}")
    private String locale;

    @Test
    public void test() {
        doNothing().when(interactionServiceSpy).write(any(String.class));
        when(interactionServiceSpy.read()).thenReturn("test");
        doNothing().when(userDaoSpy).setNewUser(any(String.class), any(String.class));
        when(userDaoSpy.getPrettyUserName()).thenReturn("test test");
        when(adapter.getMessage(any(String.class))).thenReturn("message");
        when(adapter.getLocale()).thenReturn(locale);

        UserService userServiceSpy = spy(new UserServiceImpl(userDaoSpy, interactionServiceSpy, adapter));

        TestEngine engineSpy = spy(new TestEngine(userServiceSpy, adapter));

        Parser<List<Question>> parser = new CsvParser(separator, csvFile, adapter);
        List<Question> questions = parser.parse();

        engineSpy.test(questions);

        verify(userServiceSpy, times(1)).askUserName();

        verify(userServiceSpy, times(questions.size())).askUser(any(String.class), ArgumentMatchers.any());

        verify(userServiceSpy, times(1)).getUserName();

        verify(engineSpy, times(1)).sendFeedBack(any(String.class), ArgumentMatchers.any());
    }

}