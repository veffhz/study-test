package ru.otus.service;

import java.util.List;

public interface UserService {
    void askUserName();
    String askUser(String question, List<String> answers);
    void sayToUser(String info);
    String getUserName();
}
