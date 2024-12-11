package programeermuda.spring.core.cyclic;

import lombok.AllArgsConstructor;
import lombok.Data;

//@Data
//@AllArgsConstructor
public class CyclicA {
    private CyclicB cyclicB;

    public CyclicA(CyclicB cyclicB) {
        this.cyclicB = cyclicB;
    }

    public CyclicB getCyclicB() {
        return cyclicB;
    }

    public void setCyclicB(CyclicB cyclicB) {
        this.cyclicB = cyclicB;
    }
}
