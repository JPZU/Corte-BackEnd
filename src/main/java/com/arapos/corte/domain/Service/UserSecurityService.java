package com.arapos.corte.domain.Service;

import com.arapos.corte.domain.repository.UserRepository;
import com.arapos.corte.persistence.crud.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {

    private final UserCrudRepository userCrudRepository;

    @Autowired
    public UserSecurityService(UserCrudRepository userCrudRepository) {
        this.userCrudRepository = userCrudRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.arapos.corte.persistence.entity.User userEntity = this.userCrudRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));

        return User.builder()
                .username(userEntity.getName())
                .password(userEntity.getPassword())
                .roles(userEntity.getRole().name())
                .build();
    }
}
