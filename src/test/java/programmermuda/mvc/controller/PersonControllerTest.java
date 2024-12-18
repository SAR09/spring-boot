package programmermuda.mvc.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createPerson() throws Exception {
        mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName", "Saiful")
                        .param("middleName", "Amin")
                        .param("lastName", "Rizki")
                        .param("email", "saiful@example.com")
                        .param("phone", "08983923992")
                        .param("address.street", "Jalan Sendirian")
                        .param("address.city", "Semarang")
                        .param("address.country", "Indonesia")
                        .param("address.postalCode", "1111")
                        .param("hobbies[0]", "Coding")
                        .param("hobbies[1]", "Reading")
                        .param("hobbies[2]", "Gaming")
                        .param("socialMedias[0].name", "Facebook")
                        .param("socialMedias[0].location", "Facebook.com/SaifulAminRizki")
                        .param("socialMedias[1].name", "Instagram")
                        .param("socialMedias[1].location", "Instagram.com/saipular")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Success create person Saiful Amin Rizki " +
                        "with email saiful@example.com  and phone 08983923992 with address " +
                        "Jalan Sendirian, Semarang, Indonesia, 1111"))
        );
    }

    @Test
    void createPersonValidationError() throws Exception {
        mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("middleName", "Amin")
                        .param("lastName", "Rizki")
                        .param("email", "saiful@example.com")
                        .param("phone", "08983923992")
                        .param("address.street", "Jalan Sendirian")
                        .param("address.city", "Semarang")
                        .param("address.country", "Indonesia")
                        .param("address.postalCode", "1111")
                        .param("hobbies[0]", "Coding")
                        .param("hobbies[1]", "Reading")
                        .param("hobbies[2]", "Gaming")
                        .param("socialMedias[0].name", "Facebook")
                        .param("socialMedias[0].location", "Facebook.com/SaifulAminRizki")
                        .param("socialMedias[1].name", "Instagram")
                        .param("socialMedias[1].location", "Instagram.com/saipular")
        ).andExpectAll(
                status().isBadRequest(),
                content().string(Matchers.containsString("You send invalid data"))
        );
    }
}
