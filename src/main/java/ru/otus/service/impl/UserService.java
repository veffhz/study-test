package ru.otus.service.impl;

import ru.otus.dao.UserDao;
import ru.otus.service.IUserService;
import ru.otus.service.InteractionService;

public class UserService implements IUserService {

    private final UserDao userDao;
    private final InteractionService interactionService;

    public UserService(UserDao userDao, InteractionService interactionService) {
        this.userDao = userDao;
        this.interactionService = interactionService;
    }

    public void askUserName() {
        interactionService.write("Input your first name:");
        String firstName = interactionService.read();

        interactionService.write("Input your last name:");
        String lastName = interactionService.read();

        userDao.setNewUser(firstName, lastName);
    }

    public String getUserName() {
        return userDao.getPrettyUserName();
    }

}
