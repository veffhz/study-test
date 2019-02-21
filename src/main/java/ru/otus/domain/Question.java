package ru.otus.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class Question {

    private final String text;
    private final int correctAnswerNo;
    private final List<String> answers;

    public Question(String text, int correctAnswerNo, List<String> answers) {
        this.text = text;
        this.correctAnswerNo = correctAnswerNo;
        this.answers = answers;
    }

}
