package ru.otus.domain;

import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
public class Question {

    private final String text;
    private final String correctAnswer;
    private final List<String> answers;

    public Question(String text, String correctAnswer, List<String> answers) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(text, question.text) &&
                Objects.equals(correctAnswer, question.correctAnswer) &&
                Objects.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, correctAnswer, answers);
    }
}
