package programeermuda.spring.core.cyclic;

import lombok.AllArgsConstructor;
import lombok.Data;

//@Data
//@AllArgsConstructor
public class CyclicB {
    private CyclicC cyclicC;

    public CyclicB(CyclicC cyclicC) {
        this.cyclicC = cyclicC;
    }

    public CyclicC getCyclicC() {
        return cyclicC;
    }

    public void setCyclicC(CyclicC cyclicC) {
        this.cyclicC = cyclicC;
    }
}
