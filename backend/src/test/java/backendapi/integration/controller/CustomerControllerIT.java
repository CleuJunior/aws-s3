package backendapi.integration.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static backendapi.integration.controller.CustomerJsonUtils.customerJson;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class CustomerControllerIT extends IntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    private static final String ENDPOINT = "/api/v1/customers";

    @Test
    void shouldReturnListCustomer() throws Exception {
        mockMvc
                .perform(get(ENDPOINT))
                .andDo(print())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(content().json(customerJson()));
    }

    @Test
    void shouldReturnPageCustomer() throws Exception {
        var json = "{\"content\":[{\"id\":1,\"name\":\"Whatz\",\"email\":\"whatz.junior@gmail.com\",\"birthdate\":\"1990-11-09 22:13:00\",\"gender\":\"FEMALE\",\"password\":\"123456789\",\"profileImageId\":\"image1\"}],\"pageable\":{\"pageNumber\":0,\"pageSize\":1,\"sort\":{\"empty\":true,\"sorted\":false,\"unsorted\":true},\"offset\":0,\"paged\":true,\"unpaged\":false},\"last\":false,\"totalElements\":25,\"totalPages\":25,\"size\":1,\"number\":0,\"sort\":{\"empty\":true,\"sorted\":false,\"unsorted\":true},\"numberOfElements\":1,\"first\":true,\"empty\":false}";

        mockMvc
                .perform(get(ENDPOINT)
                        .queryParam("page", "0")
                        .queryParam("size", "1")
                )
                .andDo(print())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }
}
