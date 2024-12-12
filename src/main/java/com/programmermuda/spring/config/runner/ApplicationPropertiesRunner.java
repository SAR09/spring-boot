package com.programmermuda.spring.config.runner;

import com.programmermuda.spring.config.properties.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ApplicationPropertiesRunner implements ApplicationRunner {

    private static Logger logger = Logger.getLogger(ApplicationProperties.class.getName());

    private ApplicationProperties properties;

    public ApplicationPropertiesRunner(ApplicationProperties properties) {
        this.properties = properties;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info(properties.getName());
        logger.info(String.valueOf(properties.getVersion()) );
        logger.info(String.valueOf(properties.isProductionMode()));
    }
}
