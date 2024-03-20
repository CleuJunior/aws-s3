package backendapi.dto;

import backendapi.model.Gender;

import java.time.LocalDateTime;

public record CustomerResponse(Integer id,
                               String name,
                               String email,
                               LocalDateTime birthdate,
                               Gender gender,
                               String password,
                               String profileImageId) {
}
