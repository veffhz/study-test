package ru.otus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import ru.otus.AppProperties;

import static org.springframework.util.StringUtils.parseLocaleString;

@Component
public class MessageSourceWrapperService {

    private final MessageSource messageSource;
    private final String locale;

    @Autowired
    public MessageSourceWrapperService(MessageSource messageSource, AppProperties properties) {
        this.messageSource = messageSource;
        this.locale = properties.getLocale();
    }

    public String getMessage(String key) {
        return messageSource.getMessage(key,
                null, parseLocaleString(locale));
    }

    public String getLocale() {
        return locale;
    }

}
