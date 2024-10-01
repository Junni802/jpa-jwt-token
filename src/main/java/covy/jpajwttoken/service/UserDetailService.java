package covy.jpajwttoken.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailService {
    UserDetails loadByUsername(String userEmail);
}
