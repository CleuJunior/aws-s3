package backendapi.integration;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class TestcontainersTest extends IntegrationTest {

    @Test
    void canStartPostgresDB() {
        then(postgres.isRunning()).isTrue();
        then(postgres.isCreated()).isTrue();
    }
}
