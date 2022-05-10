package com.example.demo.controller;

import com.example.demo.exception.AlreadyExists;
import com.example.demo.exception.InvalidPasswordException;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JWT.JWTTokenProvider;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.web.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
public class AuthController {
   @Autowired
   private CustomUserDetailsService usServ;
   @Autowired
   private UserRepository usRep;
   @Autowired
   private JWTTokenProvider jwtTokenProvider;
   @Autowired
   private PasswordEncoder passwordEncoder;

   @PostMapping("/signin")
   public ResponseEntity signIn(@RequestBody AuthRequest request){

      try {
         String name = request.getUsername();
         String token = jwtTokenProvider.createToken(name,
                 usRep.findByUsername(name).orElseThrow(() -> new UsernameNotFoundException("User not found"))
                         .getRoles());

         String password =  request.getPassword();
         if (passwordEncoder.matches(password,usRep.findByUsername(name).get().getPassword())) {
            Map<Object, Object> model = new HashMap<>();
            model.put("username", name);
            model.put("token", token);

            return ResponseEntity.ok(model);
         }
         else
         {
            throw new InvalidPasswordException("Invalid password");
         }
      }
      catch (AuthenticationException|InvalidPasswordException|NoSuchElementException e)
      {
         throw new BadCredentialsException("Invalid username or password");
      }
   }

   @PostMapping("/newUser")
   public String addUser(@RequestBody AuthRequest request){
      try {
         String name = request.getUsername();
         String password = request.getPassword();
         usServ.saveUser(name,password);

         return "New user was successfully added";
      }
      catch (AlreadyExists e)
      {
         throw new ResponseStatusException(HttpStatus.NOT_MODIFIED,"User with with name already exist",e);
      }
   }
}
