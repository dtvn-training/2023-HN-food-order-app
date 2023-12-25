package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.config.ApplicationConfig;
import com.dtvn.foodorderbackend.mapper.BaseMapper;
import com.dtvn.foodorderbackend.model.dto.UserDTO;
import com.dtvn.foodorderbackend.model.entity.RegisterOtp;
import com.dtvn.foodorderbackend.model.entity.User;
import com.dtvn.foodorderbackend.model.request.UserChangePasswordRequest;
import com.dtvn.foodorderbackend.model.request.UserRegisterRequest;
import com.dtvn.foodorderbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    UserRepository userRepository;
    BaseMapper mapper;
    PasswordEncoder encoder;

    OtpService otpService;
    Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    void setOtpService(OtpService otpService) {
        this.otpService = otpService;
    }

    @Autowired
    void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    void setMapper(BaseMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public boolean checkUserExist(String username) {
        return userRepository.existsUserByEmail(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username).orElseThrow();
    }

    public String register(UserRegisterRequest request) {
        try {
            User user = mapper.map(request, User.class);
            user.setRole(User.Role.USER);
            user.setStatus(User.Status.NOT_VERIFY);
            user.setBalance(0);
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            RegisterOtp otp = otpService.generateAndSaveRegisterOTP(user.getEmail());
            otpService.sendRegisterOTP(otp);
            return "Success, Your OTP will expired in " + ApplicationConfig.EXPIRED_REGISTER_OTP / 1000 / 60 + " minutes";
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return "Cannot send your OTP, cause by some internal Error";
    }

    public void changePassword(UserChangePasswordRequest request) {
        UserDetails user = loadUserByUsername(request.getEmail());
        String password = user.getPassword();
//        if(request.getOldPassword().equals())
    }

    public List<UserDTO> getUserByCriteria(String fullName, String email, User.Role role, User.Status status) {
        List<User> users = userRepository.getUserByCriteria(fullName, email, role, status);
        return mapper.mapList(users, UserDTO.class);
    }

    public void changeStatusByEmail(String email, User.Status status) {
        userRepository.changeStatusByEmail(email, status);
    }

    public void verifiedRegister(String email) {
        userRepository.updateStatusByEmail(User.Status.VERIFIED, email);
    }
}
