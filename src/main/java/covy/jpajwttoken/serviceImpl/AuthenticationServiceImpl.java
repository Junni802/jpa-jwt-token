package covy.jpajwttoken.serviceImpl;

import covy.jpajwttoken.dto.AccessToken;
import covy.jpajwttoken.dto.LoginInDto;
import covy.jpajwttoken.dto.RegisterInDto;
import covy.jpajwttoken.enums.Role;
import covy.jpajwttoken.model.User;
import covy.jpajwttoken.repository.UserRepository;
import covy.jpajwttoken.service.AuthenticationService;
import covy.jpajwttoken.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * 사용자 정보 저장 및 토큰 발행
     *
     * <pre>
     *     1. 사용자 등록요청이 들어오면 실제 사용자 정보를 저장한다
     *     2. 사용자 정보가 등록이 완료되면 Token을 발급한다.
     * </pre>
     *
     * @param inDto
     * @return
     */
    @Override
    public AccessToken register(RegisterInDto inDto) {
        User user = User
                .builder()
                .name(inDto.getName())
                .email(inDto.getEmail())
                .password(passwordEncoder.encode(inDto.getPassword()))
                .build();

        // 사용자 정보 저장
        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);

        return AccessToken
                .builder()
                .accessToken(jwtToken)
                .build();
    }

    /**
     * 로그인 했을 경우 Token발급을 위한 메서드
     *
     * <pre>
     *     1. 사용자 로그인 요청이 들어왔을 경우 실제 존재하는 회원인지 확인
     *     2. 회원 인증시 토큰 발급
     * </pre>
     *
     * @param inDto
     * @return
     */
    @Override
    public AccessToken login(LoginInDto inDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        inDto.getEmail(),
                        inDto.getPassword()
                )
        );

        // 사용자 정보를 DB에서 추출한다
        User user = userRepository
                .findByEmail(inDto.getEmail())
                .orElseThrow();

        String accessToken = jwtService.generateToken(user);

        return AccessToken
                .builder()
                .accessToken(accessToken)
                .build();
    }
}
