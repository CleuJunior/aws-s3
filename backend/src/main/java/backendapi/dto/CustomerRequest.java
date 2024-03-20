package backendapi.dto;

import backendapi.model.Gender;

import java.time.LocalDateTime;

public record CustomerRequest(String name,
                              String email,
                              LocalDateTime birthdate,
                              Gender gender,
                              String password,
                              String profileImageId) {
}
