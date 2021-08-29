package idv.kuma.itehlp2021;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class Itehlp2021ApplicationTests {

    @Autowired
    MockMvc mockMvc;


    @Test
    void contextLoads() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().is(200));

    }

}
