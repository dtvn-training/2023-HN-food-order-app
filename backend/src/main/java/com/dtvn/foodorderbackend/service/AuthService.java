package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.model.entity.User;
import com.dtvn.foodorderbackend.model.request.UserLoginRequest;
import com.dtvn.foodorderbackend.model.response.AuthResponse;
import com.dtvn.foodorderbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    final AuthenticationManager authManager;
    final UserRepository userRepository;
    final JwtService jwtService;
    final PasswordEncoder encoder;
    Logger logger = LoggerFactory.getLogger(AuthService.class);

    public AuthResponse login(@NonNull UserLoginRequest request) {
        // TODO: let AuthenticationManager
        System.out.println("Before AuthenticationManager do auth");
//        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        System.out.println("If AuthenticationManager auth success, this line will appear");
        User user = userRepository.findUserByEmailAndStatus(request.getEmail(), User.Status.VERIFIED);
        if (user == null || !encoder.matches(request.getPassword(), user.getPassword())) {
            logger.info("failed to load user {}",request);
            return null;
        }
        String token = jwtService.generateToken(user);
        return new AuthResponse(user.getId(), user.getUsername(), token);
    }
}
