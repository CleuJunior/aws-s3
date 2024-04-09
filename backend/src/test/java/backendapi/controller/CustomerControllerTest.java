package backendapi.controller;

import backendapi.dto.CustomerJson;
import backendapi.service.CustomerCrudServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static backendapi.constants.HttpStatusAndMessage.CREATED;
import static backendapi.constants.HttpStatusAndMessage.NO_CONTENT;
import static backendapi.constants.HttpStatusAndMessage.OK;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.data.domain.PageRequest.of;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerCrudServiceImpl service;
    @Mock
    private CustomerJson customerJson;
    @InjectMocks
    private CustomerController controller;
    private List<CustomerJson> customersJson;
    private static final Integer ID = 1;

    @BeforeEach
    void setUp() {
        customersJson = singletonList(customerJson);
    }

    @Test
    void shouldGetCostumerAndStatusCodeOk() {
        given(service.findAll()).willReturn(customersJson);

        var result = controller.getCustomers();

        then(result).isNotNull();
        then(result.getStatusCode().value()).isEqualTo(OK);
        then(result.getBody()).isEqualTo(customersJson);

        verify(service).findAll();
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldGetCostumerByPageAndSizeAndStatusCodeOk() {
        var page = of(0, 1);
        var pageableCustomer = new PageImpl<>(customersJson, page, 1);

        given(service.findAll(page)).willReturn(pageableCustomer);

        var result = controller.getCustomers(0, 1);

        then(result).isNotNull();
        then(result.getStatusCode().value()).isEqualTo(OK);
        then(result.getBody()).isEqualTo(pageableCustomer);

        verify(service).findAll(page);
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldReturnCustomerById() {
        given(service.findById(ID)).willReturn(customerJson);

        var result = controller.getCustomerById(ID);

        then(result).isNotNull();
        then(result.getStatusCode().value()).isEqualTo(OK);
        then(result.getBody()).isEqualTo(customerJson);

        verify(service).findById(ID);
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldRegisterCustomer() {
        given(service.save(customerJson)).willReturn(customerJson);

        var result = controller.registerCustomer(customerJson);

        then(result).isNotNull();
        then(result.getStatusCode().value()).isEqualTo(CREATED);
        then(result.getBody()).isEqualTo(customerJson);

        verify(service).save(customerJson);
        verifyNoMoreInteractions(service);
    }

    @Test
    void shouldUpdateCustomer() {
        given(service.update(ID, customerJson)).willReturn(customerJson);

        var result = controller.updateCustomer(ID, customerJson);

        then(result).isNotNull();
        then(result.getStatusCode().value()).isEqualTo(NO_CONTENT);
        then(result.getBody()).isNull();

        verify(service).update(ID, customerJson);
        verifyNoMoreInteractions(service);

    }

    @Test
    void shouldDeleteCustomer() {
        willDoNothing().given(service).deleteById(ID);

        var result = controller.deleteCustomer(ID);

        then(result).isNotNull();
        then(result.getStatusCode().value()).isEqualTo(NO_CONTENT);
        then(result.getBody()).isNull();

        verify(service).deleteById(ID);
        verifyNoMoreInteractions(service);
    }
}