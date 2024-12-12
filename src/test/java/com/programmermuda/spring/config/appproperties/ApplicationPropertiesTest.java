package com.programmermuda.spring.config.appproperties;

import com.programmermuda.spring.config.messagesource.MessageSourceTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

@SpringBootTest(classes = MessageSourceTest.TestApplication.class)
public class ApplicationPropertiesTest {

    @Autowired
    private Environment environment;

    @Test
    void testApplicationProperties() {
        String applicationName = environment.getProperty("application.name");
        Assertions.assertEquals("Belajar Spring Boot", applicationName);
    }

    @SpringBootApplication
    public static class TestAplication{

    }
}
