package ru.otus;

import org.springframework.context.ApplicationEvent;

public class LocaleSpringEvent extends ApplicationEvent {

    private String locale;

    public LocaleSpringEvent(Object source, String locale) {
        super(source);
        this.locale = locale;
    }
    public String getMessage() {
        return locale;
    }

}
