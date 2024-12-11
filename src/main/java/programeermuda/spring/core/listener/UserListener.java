package programeermuda.spring.core.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import programeermuda.spring.core.event.LoginSuccessEvent;

import java.util.logging.Logger;

@Component
public class UserListener {

    private static final Logger logger = Logger.getLogger(UserListener.class.getName());


    @EventListener(classes = LoginSuccessEvent.class)
    public void onLoginSuccessEvent(LoginSuccessEvent event){
        logger.info("Success login again for user {}" + event.getUser());
    }
}
