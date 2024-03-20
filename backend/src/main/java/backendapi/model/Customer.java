package backendapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Customer {

    @Id
    @SequenceGenerator(name = "customer_id_seq", sequenceName = "customer_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "customer_id_seq")
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private LocalDateTime birthdate;
    @Column(nullable = false)
    @Enumerated(STRING)
    private Gender gender;
    @Column(nullable = false)
    private String password;
    @Column(unique = true)
    private String profileImageId;

    public Customer(String name, String email, LocalDateTime birthdate, Gender gender, String password, String profileImageId) {
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
        this.gender = gender;
        this.password = password;
        this.profileImageId = profileImageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(birthdate, customer.birthdate) &&
                gender == customer.gender &&
                Objects.equals(password, customer.password) &&
                Objects.equals(profileImageId, customer.profileImageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, birthdate, gender, password, profileImageId);
    }

}
