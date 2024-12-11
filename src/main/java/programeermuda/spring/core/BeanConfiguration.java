package programeermuda.spring.core;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import programeermuda.spring.core.data.Foo;

@Slf4j
@Configuration
public class BeanConfiguration {

    private static final Logger log = LoggerFactory.getLogger(BeanConfiguration.class);

    @Bean
    public Foo foo(){
        Foo foo = new Foo();
        log.info("create new foo");
        return foo;
    }

}
