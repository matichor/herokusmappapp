package com.herokusmallapp.security;

import com.herokusmallapp.entity.LoggedUser;
import com.herokusmallapp.repositories.LoggedUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private LoggedUserRepository applicationUserRepository;

    public UserDetailsServiceImpl(LoggedUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoggedUser applicationUser = applicationUserRepository.findByUsername(username);
        List<GrantedAuthority> authorities = applicationUser.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), authorities);
    }
}
