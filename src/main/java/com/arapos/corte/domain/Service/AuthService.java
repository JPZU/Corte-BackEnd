package com.arapos.corte.domain.Service;

import com.arapos.corte.domain.dto.User.CreateUserDTO;
import com.arapos.corte.web.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public String login(CreateUserDTO createUserDTO) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(createUserDTO.getEmail(), createUserDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(login);

//        System.out.println(authentication.isAuthenticated());
//        System.out.println(authentication.getPrincipal());

        String jwt = jwtUtil.create(createUserDTO.getEmail());

        return jwt;
    }
}
