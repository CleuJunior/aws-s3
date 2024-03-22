package backendapi.integration.controller;

import backendapi.dto.CustomerRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static backendapi.model.Gender.FEMALE;
import static backendapi.model.Gender.MALE;
import static java.time.LocalDateTime.of;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@Transactional
class CustomerControllerIT extends IntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private static final String ENDPOINT = "/api/v1/customers";
    private static final String ENDPOINT_CUSTOMER_ID = ENDPOINT + "/{customerId}";

    @Test
    void shouldReturnListCustomer() throws Exception {
        var expectResponse = "[{\"id\":1,\"name\":\"Whatz\",\"email\":\"whatz.junior@gmail.com\",\"birthdate\":\"1990-11-09 22:13:00\",\"gender\":\"FEMALE\",\"password\":\"123456789\",\"profileImageId\":\"image1\"},{\"id\":2,\"name\":\"João\",\"email\":\"joao@gmail.com\",\"birthdate\":\"1985-05-15 10:30:00\",\"gender\":\"MALE\",\"password\":\"987654321\",\"profileImageId\":\"image2\"},{\"id\":3,\"name\":\"Maria\",\"email\":\"maria@gmail.com\",\"birthdate\":\"1992-03-25 08:45:00\",\"gender\":\"FEMALE\",\"password\":\"qwertyui\",\"profileImageId\":\"image3\"},{\"id\":4,\"name\":\"Pedro\",\"email\":\"pedro@gmail.com\",\"birthdate\":\"1988-08-12 18:20:00\",\"gender\":\"MALE\",\"password\":\"asdfghjk\",\"profileImageId\":\"image4\"},{\"id\":5,\"name\":\"Ana\",\"email\":\"ana@gmail.com\",\"birthdate\":\"1995-10-04 14:55:00\",\"gender\":\"FEMALE\",\"password\":\"zxcvbnm\",\"profileImageId\":\"image5\"}]";

        mockMvc
                .perform(get(ENDPOINT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().json(expectResponse));
    }

    @Test
    void shouldReturnPageCustomer() throws Exception {
        var expectResponse = "{\"content\":[{\"id\":3,\"name\":\"Maria\",\"email\":\"maria@gmail.com\",\"birthdate\":\"1992-03-25 08:45:00\",\"gender\":\"FEMALE\",\"password\":\"qwertyui\",\"profileImageId\":\"image3\"},{\"id\":4,\"name\":\"Pedro\",\"email\":\"pedro@gmail.com\",\"birthdate\":\"1988-08-12 18:20:00\",\"gender\":\"MALE\",\"password\":\"asdfghjk\",\"profileImageId\":\"image4\"}],\"pageable\":{\"pageNumber\":1,\"pageSize\":2,\"sort\":{\"empty\":true,\"sorted\":false,\"unsorted\":true},\"offset\":2,\"unpaged\":false,\"paged\":true},\"last\":false,\"totalPages\":3,\"totalElements\":5,\"size\":2,\"number\":1,\"sort\":{\"empty\":true,\"sorted\":false,\"unsorted\":true},\"first\":false,\"numberOfElements\":2,\"empty\":false}";

        mockMvc
                .perform(get(ENDPOINT)
                        .queryParam("page", "1")
                        .queryParam("size", "2")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().json(expectResponse));
    }

    @Test
    void shouldReturnCustomerById() throws Exception {
        var expectResponse = "{\"id\":2,\"name\":\"João\",\"email\":\"joao@gmail.com\",\"birthdate\":\"1985-05-15 10:30:00\",\"gender\":\"MALE\",\"password\":\"987654321\",\"profileImageId\":\"image2\"}";

        mockMvc
                .perform(get(ENDPOINT_CUSTOMER_ID, 2))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().json(expectResponse));
    }

    @Test
    void shouldSaveCustomer() throws Exception {
        var birthdate = of(1989, 3, 4, 12, 32);
        var request = new CustomerRequest("Save", "savedata@gmail.com", birthdate, FEMALE, "123", "ABC123");
        var jsonRequest = objectMapper.writeValueAsString(request);
        var expectResponse = "{\"id\":6,\"name\":\"Save\",\"email\":\"savedata@gmail.com\",\"birthdate\":\"1989-03-04 12:32:00\",\"gender\":\"FEMALE\",\"password\":\"123\",\"profileImageId\":\"ABC123\"}";

        mockMvc
                .perform(post(ENDPOINT)
                        .contentType(APPLICATION_JSON)
                        .content(jsonRequest)
                        .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isCreated())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().json(expectResponse));
    }

    @Test
    void shouldUpdateCustomer() throws Exception {
        var birthdate = of(1977, 3, 5, 12, 32);
        var request = new CustomerRequest("Update", "update@gmail.com", birthdate, MALE, "456", "DEF456");
        var jsonRequest = objectMapper.writeValueAsString(request);
        var expectResponse = "{\"id\":1,\"name\":\"Update\",\"email\":\"update@gmail.com\",\"birthdate\":\"1977-03-05 12:32:00\",\"gender\":\"MALE\",\"password\":\"456\",\"profileImageId\":\"DEF456\"}";

        mockMvc
                .perform(put(ENDPOINT_CUSTOMER_ID, 1)
                        .contentType(APPLICATION_JSON)
                        .content(jsonRequest)
                        .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isNoContent());

        mockMvc
                .perform(get(ENDPOINT_CUSTOMER_ID, 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().json(expectResponse));
    }

    @Test
    void shouldDeleteCustomerById() throws Exception {
        var expectResponse = "[{\"id\":2,\"name\":\"João\",\"email\":\"joao@gmail.com\",\"birthdate\":\"1985-05-15 10:30:00\",\"gender\":\"MALE\",\"password\":\"987654321\",\"profileImageId\":\"image2\"},{\"id\":3,\"name\":\"Maria\",\"email\":\"maria@gmail.com\",\"birthdate\":\"1992-03-25 08:45:00\",\"gender\":\"FEMALE\",\"password\":\"qwertyui\",\"profileImageId\":\"image3\"},{\"id\":4,\"name\":\"Pedro\",\"email\":\"pedro@gmail.com\",\"birthdate\":\"1988-08-12 18:20:00\",\"gender\":\"MALE\",\"password\":\"asdfghjk\",\"profileImageId\":\"image4\"},{\"id\":5,\"name\":\"Ana\",\"email\":\"ana@gmail.com\",\"birthdate\":\"1995-10-04 14:55:00\",\"gender\":\"FEMALE\",\"password\":\"zxcvbnm\",\"profileImageId\":\"image5\"}]";

        mockMvc
                .perform(delete(ENDPOINT_CUSTOMER_ID, 1)
                        .contentType(APPLICATION_JSON)
                        .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().isNoContent());

        mockMvc
                .perform(get(ENDPOINT))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(content().json(expectResponse));
    }
}
