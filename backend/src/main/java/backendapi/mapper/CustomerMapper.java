package backendapi.mapper;

import backendapi.dto.CustomerJson;
import backendapi.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerMapper {

    public Customer from(CustomerJson request) {
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

    public CustomerJson from(Customer customer) {
        return new CustomerJson(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getBirthdate(),
                customer.getGender(),
                customer.getPassword(),
                customer.getProfileImageId()
        );
    }

    public List<CustomerJson> from(List<Customer> customer) {
        return customer
                .stream()
                .map(this::from)
                .toList();
    }

    public Page<CustomerJson> from(Page<Customer> customer) {
        return customer.map(this::from);
    }
}
