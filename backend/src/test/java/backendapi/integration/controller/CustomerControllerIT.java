package backendapi.integration.controller;

import backendapi.dto.CustomerRequest;
import backendapi.dto.CustomerResponse;
import backendapi.factory.CustomerFactory;
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

@Transactional
@AutoConfigureMockMvc
class CustomerControllerIT extends IntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CustomerFactory factory;
    private static final String ENDPOINT = "/api/v1/customers";
    private static final String ENDPOINT_CUSTOMER_ID = ENDPOINT + "/{customerId}";

    @Test
    void shouldReturnListCustomer() throws Exception {
        var expectResponse = factory.buildCustomersAsString();

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
        var expectResponse = factory.buildCustomersPageAsString(1, 2);

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
        var id = 3;
        var expectResponse = factory.buildCustomerAsStringById(id);

        mockMvc
                .perform(get(ENDPOINT_CUSTOMER_ID, id))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().json(expectResponse));
    }

    @Test
    void shouldSaveCustomer() throws Exception {
        var birthdate = of(1989, 3, 4, 12, 32);
        var request = new CustomerRequest("Save", "savedata@gmail.com", birthdate, FEMALE, "123", "ABC123");
        var response = new CustomerResponse(6, "Save", "savedata@gmail.com", birthdate, FEMALE, "123", "ABC123");
        var jsonRequest = factory.buildCustomerRequestAsString(request);
        var jsonResponse = factory.buildCustomerResponseAsString(response);

        mockMvc
                .perform(post(ENDPOINT)
                        .contentType(APPLICATION_JSON)
                        .content(jsonRequest)
                        .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isCreated())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().json(jsonResponse));
    }

    @Test
    void shouldUpdateCustomer() throws Exception {
        var id = 1;
        var birthdate = of(1977, 3, 5, 12, 32);
        var request = new CustomerRequest("Update", "update@gmail.com", birthdate, MALE, "456", "DEF456");
        var jsonRequest = factory.buildCustomerRequestAsString(request);
        var expectResponse = factory.buildCustomerAsStringFromRequestToResponse(id, request);

        mockMvc
                .perform(put(ENDPOINT_CUSTOMER_ID, id)
                        .contentType(APPLICATION_JSON)
                        .content(jsonRequest)
                        .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isNoContent());

        mockMvc
                .perform(get(ENDPOINT_CUSTOMER_ID, id))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().json(expectResponse));
    }

    @Test
    void shouldDeleteCustomerById() throws Exception {
        var id = 1;
        var expectResponse = factory.buildCustomerAsStringDeleteById(id);

        mockMvc
                .perform(delete(ENDPOINT_CUSTOMER_ID, id)
                        .contentType(APPLICATION_JSON)
                        .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                )
                .andDo(print())
                .andExpect(status().isNoContent());

        mockMvc
                .perform(get(ENDPOINT))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(content().json(expectResponse));
    }
}
