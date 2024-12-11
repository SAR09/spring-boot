package programeermuda.spring.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.boot.web.reactive.context.ConfigurableReactiveWebApplicationContext;
import programeermuda.spring.core.client.PaymentGatewayClient;

public class FactoryTest {

    private ConfigurableReactiveWebApplicationContext applicationContext;

    @BeforeEach
    void setUp() {
        applicationContext = new AnnotationConfigReactiveWebApplicationContext(FactoryConfiguration.class);
        applicationContext.registerShutdownHook();
    }

    @Test
    void testFactory() {
        PaymentGatewayClient gatewayClient = applicationContext.getBean(PaymentGatewayClient.class);

        Assertions.assertNotNull(gatewayClient);
        Assertions.assertEquals("https://example.com", gatewayClient.getEndpoint());
        Assertions.assertEquals("private", gatewayClient.getPrivateKey());
        Assertions.assertEquals("public", gatewayClient.getPublicKey());
    }
}
