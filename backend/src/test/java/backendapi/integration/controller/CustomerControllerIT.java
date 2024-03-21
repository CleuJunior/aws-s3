package backendapi.integration.controller;

import backendapi.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerControllerIT extends IntegrationTest {

    @Autowired
    private CustomerRepository repository;
//    @Autowired
//    private MockMvc mockMvc;

//    @BeforeEach
//    void setUp() {
//        repository.saveAll(createCustomers());
//    }

    @Test
    void testtest() {
        assertThat(postgres.isCreated()).isTrue();
    }

}
