package ru.otus.service.impl;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.LocaleSpringEvent;

import static org.springframework.util.StringUtils.parseLocaleString;

@Log
@Component
public class MessageSourceWrapperService implements ApplicationListener<LocaleSpringEvent> {

    private final MessageSource messageSource;
    private String locale = "en_US";

    @Autowired
    public MessageSourceWrapperService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String key) {
        return messageSource.getMessage(key,
                null, parseLocaleString(locale));
    }

    public String getLocale() {
        return locale;
    }

    @Override
    public void onApplicationEvent(LocaleSpringEvent event) {
        String locale = event.getMessage();
        this.locale = locale;
        log.info("Received spring locale event - " + locale);
    }

}
