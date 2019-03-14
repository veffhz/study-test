package ru.otus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageAdapter {

    private Locale RU_LOCALE = new Locale("ru", "RU");

    @Autowired
    private MessageSource messageSource;

    public String getMessage(String key) {
        return messageSource.getMessage(key,
                null, Locale.US);
    }

}
