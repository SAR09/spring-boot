package com.programmermuda.spring.config.springbootmessagesource;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import java.util.Locale;

@SpringBootTest(classes = SpringBootMessageSourceTest.TestApplication.class)
public class SpringBootMessageSourceTest {

    @Autowired
    private TestApplication.SampleSource sampleSource;

    @Test
    void testHelloSaiful() {
        Assertions.assertEquals("Hello saiful", sampleSource.helloSaiful(Locale.ENGLISH));
        Assertions.assertEquals("Halo saiful", sampleSource.helloSaiful(new Locale("in_ID")));
    }

    @SpringBootApplication
    public static class TestApplication{

        @Component
        public static class SampleSource implements MessageSourceAware {

            public MessageSource messageSource;

            @Override
            public void setMessageSource(MessageSource messageSource) {
                this.messageSource = messageSource;
            }

            public String helloSaiful(Locale locale){
                return messageSource.getMessage("hello", new Object[]{"saiful"}, locale);
            }
        }
    }
}
