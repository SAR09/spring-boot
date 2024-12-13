package programmermuda.validation;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import programmermuda.validation.helper.SayHello;

@SpringBootTest
public class SayHelloTest {

    @Autowired
    private SayHello sayHello;

    @Test
    void testSuccess () {
        String message = sayHello.sayHello("Saiful");
        Assertions.assertEquals("Hello Saiful", message);
    }

    @Test
    void testError() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            sayHello.sayHello("");
        });
    }
}
