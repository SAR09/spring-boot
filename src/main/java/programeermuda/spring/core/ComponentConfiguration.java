package programeermuda.spring.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import programeermuda.spring.core.data.MultiFoo;

@Configuration
@ComponentScan(basePackages = {
        "programeermuda.spring.core.repository",
        "programeermuda.spring.core.service",
        "programeermuda.spring.core.configuration",

})
@Import(MultiFoo.class)
public class ComponentConfiguration {
}
