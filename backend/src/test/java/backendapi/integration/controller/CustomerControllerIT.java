package backendapi.integration.controller;

import backendapi.dto.CustomerRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static backendapi.constants.HttpStatusAndMessage.CREATED;
import static backendapi.constants.HttpStatusAndMessage.OK;
import static backendapi.model.Gender.MALE;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.time.LocalDateTime.of;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
class CustomerControllerIT extends IntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;
    private static final String ENDPOINT = "/api/v1/customers";
    private static final String ENDPOINT_CUSTOMER_ID = ENDPOINT + "/{customerId}";

    @Test
    void shouldReturnListCustomer() {
        given()
                .when()
                .get(ENDPOINT)
                .then()
                .assertThat()
                .contentType(JSON)
                .statusCode(OK)
                .body("$.size()", is(5))
                .body("id", hasItem(1))
                .body("name", hasItem("Whatz"))
                .body("email", hasItem("whatz.junior@gmail.com"));
    }

    @Test
    void shouldReturnPageCustomer() {
        given()
                .when()
                .queryParam("page", 1)
                .queryParam("size", 3)
                .get(ENDPOINT)
                .then()
                .assertThat()
                .contentType(JSON)
                .statusCode(OK)
                .body("content.size()", is(2))
                .body("totalElements", equalTo(5))
                .body("pageable.pageNumber", equalTo(1))
                .body("pageable.pageSize", equalTo(3));
    }

    @Test
    void shouldReturnCustomerById() {
        given()
                .when()
                .pathParam("customerId", 4)
                .get(ENDPOINT_CUSTOMER_ID)
                .then()
                .assertThat()
                .contentType(JSON)
                .statusCode(OK)
                .body("id", is(4))
                .body("name", containsString("Pedro"))
                .body("email", containsString("pedro@gmail.com"))
                .body("birthdate", containsString("1988-08-12 18:20:00"))
                .body("gender", containsString("MALE"))
                .body("password", containsString("asdfghjk"))
                .body("profileImageId", containsString("image4"));
    }

    @Test
    void shouldSaveCustomer() throws Exception {
        var expectResponse3 = "{\"name\":\"Update\",\"email\":\"update@gmail.com\",\"birthdate\":\"1996-08-11T11:00:00\",\"gender\":\"MALE\",\"password\":\"456\",\"profileImageId\":\"DEF456\"}";
        var oto = "{\"id\":\"10\",\"name\":\"Update\",\"email\":\"update@gmail.com\",\"birthdate\":\"1996-08-11T11:00:00\",\"gender\":\"MALE\",\"password\":\"456\",\"profileImageId\":\"DEF456\"}\n";

        given()
                .contentType(JSON)
                .body(expectResponse3)
                .post(ENDPOINT)
                .then()
                .log().all()
                .contentType(JSON)
                .statusCode(CREATED);


//        mockMvc
//                .perform(post(ENDPOINT)
//                        .contentType(APPLICATION_JSON)
//                        .content(jsonRequest)
//                        .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
//                )
//                .andExpect(status().isCreated())
//                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
//                .andExpect(content().contentType(APPLICATION_JSON))
//                .andExpect(content().json(expectResponse));
    }

    @Test
    void shouldUpdateCustomer() throws Exception {
        var birthdate = of(1977, 3, 5, 12, 32);
        var request = new CustomerRequest("Update", "update@gmail.com", birthdate, MALE, "456", "DEF456");
        var jsonRequest = objectMapper.writeValueAsString(request);
        var expectResponse = "{\"id\":1,\"name\":\"Update\",\"email\":\"update@gmail.com\",\"birthdate\":\"1977-03-05 12:32:00\",\"gender\":\"MALE\",\"password\":\"456\",\"profileImageId\":\"DEF456\"}";

//        mockMvc
//                .perform(put(ENDPOINT_CUSTOMER_ID, 1)
//                        .contentType(APPLICATION_JSON)
//                        .content(jsonRequest)
//                        .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
//                )
//                .andExpect(status().isNoContent());
//
//        mockMvc
//                .perform(get(ENDPOINT_CUSTOMER_ID, 1))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
//                .andExpect(content().contentType(APPLICATION_JSON))
//                .andExpect(content().json(expectResponse));
    }

    @Test
    void shouldDeleteCustomerById() throws Exception {
//        var expectResponse = "[{\"id\":2,\"name\":\"João\",\"email\":\"joao@gmail.com\",\"birthdate\":\"1985-05-15 10:30:00\",\"gender\":\"MALE\",\"password\":\"987654321\",\"profileImageId\":\"image2\"},{\"id\":3,\"name\":\"Maria\",\"email\":\"maria@gmail.com\",\"birthdate\":\"1992-03-25 08:45:00\",\"gender\":\"FEMALE\",\"password\":\"qwertyui\",\"profileImageId\":\"image3\"},{\"id\":4,\"name\":\"Pedro\",\"email\":\"pedro@gmail.com\",\"birthdate\":\"1988-08-12 18:20:00\",\"gender\":\"MALE\",\"password\":\"asdfghjk\",\"profileImageId\":\"image4\"},{\"id\":5,\"name\":\"Ana\",\"email\":\"ana@gmail.com\",\"birthdate\":\"1995-10-04 14:55:00\",\"gender\":\"FEMALE\",\"password\":\"zxcvbnm\",\"profileImageId\":\"image5\"}]";
//
//        mockMvc
//                .perform(delete(ENDPOINT_CUSTOMER_ID, 1)
//                        .contentType(APPLICATION_JSON)
//                        .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
//                )
//                .andDo(print())
//                .andExpect(status().isNoContent());
//
//        mockMvc
//                .perform(get(ENDPOINT))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
//                .andExpect(content().contentType(APPLICATION_JSON))
//                .andExpect(jsonPath("$", hasSize(4)))
//                .andExpect(content().json(expectResponse));
    }
}
