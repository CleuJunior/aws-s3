package backendapi;

import backendapi.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static backendapi.factory.CustomerFactory.createCustomers;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerIT extends IntegrationTest {

    @Autowired
    private CustomerRepository repository;

    @BeforeEach
    void setUp() {
        repository.saveAll(createCustomers());
    }

    @Test
    void testtest() {
        assertThat(postgres.isCreated()).isTrue();
    }

}
