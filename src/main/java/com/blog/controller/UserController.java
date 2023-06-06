package com.blog.controller;

import com.blog.config.JwtService;
import com.blog.payloads.AuthReq;
import com.blog.payloads.UserDto;
import com.blog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/user/{user_id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<UserDto> getUserId(@PathVariable Integer user_id){
        UserDto user = userService.getUserById(user_id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/user/post")
    public ResponseEntity<UserDto> postUser(@Valid @RequestBody UserDto user){
        UserDto new_user = this.userService.createUser(user);
        return new ResponseEntity<>(new_user, HttpStatus.CREATED);
    }

    @PutMapping("/user/{user_id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto user , @PathVariable Integer user_id){
        UserDto updated_user = userService.updateUser(user,user_id);
        return new ResponseEntity<>(updated_user, HttpStatus.OK);
    }

    @DeleteMapping("/user/{user_id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer user_id){
         userService.deleteUser(user_id);
         return ResponseEntity.ok(Map.of("message","User Deleted successfully"));
    }

    @PostMapping("/auth")
    public ResponseEntity<String> auth(@RequestBody AuthReq authReq){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.getUsername(),authReq.getPassword()));
        if(authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authReq.getUsername());
            return ResponseEntity.ok(token);
        }
        else{
            throw new UsernameNotFoundException("User not found or Invalid Credentials");
        }
    }
}
