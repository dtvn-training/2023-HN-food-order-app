package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.config.ApplicationConfig;
import com.dtvn.foodorderbackend.mapper.Mapper;
import com.dtvn.foodorderbackend.model.dto.request.UserChangePasswordRequest;
import com.dtvn.foodorderbackend.model.dto.request.UserRegisterRequest;
import com.dtvn.foodorderbackend.model.dto.request.cart.UserResetPasswordRequest;
import com.dtvn.foodorderbackend.model.dto.response.UserDTO;
import com.dtvn.foodorderbackend.model.entity.RegisterOtp;
import com.dtvn.foodorderbackend.model.entity.User;
import com.dtvn.foodorderbackend.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
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
    HttpServletRequest httpServletRequest;
    UserRepository userRepository;
    Mapper mapper;
    PasswordEncoder encoder;
    OtpService otpService;
    final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    void setOtpService(OtpService otpService) {
        this.otpService = otpService;
    }

    @Autowired
    void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Autowired
    void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public boolean checkUserExist(String username) {
        return userRepository.existsByEmail(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username).orElseThrow();
    }
    public User findUserByUsername(String username){
        return userRepository.findUserByEmail(username).orElse(null);
    }

    public User loadUserById(long id) {
        return userRepository.findUserById(id).orElseThrow();
    }

    public String register(UserRegisterRequest request) {
        try {
//            logger.info("request is: {}",request);
            User user = mapper.map(request, User.class);
            user.setRole(User.Role.USER);
            user.setStatus(User.Status.NOT_VERIFY);
            user.setBalance(0);
            user.setPassword(encoder.encode(user.getPassword()));
//            logger.info("user mapped is; {}",user);
            userRepository.save(user);
            RegisterOtp otp = otpService.generateAndSaveRegisterOTP(user.getEmail());
            otpService.sendRegisterOTP(otp);
            return "Success, Your OTP will expired in " + ApplicationConfig.EXPIRED_REGISTER_OTP / 1000 / 60 + " minutes";
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return "Cannot send your OTP, cause by some internal Error";
    }

    public boolean changePassword(UserChangePasswordRequest request) {
        User user = userRepository.findUserByEmail(request.getEmail()).orElse(null);
        if (user == null) {
            return false;
        }
        if (user.getId() != Long.parseLong(String.valueOf(httpServletRequest.getAttribute("user_id")))) {
            return false;
        }

        String password = user.getPassword();
        if (!encoder.matches(request.getOldPassword(), password)) {
            return false;
        }
        changePassword(user, request.getNewPassword());
        return true;
    }

    public boolean changePassword(UserResetPasswordRequest userResetPasswordRequest) {
        User user = userRepository.findUserByEmail(userResetPasswordRequest.getEmail()).orElse(null);
        if (user == null) {
            return false;
        }
        if (!otpService.checkResetPasswordOtp(userResetPasswordRequest.getEmail(), userResetPasswordRequest.getOtp())) {
            return false;
        }
        otpService.destroyResetPasswordOtp(user.getEmail(), userResetPasswordRequest.getOtp());
        changePassword(user, userResetPasswordRequest.getNewPassword());
        return true;
    }

    public void changePassword(User user, String newPassword) {
        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);
    }


    public List<UserDTO> getUserByCriteria(String fullName, String email, User.Role role, User.Status status) {
        List<User> users = userRepository.getUserByCriteria(fullName, email, role, status);
        return mapper.mapList(users, UserDTO.class);
    }

    public List<User> getUserVerified(){
        return userRepository.findAllByStatus(User.Status.VERIFIED);
    }

    public void verifiedRegister(String email) {
        userRepository.updateStatusByEmail(User.Status.VERIFIED, email);
    }

    public List<User> getUserNotApproved() {
        return userRepository.findUserByApprovedAndStatus(false, User.Status.VERIFIED);
    }

    public List<User> getUserApproved() {
        return userRepository.findUserByApprovedAndStatus(true, User.Status.VERIFIED);
    }

    public void changeApprovedByEmail(String email, boolean approved) {
        userRepository.changeApprovedByEmail(email, approved);
    }

}
