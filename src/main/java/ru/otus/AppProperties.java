package ru.otus;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties
public class AppProperties {
    private String separator;
    private String csvFile;
    private String locale;
}
