package de.schulung.todoTogether.Controller;

import org.springframework.web.bind.annotation.RestController;

import de.schulung.todoTogether.Model.MyUser;
import de.schulung.todoTogether.Model.MyUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class RegistrationController {

    @Autowired
    MyUserRepository myUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register/user")
    public MyUser postMethodName(@RequestBody MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return myUserRepository.save(user);
    }
    
}
