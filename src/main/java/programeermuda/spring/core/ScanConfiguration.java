package programeermuda.spring.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "programeermuda.spring.core.configuration"
})
public class ScanConfiguration {
}
