package programeermuda.spring.core.runnerapp;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class SimpleRunner implements ApplicationRunner {

    private static final Logger logger = Logger.getLogger(SimpleRunner.class.getName());

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<String> profiles = args.getOptionValues("profiles");
        logger.info("Profiles : {}" + profiles);
    }
}
