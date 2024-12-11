package programeermuda.spring.core.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import programeermuda.spring.core.event.LoginSuccessEvent;

import java.util.logging.Logger;

@Component
public class LoginAgainSuccessListener implements ApplicationListener<LoginSuccessEvent> {

    private static final Logger logger = Logger.getLogger(LoginAgainSuccessListener.class.getName());

    @Override
    public void onApplicationEvent(LoginSuccessEvent event) {
        logger.info("Success login again for user {}" + event.getUser());

    }
}
