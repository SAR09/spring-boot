package programeermuda.spring.core.data;

import org.springframework.stereotype.Component;
import programeermuda.spring.core.aware.IdAware;

@Component
public class Car implements IdAware {

    private String id;

    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}
