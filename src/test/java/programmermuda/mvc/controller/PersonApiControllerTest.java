package programmermuda.mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import programmermuda.mvc.model.CreatePersonRequest;
import programmermuda.mvc.model.CreateSocialMediaRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createPerson() throws Exception {
        CreatePersonRequest request = new CreatePersonRequest();
        request.setFirstName("Saiful");
        request.setMiddleName("Amin");
        request.setLastName("Rizki");
        request.setEmail("saiful@example.com");
        request.setPhone("082912919");
        request.setHobbies(List.of("Coding", "Reading", "Gaming"));
        request.setSocialMedias(new ArrayList<>());
        request.getSocialMedias().add(new CreateSocialMediaRequest("Facebook", "facebook.com/SaifulAminRizki"));
        request.getSocialMedias().add(new CreateSocialMediaRequest("Instagram", "instagram.com/saipular"));

        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
        ).andExpectAll(
                status().isOk(),
                content().json(jsonRequest)
        );
    }

    @Test
    void createPersonValidationError() throws Exception {
        CreatePersonRequest request = new CreatePersonRequest();
        request.setMiddleName("Amin");
        request.setLastName("Rizki");
        request.setHobbies(List.of("Coding", "Reading", "Gaming"));
        request.setSocialMedias(new ArrayList<>());
        request.getSocialMedias().add(new CreateSocialMediaRequest("Facebook", "facebook.com/SaifulAminRizki"));
        request.getSocialMedias().add(new CreateSocialMediaRequest("Instagram", "instagram.com/saipular"));

        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
        ).andExpectAll(
                status().isBadRequest(),
                content().string(Matchers.containsString("Validation Error"))
        );
    }
}
