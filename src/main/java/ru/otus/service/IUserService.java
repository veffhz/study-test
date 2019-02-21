package ru.otus.service;

import java.util.List;

public interface IUserService {
    void askUserName();
    String askUser(String question, List<String> answers);
    void sayToUser(String info);
    String getUserName();
}
