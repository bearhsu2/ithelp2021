package idv.kuma.itehlp2021.scholarship.register;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class RegisterControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegisterService service;

    @Test
    void student_not_found() throws Exception {

        Mockito.doThrow(new StudentNotExistException())
                .when(service)
                .execute(any(RegisterRequest.class));

        MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders
                .post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new RegisterRequest()));

        mockMvc.perform(postRequest)
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().json(objectMapper.writeValueAsString(ApiResponse.bad(987))));

    }


//
//    @Test
//    void all_ok() throws Exception {
//        CreateTeamRequest requestBody = request_body_with_match_and_captain(anyMatchId, captainPlayerKey);
//        mockMvc.perform(make_request(requestBody))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(ApiResponse.empty())));
//    }
}