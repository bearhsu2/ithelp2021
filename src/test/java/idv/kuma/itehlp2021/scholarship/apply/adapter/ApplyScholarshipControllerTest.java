package idv.kuma.itehlp2021.scholarship.apply.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import idv.kuma.itehlp2021.scholarship.apply.ApplyScholarshipService;
import idv.kuma.itehlp2021.student.register.ApiResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApplyScholarshipControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @MockBean
    private ApplyScholarshipService applyScholarshipService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void student_NOT_exists() throws Exception {

        assume_student_not_exist(9527L);

        mockMvc.perform(request(
                        "/scholarship/apply"
                        , application_form(9527L, 55688L)))
                .andExpect(status().is(400))
                .andExpect(content().json(bad_response_content(987)));

    }

    @Test
    void scholarship_NOT_exists() throws Exception {

        assume_scholarship_not_exists(55688L);

        mockMvc.perform(request(
                        "/scholarship/apply"
                        , application_form(9527L, 55688L)))
                .andExpect(status().is(400))
                .andExpect(content().json(bad_response_content(369)));// 369: scholar not exists

    }

    @Test
    void data_access_error() throws Exception {

        assume_data_access_would_fail(9527L, 55688L);

        mockMvc.perform(request(
                        "/scholarship/apply"
                        , application_form(9527L, 55688L)))
                .andExpect(status().is(500))
                .andExpect(content().json(bad_response_content(666)));// 666: data access error

    }

    @Test
    void unknown_error() throws Exception {

        given_some_bug_exists(9527L, 55688L);

        mockMvc.perform(request(
                        "/scholarship/apply"
                        , application_form(9527L, 55688L)))
                .andExpect(status().is(500))
                .andExpect(content().json(bad_response_content(999)));

    }

    private void given_some_bug_exists(long studentId, long scholarshipId) throws ClientSideErrorException, DataAccessErrorException {
        Mockito.doThrow(new RuntimeException("some message"))
                .when(applyScholarshipService)
                .apply(application_form(studentId, scholarshipId));
    }

    private void assume_data_access_would_fail(long studentId, long scholarshipId) throws DataAccessErrorException, ClientSideErrorException {
        Mockito.doThrow(new DataAccessErrorException("ANY_MESSAGE"))
                .when(applyScholarshipService)
                .apply(application_form(studentId, scholarshipId));
    }

    private void assume_scholarship_not_exists(long scholarshipId) throws DataAccessErrorException, ClientSideErrorException {
        Mockito.doThrow(new ClientSideErrorException("ANY_MESSAGE", 369))
                .when(applyScholarshipService)
                .apply(application_form(9527L, scholarshipId));
    }

    private String bad_response_content(int errorCode) throws JsonProcessingException {
        return objectMapper.writeValueAsString(ApiResponse.bad(errorCode));
    }

    private MockHttpServletRequestBuilder request(String urlTemplate, ApplicationForm form) throws JsonProcessingException {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(urlTemplate)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(form));
        return request;
    }

    private void assume_student_not_exist(long studentId) throws DataAccessErrorException, ClientSideErrorException {
        Mockito.doThrow(new ClientSideErrorException("ANY_MESSAGE", 987))
                .when(applyScholarshipService)
                .apply(application_form(studentId, 55688L));
    }

    private ApplicationForm application_form(long studentId, long scholarshipId) {
        ApplicationForm applicationForm = new ApplicationForm(
                studentId,
                scholarshipId
        );
        return applicationForm;
    }
}
