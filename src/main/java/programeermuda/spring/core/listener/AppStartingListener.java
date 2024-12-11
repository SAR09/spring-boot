package programeermuda.spring.core.listener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

import java.util.logging.Logger;

public class AppStartingListener implements ApplicationListener<ApplicationStartedEvent> {

    private static final Logger logger = Logger.getLogger(AppStartingListener.class.getName());

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        logger.info("Application starting");
    }
}
