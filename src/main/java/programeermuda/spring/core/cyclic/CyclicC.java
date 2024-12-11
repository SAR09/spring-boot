package programeermuda.spring.core.cyclic;

import lombok.AllArgsConstructor;
import lombok.Data;

//@Data
//@AllArgsConstructor
public class CyclicC {

    private CyclicA cyclicA;

    public CyclicC(CyclicA cyclicA) {
        this.cyclicA = cyclicA;
    }

    public CyclicA getCyclicA() {
        return cyclicA;
    }

    public void setCyclicA(CyclicA cyclicA) {
        this.cyclicA = cyclicA;
    }
}
