package com.rangjin.restapi.service;

import com.rangjin.restapi.advice.exception.CustomUserNotFoundException;
import com.rangjin.restapi.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String pk) throws UsernameNotFoundException {
        return userRepository.findById(Long.valueOf(pk)).orElseThrow(CustomUserNotFoundException::new);
    }

}
