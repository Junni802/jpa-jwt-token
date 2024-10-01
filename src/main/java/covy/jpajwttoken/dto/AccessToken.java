package covy.jpajwttoken.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessToken {

    /**
     * 토큰정보
     */
    @JsonProperty(value = "access_token")
    private String accessToken;

}
