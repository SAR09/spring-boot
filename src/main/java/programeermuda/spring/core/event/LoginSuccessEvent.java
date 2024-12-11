package programeermuda.spring.core.event;

import org.springframework.context.ApplicationEvent;
import programeermuda.spring.core.data.User;

public class LoginSuccessEvent extends ApplicationEvent {

    private final User user;

    public User getUser() {
        return user;
    }

    public LoginSuccessEvent(User user){
        super(user);
        this.user = user;
    }
}
