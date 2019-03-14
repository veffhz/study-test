package ru.otus;

import java.io.InputStream;
import java.util.Properties;

public class TestUtils {
    public static Properties getProperties() throws Exception {
        Properties p = new Properties();
        InputStream in = TestUtils.class.getResourceAsStream("/application-test.properties");
        p.load(in);
        return p;
    }
}
