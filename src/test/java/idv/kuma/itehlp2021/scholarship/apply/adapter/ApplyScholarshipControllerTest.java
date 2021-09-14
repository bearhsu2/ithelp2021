package idv.kuma.itehlp2021.scholarship.apply.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import idv.kuma.itehlp2021.scholarship.apply.ApplyScholarshipService;
import idv.kuma.itehlp2021.student.register.ApiResponse;
import idv.kuma.itehlp2021.student.register.StudentNotExistException;
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

    @MockBean
    ApplyScholarshipService applyScholarshipService;
    private ObjectMapper objectMapper = new ObjectMapper();
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

    private void assume_scholarship_not_exists(long scholarshipId) throws StudentNotExistException, ScholarshipNotExistException {
        Mockito.doThrow(new ScholarshipNotExistException("ANY_MESSAGE"))
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

    private void assume_student_not_exist(long studentId) throws StudentNotExistException, ScholarshipNotExistException {
        Mockito.doThrow(new StudentNotExistException("ANY_MESSAGE"))
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
