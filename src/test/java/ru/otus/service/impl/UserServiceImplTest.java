package ru.otus.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.otus.dao.UserDao;
import ru.otus.service.UserService;
import ru.otus.service.InteractionService;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    @Mock
    private InteractionService interactionService;

    @Mock
    private UserService userService;

    @Mock
    private MessageSourceWrapperService adapter;

    @BeforeEach
    void setUp() {
        doNothing().when(interactionService).write(any(String.class));
        when(interactionService.read()).thenReturn("test");
        doNothing().when(userDao).setNewUser(any(String.class), any(String.class));
        when(userDao.getPrettyUserName()).thenReturn("test test");
        when(adapter.getMessage(any(String.class))).thenReturn("message");

        userService = new UserServiceImpl(userDao, interactionService, adapter);
    }

    @Test
    void askUserName() {
        userService.askUserName();

        verify(interactionService, times(2)).write(any(String.class));
        verify(interactionService, times(2)).read();
        verify(userDao, times(1)).setNewUser(any(String.class), any(String.class));
    }

    @Test
    void askUser() {
        String answer = userService.askUser("question", Arrays.asList("one", "two", "three"));

        verify(interactionService, times(4)).write(any(String.class));
        assertEquals(answer, "test");
    }

    @Test
    void sayToUser() {
        userService.sayToUser("test");
        verify(interactionService, times(1)).write(any(String.class));
    }

    @Test
    void getUserName() {
        String userName = userService.getUserName();
        assertEquals(userName, "test test");
    }
}