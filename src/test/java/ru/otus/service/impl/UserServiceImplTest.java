package ru.otus.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.dao.UserDao;
import ru.otus.service.UserService;
import ru.otus.service.InteractionService;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    private UserDao userDaoSpy;
    private InteractionService interactionServiceSpy;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userDaoSpy = Mockito.mock(UserDao.class);
        interactionServiceSpy = Mockito.mock(InteractionService.class);

        doNothing().when(interactionServiceSpy).write(any(String.class));
        when(interactionServiceSpy.read()).thenReturn("test");

        doNothing().when(userDaoSpy).setNewUser(any(String.class), any(String.class));
        when(userDaoSpy.getPrettyUserName()).thenReturn("test test");

        MessageAdapter adapter = Mockito.mock(MessageAdapter.class);
        when(adapter.getMessage(any(String.class))).thenReturn("message");

        userService = new UserServiceImpl(userDaoSpy, interactionServiceSpy, adapter);
    }

    @Test
    void askUserName() {
        userService.askUserName();

        verify(interactionServiceSpy, times(2)).write(any(String.class));
        verify(interactionServiceSpy, times(2)).read();
        verify(userDaoSpy, times(1)).setNewUser(any(String.class), any(String.class));
    }

    @Test
    void askUser() {
        String answer = userService.askUser("question", Arrays.asList("one", "two", "three"));

        verify(interactionServiceSpy, times(4)).write(any(String.class));
        assertEquals(answer, "test");
    }

    @Test
    void sayToUser() {
        userService.sayToUser("test");
        verify(interactionServiceSpy, times(1)).write(any(String.class));
    }

    @Test
    void getUserName() {
        String userName = userService.getUserName();
        assertEquals(userName, "test test");
    }
}