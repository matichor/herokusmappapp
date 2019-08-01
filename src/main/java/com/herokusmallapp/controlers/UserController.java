package com.herokusmallapp.controlers;

import com.herokusmallapp.entity.LoggedUser;
import com.herokusmallapp.entity.UserRole;
import com.herokusmallapp.repositories.LoggedUserRepository;
import com.herokusmallapp.repositories.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserRoleRepository userRoleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final LoggedUserRepository userRepository;



    @PostMapping("/adduser")
    private void addUser(@RequestBody LoggedUser loggedUser) {
        loggedUser.setPassword(this.bCryptPasswordEncoder.encode(loggedUser.getPassword()));
        this.userRepository.save(loggedUser);
    }


    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public String getHello() {
        LoggedUser loggedUser = userRepository.findOne(1L);
        UserRole userRole = new UserRole();
        userRole.setName("sasdas");
        userRoleRepository.save(userRole);
        return "Hello";
    }






}
