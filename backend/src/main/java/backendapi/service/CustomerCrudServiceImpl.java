package backendapi.service;

import backendapi.dto.CustomerJson;
import backendapi.exception.ResourceNotFoundException;
import backendapi.mapper.CustomerMapper;
import backendapi.model.Customer;
import backendapi.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;
import static java.util.Collections.emptyList;
import static org.springframework.data.domain.Page.empty;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerCrudServiceImpl implements CrudService<CustomerJson> {

    private final CustomerMapper mapper;
    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerJson> findAll() {
        var customers = customerRepository.findAll();

        if (customers.isEmpty()) {
            log.info("Empty customers");
            return emptyList();
        }

        log.info("Successfully found customers");
        return mapper.from(customers);
    }

    @Override
    public Page<CustomerJson> findAll(Pageable pageable) {
        var customer = customerRepository.findAll(pageable);

        if (customer.isEmpty()) {
            log.info("Empty customer");
            return empty();
        }

        log.info("Successfully found customers");
        return mapper.from(customer);
    }

    @Override
    public CustomerJson findById(Integer id) {
        var customer = findCustomerByIdOrThrow(id);

        log.info("Successfully found customer with id {}", id);
        return mapper.from(customer);
    }

    @Override
    public CustomerJson save(CustomerJson request) {
        var customer = mapper.from(request);

        customerRepository.save(customer);

        log.info("Customer: {} saved", customer);
        return mapper.from(customer);
    }

    @Override
    public CustomerJson update(Integer id, CustomerJson request) {
        var customer = findCustomerByIdOrThrow(id);

        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setBirthdate(request.birthdate());
        customer.setGender(request.gender());
        customer.setPassword(request.password());
        customer.setProfileImageId(request.profileImageId());

        customerRepository.save(customer);

        log.info("Customer: {} updated", customer);
        return mapper.from(customer);
    }

    @Override
    public void deleteById(Integer id) {
        var customer = findCustomerByIdOrThrow(id);

        customerRepository.delete(customer);
        log.info("Customer with id {} deleted", id);
    }

    private Customer findCustomerByIdOrThrow(Integer id) {
        var customer = customerRepository.findById(id);

        if (customer.isEmpty()) {
            log.warn("Customer with id {} not found", id);
            throw new ResourceNotFoundException("Customer id: %d, not found", id);
        }

        return customer.get();
    }
}
