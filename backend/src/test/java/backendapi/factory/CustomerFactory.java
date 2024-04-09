package backendapi.factory;

import backendapi.dto.CustomerJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static backendapi.constants.CustomersDataConstant.ANA;
import static backendapi.constants.CustomersDataConstant.ANA_BIRTHDATE;
import static backendapi.constants.CustomersDataConstant.ANA_EMAIL;
import static backendapi.constants.CustomersDataConstant.ANA_GENDER;
import static backendapi.constants.CustomersDataConstant.ANA_ID;
import static backendapi.constants.CustomersDataConstant.ANA_PASSWORD;
import static backendapi.constants.CustomersDataConstant.ANA_PROFILE_IMAGE_ID;
import static backendapi.constants.CustomersDataConstant.JOAO;
import static backendapi.constants.CustomersDataConstant.JOAO_BIRTHDATE;
import static backendapi.constants.CustomersDataConstant.JOAO_EMAIL;
import static backendapi.constants.CustomersDataConstant.JOAO_GENDER;
import static backendapi.constants.CustomersDataConstant.JOAO_ID;
import static backendapi.constants.CustomersDataConstant.JOAO_PASSWORD;
import static backendapi.constants.CustomersDataConstant.JOAO_PROFILE_IMAGE_ID;
import static backendapi.constants.CustomersDataConstant.MARIA;
import static backendapi.constants.CustomersDataConstant.MARIA_BIRTHDATE;
import static backendapi.constants.CustomersDataConstant.MARIA_EMAIL;
import static backendapi.constants.CustomersDataConstant.MARIA_GENDER;
import static backendapi.constants.CustomersDataConstant.MARIA_ID;
import static backendapi.constants.CustomersDataConstant.MARIA_PASSWORD;
import static backendapi.constants.CustomersDataConstant.MARIA_PROFILE_IMAGE_ID;
import static backendapi.constants.CustomersDataConstant.PEDRO;
import static backendapi.constants.CustomersDataConstant.PEDRO_BIRTHDATE;
import static backendapi.constants.CustomersDataConstant.PEDRO_EMAIL;
import static backendapi.constants.CustomersDataConstant.PEDRO_GENDER;
import static backendapi.constants.CustomersDataConstant.PEDRO_ID;
import static backendapi.constants.CustomersDataConstant.PEDRO_PASSWORD;
import static backendapi.constants.CustomersDataConstant.PEDRO_PROFILE_IMAGE_ID;
import static backendapi.constants.CustomersDataConstant.WHAZT;
import static backendapi.constants.CustomersDataConstant.WHAZT_BIRTHDATE;
import static backendapi.constants.CustomersDataConstant.WHAZT_EMAIL;
import static backendapi.constants.CustomersDataConstant.WHAZT_GENDER;
import static backendapi.constants.CustomersDataConstant.WHAZT_ID;
import static backendapi.constants.CustomersDataConstant.WHAZT_PASSWORD;
import static backendapi.constants.CustomersDataConstant.WHAZT_PROFILE_IMAGE_ID;
import static java.lang.Math.min;
import static java.util.List.of;

@Component
@RequiredArgsConstructor
public class CustomerFactory {

    private final ObjectMapper objectMapper;
    private final List<CustomerJson> customers = of(
            new CustomerJson(WHAZT_ID, WHAZT, WHAZT_EMAIL, WHAZT_BIRTHDATE, WHAZT_GENDER, WHAZT_PASSWORD, WHAZT_PROFILE_IMAGE_ID),
            new CustomerJson(JOAO_ID, JOAO, JOAO_EMAIL, JOAO_BIRTHDATE, JOAO_GENDER, JOAO_PASSWORD, JOAO_PROFILE_IMAGE_ID),
            new CustomerJson(MARIA_ID, MARIA, MARIA_EMAIL, MARIA_BIRTHDATE, MARIA_GENDER, MARIA_PASSWORD, MARIA_PROFILE_IMAGE_ID),
            new CustomerJson(PEDRO_ID, PEDRO, PEDRO_EMAIL, PEDRO_BIRTHDATE, PEDRO_GENDER, PEDRO_PASSWORD, PEDRO_PROFILE_IMAGE_ID),
            new CustomerJson(ANA_ID, ANA, ANA_EMAIL, ANA_BIRTHDATE, ANA_GENDER, ANA_PASSWORD, ANA_PROFILE_IMAGE_ID)
    );

    public String buildCustomersAsString() throws JsonProcessingException {
        return objectMapper.writeValueAsString(customers);
    }

    public String buildCustomersPageAsString(int page, int size) throws JsonProcessingException {
        var pageCustomer = PageRequest.of(page, size);
        var start = (int) pageCustomer.getOffset();
        var end = min((start + pageCustomer.getPageSize()), customers.size());
        var pageResponse = new PageImpl<>(customers.subList(start, end), pageCustomer, 5);

        return objectMapper.writeValueAsString(pageResponse);
    }

    public String buildCustomerAsStringById(int id) throws JsonProcessingException {
        var index = zeroIndexBase(id);
        var response = customers.get(index);

        return objectMapper.writeValueAsString(response);
    }

    public String buildCustomerAsStringDeleteById(int id) throws JsonProcessingException {
        var response = new ArrayList<>(customers);
        var index = zeroIndexBase(id);

        response.remove(index);

        return objectMapper.writeValueAsString(response);
    }

    public String buildCustomerRequestAsString(CustomerJson request) throws JsonProcessingException {
        return objectMapper.writeValueAsString(request);
    }

    public String buildCustomerResponseAsString(CustomerJson response) throws JsonProcessingException {
        return objectMapper.writeValueAsString(response);
    }

    public String buildCustomerAsStringFromRequestToResponse(int id, CustomerJson request) throws JsonProcessingException {
        var responese = new CustomerJson(
                id,
                request.name(),
                request.email(),
                request.birthdate(),
                request.gender(),
                request.password(),
                request.profileImageId()
        );

        return objectMapper.writeValueAsString(responese);
    }

    private int zeroIndexBase(int value) {
        return value - 1;
    }
}
