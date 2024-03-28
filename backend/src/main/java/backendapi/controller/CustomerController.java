package backendapi.controller;

import backendapi.dto.CustomerRequest;
import backendapi.dto.CustomerResponse;
import backendapi.service.CustomerCrudServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerCrudServiceImpl service;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getCustomers() {
        var body = service.findAll();
        return ResponseEntity.ok(body);
    }

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<CustomerResponse>> getCustomers(
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "size", required = false) int size) {

        var body = service.findAll(of(page, size));
        return ResponseEntity.ok(body);
    }

    @GetMapping("{customerId}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable("customerId") Integer customerId) {
        var body = service.findById(customerId);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> registerCustomer(@RequestBody CustomerRequest request) {
        var body = service.save(request);
        return ResponseEntity.status(CREATED).body(body);
    }

    @PutMapping("{customerId}")
    public ResponseEntity<Void> updateCustomer(@PathVariable("customerId") Integer customerId, @RequestBody CustomerRequest updateRequest) {
        service.update(customerId, updateRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") Integer customerId) {
        service.deleteById(customerId);
        return ResponseEntity.noContent().build();
    }
}
