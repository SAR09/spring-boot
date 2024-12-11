package programeermuda.spring.core.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import programeermuda.spring.core.repository.CustomerRepository;

@Component
public class CustomerService {


    @Autowired
    @Qualifier("normalCustomerRepository")
    private CustomerRepository normalCustomerRepository;

    @Autowired
    @Qualifier("premiumCustomerRepository")
    private CustomerRepository premiumCustomerRepository;

    public CustomerRepository getNormalCustomerRepository() {
        return normalCustomerRepository;
    }

    public CustomerRepository getPremiumCustomerRepository() {
        return premiumCustomerRepository;
    }
}
