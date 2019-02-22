package ru.otus.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.dao.UserDao;
import ru.otus.service.IUserService;
import ru.otus.service.InteractionService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void askUserName() {
        UserDao userDaoSpy = Mockito.mock(UserDao.class);
        InteractionService interactionServiceSpy = Mockito.mock(InteractionService.class);

        doNothing().when(interactionServiceSpy).write(any(String.class));
        when(interactionServiceSpy.read()).thenReturn("test");

        doNothing().when(userDaoSpy).setNewUser(any(String.class), any(String.class));

        IUserService userService = new UserService(userDaoSpy, interactionServiceSpy);

        userService.askUserName();

        verify(interactionServiceSpy, times(2)).write(any(String.class));
        verify(interactionServiceSpy, times(2)).read();

        verify(userDaoSpy, times(1)).setNewUser(any(String.class), any(String.class));

    }

    @Test
    void askUser() {
    }

    @Test
    void sayToUser() {
    }

    @Test
    void getUserName() {
    }
}