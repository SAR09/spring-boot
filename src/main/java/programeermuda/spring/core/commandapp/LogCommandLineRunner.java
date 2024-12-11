package programeermuda.spring.core.commandapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Component
public class LogCommandLineRunner implements CommandLineRunner {

    private static final Logger logger = Logger.getLogger(LogCommandLineRunner.class.getName());

    @Override
    public void run(String... args) throws Exception {
        logger.info("Log Command Line Runner : {}" + Arrays.toString(args));
    }
}
