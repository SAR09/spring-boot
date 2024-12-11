package programeermuda.spring.core.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import programeermuda.spring.core.aware.IdAware;

import java.util.UUID;

@Component
public class IdGeneratorBeanPostProcessor implements BeanPostProcessor, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(IdGeneratorBeanPostProcessor.class);

    @Override
    public int getOrder() {
        return 1;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.info("Id Generator Processor for Bean {} ", beanName);
        if (bean instanceof IdAware) {
            logger.info("Set Id generator for Bean {}", beanName);
            IdAware idAware = (IdAware) bean;
            idAware.setId(UUID.randomUUID().toString());
        }
        return bean;
    }


}
