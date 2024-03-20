package backendapi.repository;

import backendapi.model.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
