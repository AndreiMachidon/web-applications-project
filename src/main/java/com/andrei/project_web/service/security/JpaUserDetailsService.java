package com.andrei.project_web.service.security;

import com.andrei.project_web.domain.security.Authority;
import com.andrei.project_web.domain.security.User;
import com.andrei.project_web.repositories.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Profile("mysql")
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;

        Optional<User> userOpt= userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            user = userOpt.get();
        } else {
            throw new UsernameNotFoundException("Username: " + username);
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),user.getEnabled(), user.getAccountNonExpired(),
                user.getCredentialsNonExpired(),user.getAccountNonLocked(),
                getAuthorities(Set.of(user.getAuthority())));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<Authority> authorities) {
        if (authorities == null){
            return new HashSet<>();
        } else if (authorities.isEmpty()){
            return new HashSet<>();
        }
        else{
            return authorities.stream()
                    .map(Authority::getRole)
                    .map(Objects::toString)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
        }
    }
}
