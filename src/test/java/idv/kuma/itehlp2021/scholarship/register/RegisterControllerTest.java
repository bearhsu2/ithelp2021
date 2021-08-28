package idv.kuma.itehlp2021.scholarship.register;

import com.fasterxml.jackson.core.JsonProcessingException;
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
    void all_ok() throws Exception {
        mockMvc.perform(request("/register", 35L))
                .andExpect(status().is(HttpStatus.OK.value()));
    }

    private MockHttpServletRequestBuilder request(String url, long studentId) throws JsonProcessingException {
        return MockMvcRequestBuilders
                .post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new RegisterRequest(studentId)));
    }

    @Test
    void student_not_found() throws Exception {
        given_student_not_exists(35L);

        mockMvc.perform(request("/register", 35L))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().json(bad_response(987)));
    }

    private String bad_response(int errorCode) throws JsonProcessingException {
        return objectMapper.writeValueAsString(ApiResponse.bad(errorCode));
    }

    private void given_student_not_exists(long studentId) throws StudentNotExistException {
        Mockito.doThrow(new StudentNotExistException("ANY_MESSAGE"))
                .when(service)
                .execute(new RegisterRequest(studentId));
    }


}