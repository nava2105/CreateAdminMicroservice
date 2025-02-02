package cl.nava.springsecurityjwt.controllers;

import cl.nava.springsecurityjwt.dtos.*;
import cl.nava.springsecurityjwt.factories.IRoleFactory;
import cl.nava.springsecurityjwt.factories.IUserFactory;
import cl.nava.springsecurityjwt.models.RolesModel;
import cl.nava.springsecurityjwt.models.UsersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth/")
public class RestControllerAuth {
    private final PasswordEncoder passwordEncoder;
    private final IRoleFactory roleFactory;
    private final IUserFactory userFactory;

    @Autowired
    public RestControllerAuth(PasswordEncoder passwordEncoder, IRoleFactory roleFactory, IUserFactory userFactory) {
        this.passwordEncoder = passwordEncoder;
        this.roleFactory = roleFactory;
        this.userFactory = userFactory;
    }

    // Method to register users with the "ADMIN" role
    @PostMapping("register/admin")
    public ResponseEntity<String> registerAdmin(@RequestBody DtoRegister dtoRegister) {
        if (userFactory.existsByUserName(dtoRegister.getUsername())) {
            return new ResponseEntity<>("The user already exists, try another one", HttpStatus.BAD_REQUEST);
        }
        UsersModel users = new UsersModel();
        users.setUserName(dtoRegister.getUsername());
        users.setPassword(passwordEncoder.encode(dtoRegister.getPassword()));
        RolesModel roles = roleFactory.findByName("ADMIN").orElseThrow(() -> new IllegalArgumentException("Role ADMIN not found"));
        users.setRoles(Collections.singletonList(roles));
        userFactory.create(users);
        return new ResponseEntity<>("Successful admin registration", HttpStatus.OK);
    }
}