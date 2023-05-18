package com.mryzhan.service.impl;

import com.mryzhan.entity.User;
import com.mryzhan.entity.common.UserPrincipal;
import com.mryzhan.repository.UserRepository;
import com.mryzhan.service.SecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final UserRepository userRepository;

    public SecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username);

        if(user.equals(null)) {
            throw new UsernameNotFoundException("This user doeesn`t exist");
        }
        return new UserPrincipal(user);
    }
}
