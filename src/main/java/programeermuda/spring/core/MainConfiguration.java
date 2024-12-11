package programeermuda.spring.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import programeermuda.spring.core.configuration.BarConfiguration;
import programeermuda.spring.core.configuration.FooConfiguration;

@Configuration
@Import(value = {
        FooConfiguration.class,
        BarConfiguration.class
})
public class MainConfiguration {
}
