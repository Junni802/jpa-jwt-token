package covy.jpajwttoken.controller;

import covy.jpajwttoken.dto.AccessToken;
import covy.jpajwttoken.dto.LoginInDto;
import covy.jpajwttoken.dto.RegisterInDto;
import covy.jpajwttoken.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

    private AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AccessToken> register(
        @RequestBody @Valid RegisterInDto inDto
    ) {
        return ResponseEntity.ok(authService.register(inDto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AccessToken> authenticate(
        @RequestBody LoginInDto inDto
    ) {
        return ResponseEntity.ok(authService.login(inDto));
    }

}
