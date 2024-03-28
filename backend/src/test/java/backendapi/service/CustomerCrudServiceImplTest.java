package backendapi.service;

import backendapi.dto.CustomerRequest;
import backendapi.dto.CustomerResponse;
import backendapi.mapper.CustomerMapper;
import backendapi.model.Customer;
import backendapi.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.data.domain.Page.empty;
import static org.springframework.data.domain.PageRequest.of;

@ExtendWith(MockitoExtension.class)
class CustomerCrudServiceImplTest {

    @Mock
    private Customer customer;
    @Mock
    private CustomerResponse customerResponse;
    @Mock
    private CustomerRequest customerRequest;
    @Mock
    private CustomerMapper mapper;
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerCrudServiceImpl service;

    private static final Integer ID = 1;

    @Test
    void shouldReturnAllCustomer() {
        var customers = singletonList(customer);

        given(customerRepository.findAll()).willReturn(customers);
        given(mapper.from(customers)).willReturn(singletonList(customerResponse));

        var result = service.findAll();

        then(result).hasSize(1);
        then(result).hasOnlyElementsOfTypes(CustomerResponse.class);
        then(result).containsOnly(customerResponse);

        verify(customerRepository).findAll();
        verify(mapper).from(customers);
        verifyNoMoreInteractions(customerRepository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldReturnEmptyListOfCustomer() {
        given(customerRepository.findAll()).willReturn(emptyList());

        var result = service.findAll();

        then(result).isEmpty();

        verify(customerRepository).findAll();
        verifyNoMoreInteractions(customerRepository);
        verifyNoInteractions(mapper);
    }

    @Test
    void shouldReturnAllPagedCustomer() {
        var page = of(0, 1);
        var customerPage = new PageImpl<>(singletonList(customer), page, 1);
        var customerPageResponse = new PageImpl<>(singletonList(customerResponse), page, 1);

        given(customerRepository.findAll(page)).willReturn(customerPage);
        given(mapper.from(customerPage)).willReturn(customerPageResponse);

        var result = service.findAll(page);

        then(result).hasSize(1);
        then(result).hasOnlyElementsOfTypes(CustomerResponse.class);
        then(result).containsOnly(customerResponse);

        verify(customerRepository).findAll(page);
        verify(mapper).from(customerPage);
        verifyNoMoreInteractions(customerRepository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldReturnEmptyPageOfCustomer() {
        var page = of(0, 1);

        given(customerRepository.findAll(page)).willReturn(empty());

        var result = service.findAll(page);

        then(result).isEmpty();

        verify(customerRepository).findAll(page);
        verifyNoMoreInteractions(customerRepository);
        verifyNoInteractions(mapper);
    }

    @Test
    void shouldReturnCustomerById() {
        given(customerRepository.findById(ID)).willReturn(Optional.of(customer));
        given(mapper.from(customer)).willReturn(customerResponse);

        var result = service.findById(ID);

        then(result).isNotNull();
        then(result).isInstanceOf(CustomerResponse.class);
        then(result).isEqualTo(customerResponse);

        verify(customerRepository).findById(ID);
        verify(mapper).from(customer);
        verifyNoMoreInteractions(customerRepository);
        verifyNoMoreInteractions(mapper);
    }

    @Test
    void shouldThrowErrorWhenFindByIdNotFound() {
        given(customerRepository.findById(ID)).willReturn(Optional.empty());


        assertThatThrownBy(() -> service.findById(ID))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Customer with id " + ID + " not found");

        verifyNoInteractions(mapper);
    }

    @Test
    void shouldSaveCustomer() {
        given(mapper.from(customerRequest)).willReturn(customer);
        given(customerRepository.save(customer)).willReturn(customer);
        given(mapper.from(customer)).willReturn(customerResponse);

        var result = service.save(customerRequest);

        then(result).isNotNull();
        then(result).isInstanceOf(CustomerResponse.class);
        then(result).isEqualTo(customerResponse);

        verify(mapper).from(customerRequest);
        verify(customerRepository).save(customer);
        verify(mapper).from(customer);
        verifyNoMoreInteractions(mapper);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void shouldUpdateCustomer() {
        given(customerRepository.save(customer)).willReturn(customer);
        given(customerRepository.findById(ID)).willReturn(Optional.of(customer));
        given(mapper.from(customer)).willReturn(customerResponse);

        var result = service.update(ID, customerRequest);

        then(result).isNotNull();
        then(result).isInstanceOf(CustomerResponse.class);
        then(result).isEqualTo(customerResponse);

        verify(customerRepository).save(customer);
        verify(mapper).from(customer);
        verifyNoMoreInteractions(mapper);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void shouldThrowErrorWhenUpdateIdNotFound() {
        given(customerRepository.findById(ID)).willReturn(Optional.empty());

        assertThatThrownBy(() -> service.update(ID, customerRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Customer with id " + ID + " not found");

        verifyNoInteractions(mapper);
    }

    @Test
    void shouldDeleteCustomer() {
        given(customerRepository.findById(ID)).willReturn(Optional.of(customer));
        willDoNothing().given(customerRepository).delete(customer);

        service.deleteById(ID);

        verify(customerRepository).findById(ID);
        verify(customerRepository).delete(customer);
    }

    @Test
    void shouldThrowErrorWhenDeleteIdNotFound() {
        given(customerRepository.findById(ID)).willReturn(Optional.empty());

        assertThatThrownBy(() -> service.deleteById(ID))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Customer with id " + ID + " not found");
    }
}