package ru.otus.shell;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.util.StringUtils;
import ru.otus.LocaleSpringEvent;
import ru.otus.service.TestService;
import ru.otus.service.impl.MessageSourceWrapperService;

@Log
@ShellComponent
public class TestCommands {

    private final TestService testService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final MessageSourceWrapperService messageSourceWrapper;

    @Autowired
    public TestCommands(TestService testService, ApplicationEventPublisher publisher,
                        MessageSourceWrapperService wrapperService) {
        this.testService = testService;
        this.applicationEventPublisher = publisher;
        this.messageSourceWrapper = wrapperService;
    }

    @ShellMethod("Set locale.")
    public void locale(@ShellOption(defaultValue="") String locale) {
        if (StringUtils.isEmpty(locale)) {
            log.info(String.format("Current locale %s", messageSourceWrapper.getLocale()));
        } else {
            log.info(String.format("Publishing locale event. Send locale %s", locale));
            LocaleSpringEvent customSpringEvent = new LocaleSpringEvent(this, locale);
            applicationEventPublisher.publishEvent(customSpringEvent);
        }
    }

    @ShellMethod("Run test.")
    public void test() {
        testService.runTest();
    }

}
