package covy.jpajwttoken.service;

import covy.jpajwttoken.dto.AccessToken;
import covy.jpajwttoken.dto.LoginInDto;
import covy.jpajwttoken.dto.RegisterInDto;

public interface AuthenticationService {

    AccessToken register(RegisterInDto inDto);
    AccessToken login(LoginInDto inDto);

}
