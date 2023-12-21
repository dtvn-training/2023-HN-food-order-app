package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.mapper.BaseMapper;
import com.dtvn.foodorderbackend.model.dto.UserDTO;
import com.dtvn.foodorderbackend.model.entity.User;
import com.dtvn.foodorderbackend.model.request.UserChangePasswordRequest;
import com.dtvn.foodorderbackend.model.request.UserRegisterRequest;
import com.dtvn.foodorderbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

    @Autowired void setUserRepository(UserRepository userRepository){
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
        return userRepository.existsUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username).orElseThrow();
    }

    public UserRegisterRequest register(UserRegisterRequest request) {
        User user = mapper.map(request, User.class);
        user.setRole(User.Role.USER);
        user.setStatus(User.Status.NOT_VERIFY);
        user.setBalance(0);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return request;
    }
    public void changePassword(UserChangePasswordRequest request){
        // TODO;
    }

    public List<UserDTO> getUserByCriteria(String fullName, String username, User.Role role, User.Status status) {
        List<User> users =  userRepository.getUserByCriteria(fullName,username,role,status);
        return mapper.mapList(users,UserDTO.class);
    }

    public void changeStatusByUsername(String username,User.Status status) {
        userRepository.changeStatusByUsername(username,status);
    }
}
