package ru.otus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.otus.dao.UserDao;
import ru.otus.service.UserService;
import ru.otus.service.InteractionService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final InteractionService interactionService;
    private final MessageSourceWrapperService messageSourceWrapper;

    @Autowired
    public UserServiceImpl(UserDao userDao, InteractionService interactionService, MessageSourceWrapperService messageSourceWrapper) {
        this.userDao = userDao;
        this.interactionService = interactionService;
        this.messageSourceWrapper = messageSourceWrapper;
    }

    public void askUserName() {
        interactionService.write(messageSourceWrapper.getMessage("question.firstname"));
        String firstName = interactionService.read();

        interactionService.write(messageSourceWrapper.getMessage("question.lastname"));
        String lastName = interactionService.read();

        userDao.setNewUser(firstName, lastName);
    }

    @Override
    public String askUser(String question, List<String> answers) {
        interactionService.write(question);
        answers.forEach(interactionService::write);
        return interactionService.read();
    }

    @Override
    public void sayToUser(String info) {
        interactionService.write(info);
    }

    public String getUserName() {
        return userDao.getPrettyUserName();
    }

}
