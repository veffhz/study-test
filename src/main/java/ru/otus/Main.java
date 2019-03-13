package ru.otus;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.otus.service.TestService;

public class Main {

    private static final String CONFIG_LOCATION = "spring-context.xml";

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_LOCATION);
        TestService testService = context.getBean(TestService.class);
        testService.runTest();
    }

}
