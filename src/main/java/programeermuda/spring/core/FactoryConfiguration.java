package programeermuda.spring.core;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import programeermuda.spring.core.factory.PaymentGatewayClientFactory;

@Component
@Import({
        PaymentGatewayClientFactory.class
})
public class FactoryConfiguration {
}
