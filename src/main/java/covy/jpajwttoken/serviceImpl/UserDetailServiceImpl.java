package covy.jpajwttoken.serviceImpl;

import covy.jpajwttoken.repository.UserRepository;
import covy.jpajwttoken.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailService {

    private final UserRepository userRepository;

    /**
     * 사용자 정보를 추출하는 Id
     *
     * @param userEmail
     * @return
     */
    @Override
    public UserDetails loadByUsername(String userEmail) {
        return userRepository
                .findByEmail(userEmail)
                .orElseThrow();
    }
}
