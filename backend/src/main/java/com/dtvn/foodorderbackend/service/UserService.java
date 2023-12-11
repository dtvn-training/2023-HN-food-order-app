package com.dtvn.foodorderbackend.service;

import com.dtvn.foodorderbackend.mapper.BaseMapper;
import com.dtvn.foodorderbackend.model.entity.User;
import com.dtvn.foodorderbackend.model.request.UserRegisterRequest;
import com.dtvn.foodorderbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    BaseMapper mapper;

    @Autowired
    void setMapper(BaseMapper mapper){
        this.mapper = mapper;
    }
    public boolean checkUserExist(String username){
        return userRepository.existsUserByUsername(username);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username).orElseThrow();
    }

    public UserRegisterRequest register(UserRegisterRequest request) {
        User user = mapper.map(request,User.class);
        user.setRole(User.Role.USER);
        user.setStatus(User.Status.NOT_VERIFY);
        user.setLoan(0L);
        userRepository.save(user);
        return request;
    }

}
