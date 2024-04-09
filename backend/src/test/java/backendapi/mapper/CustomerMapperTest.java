package backendapi.mapper;

import backendapi.dto.CustomerJson;
import backendapi.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import static backendapi.constants.CustomersDataConstant.WHAZT;
import static backendapi.constants.CustomersDataConstant.WHAZT_BIRTHDATE;
import static backendapi.constants.CustomersDataConstant.WHAZT_EMAIL;
import static backendapi.constants.CustomersDataConstant.WHAZT_GENDER;
import static backendapi.constants.CustomersDataConstant.WHAZT_ID;
import static backendapi.constants.CustomersDataConstant.WHAZT_PASSWORD;
import static backendapi.constants.CustomersDataConstant.WHAZT_PROFILE_IMAGE_ID;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.data.domain.PageRequest.of;

@ExtendWith(MockitoExtension.class)
class CustomerMapperTest {

    @InjectMocks
    private CustomerMapper mapper;

    private static final Customer customer = new Customer(WHAZT_ID, WHAZT, WHAZT_EMAIL, WHAZT_BIRTHDATE, WHAZT_GENDER, WHAZT_PASSWORD, WHAZT_PROFILE_IMAGE_ID);
    private static final Customer customerWithouId = new Customer(WHAZT, WHAZT_EMAIL, WHAZT_BIRTHDATE, WHAZT_GENDER, WHAZT_PASSWORD, WHAZT_PROFILE_IMAGE_ID);
    private static final CustomerJson jsonResponse = new CustomerJson(WHAZT_ID, WHAZT, WHAZT_EMAIL, WHAZT_BIRTHDATE, WHAZT_GENDER, WHAZT_PASSWORD, WHAZT_PROFILE_IMAGE_ID);
    private static final CustomerJson jsonRequest = new CustomerJson(WHAZT, WHAZT_EMAIL, WHAZT_BIRTHDATE, WHAZT_GENDER, WHAZT_PASSWORD, WHAZT_PROFILE_IMAGE_ID);

    @Test
    void shouldMapCustomerDtoToCustomer() {
        var result = mapper.from(jsonRequest);

        then(result).isNotNull();
        then(result).isEqualTo(customerWithouId);
    }

    @Test
    void shouldMapToCustomerToDto() {
        var result = mapper.from(customer);

        then(result).isNotNull();
        then(result).isEqualTo(jsonResponse);
    }

    @Test
    void shouldMapToCustomerToDtoList() {
        var custumers = singletonList(customer);
        var result = mapper.from(custumers);

        then(result).isNotNull();
        then(result).isEqualTo(singletonList(jsonResponse));
    }

    @Test
    void shouldMapToCustomerToDtoPage() {
        var custumers = singletonList(customer);
        var page = of(0, 1);
        var pagealbe = new PageImpl<>(custumers, page, 1);
        var result = mapper.from(pagealbe);

        then(result).isNotNull();
        then(result).hasSize(1);
        then(result.get()).isEqualTo(singletonList(jsonResponse));
    }

}