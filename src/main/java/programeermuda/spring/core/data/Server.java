package programeermuda.spring.core.data;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Server  {

    @PostConstruct
    public void start(){

    }

    @PreDestroy
    public void stop(){

    }


}
