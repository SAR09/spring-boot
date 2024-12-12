package com.programmermuda.spring.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class ResourceTest {

    @Test
    void testResource() throws IOException {
        var resources = new ClassPathResource("/text/sample.txt");

        Assertions.assertNotNull(resources);
        Assertions.assertTrue(resources.exists());
        Assertions.assertNotNull(resources.getFile());
    }
}
