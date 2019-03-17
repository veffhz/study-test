package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.service.TestService;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class StudyTestApplication {

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
		ms.setBasename("/i18n/bundle");
		ms.setDefaultEncoding("UTF-8");
		return ms;
	}

	public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(StudyTestApplication.class, args);

        TestService testService = applicationContext.getBean(TestService.class);
        testService.runTest();
    }

}
