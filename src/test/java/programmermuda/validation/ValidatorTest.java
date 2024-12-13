package programmermuda.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import programmermuda.validation.data.Person;

import java.util.Set;

@SpringBootTest
public class ValidatorTest {

    @Autowired
    private Validator validator;

    @Test
    void personNotValid() {
        var person = new Person("", "");
        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);
        Assertions.assertFalse(constraintViolations.isEmpty());
    }

    @Test
    void personValid() {
        var person = new Person("1", "Saiful");

        Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);
        Assertions.assertTrue(constraintViolations.isEmpty());
    }
}
