package backendapi.dto;

import backendapi.model.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record CustomerResponse(Integer id,
                               String name,
                               String email,
                               @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                               LocalDateTime birthdate,
                               Gender gender,
                               String password,
                               String profileImageId) {
}
