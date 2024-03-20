package backendapi.factory;

import backendapi.model.Customer;

import java.util.List;

import static backendapi.model.Gender.FEMALE;
import static backendapi.model.Gender.MALE;
import static java.time.LocalDateTime.of;

public class CustomerFactory {

    public static List<Customer> createCustomers() {
        return List.of(
                new Customer("João", "joao@gmail.com", of(1985, 5, 15, 10, 30), MALE, "zzasddr", "456"),
                new Customer("Maria", "maria@gmail.com", of(1992, 3, 25, 8, 45), FEMALE, "qwertyui", "789"),
                new Customer("Pedro", "pedro@gmail.com", of(1988, 8, 12, 18, 20), MALE, "asdfghjk", "321"),
                new Customer("Ana", "ana@gmail.com", of(1995, 10, 4, 14, 55), FEMALE, "zxcvbnm", "654")
        );
    }
}
