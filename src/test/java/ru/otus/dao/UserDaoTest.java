package ru.otus.dao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDaoTest {

    @Test
    void getPrettyUserName() {
        UserDao userDao = new UserDao();
        userDao.setNewUser("test", "test");
        assertEquals(userDao.getPrettyUserName(), "test test");
    }
}