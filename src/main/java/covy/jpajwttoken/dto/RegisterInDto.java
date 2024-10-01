package covy.jpajwttoken.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import covy.jpajwttoken.annotation.ValidEmail;
import covy.jpajwttoken.enums.Role;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegisterInDto {

    @JsonProperty(value = "name")
    @ValidEmail
    private String name;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "role")
    private Role role;

}
