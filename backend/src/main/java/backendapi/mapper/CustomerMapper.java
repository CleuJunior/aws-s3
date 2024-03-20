package backendapi.mapper;

import backendapi.dto.CustomerRequest;
import backendapi.dto.CustomerResponse;
import backendapi.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerMapper {

    public Customer from(CustomerRequest request) {
        return Customer
                .builder()
                .name(request.name())
                .email(request.email())
                .birthdate(request.birthdate())
                .gender(request.gender())
                .password(request.password())
                .profileImageId(request.profileImageId())
                .build();
    }

    public CustomerResponse from(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getBirthdate(),
                customer.getGender(),
                customer.getPassword(),
                customer.getProfileImageId()
        );
    }

    public List<CustomerResponse> from(List<Customer> customer) {
        return customer
                .stream()
                .map(this::from)
                .toList();
    }

    public Page<CustomerResponse> from(Page<Customer> customer) {
        return customer.map(this::from);
    }
}
