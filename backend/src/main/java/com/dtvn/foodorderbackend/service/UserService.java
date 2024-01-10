package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.config.ApplicationConfig;
import com.dtvn.foodorderbackend.mapper.Mapper;
import com.dtvn.foodorderbackend.model.dto.UserDTO;
import com.dtvn.foodorderbackend.model.entity.RegisterOtp;
import com.dtvn.foodorderbackend.model.entity.User;
import com.dtvn.foodorderbackend.model.request.*;
import com.dtvn.foodorderbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    UserRepository userRepository;
    Mapper mapper;
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
    void setMapper(Mapper mapper) {
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

    public User loadUserById(long id){
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

    public void changePassword(UserChangePasswordRequest request) {
        UserDetails user = loadUserByUsername(request.getEmail());
        String password = user.getPassword();
//        if(request.getOldPassword().equals())
        // TODO: change password
    }

    public List<UserDTO> getUserByCriteria(String fullName, String email, User.Role role, User.Status status) {
        List<User> users = userRepository.getUserByCriteria(fullName, email, role, status);
        return mapper.mapList(users, UserDTO.class);
    }


    public void verifiedRegister(String email) {
        userRepository.updateStatusByEmail(User.Status.VERIFIED, email);
    }

    public List<User> getUserNotApproved(){
        return userRepository.findUserByApprovedAndStatus(false, User.Status.VERIFIED);
    }
    public List<User> getUserApproved(){
        return userRepository.findUserByApprovedAndStatus(true,User.Status.VERIFIED);
    }

    public void changeApprovedByEmail(String email, boolean approved) {
        userRepository.changeApprovedByEmail(email,approved);
    }
}
