package com.lzw.securitydome.controller.user;

import com.lzw.securitydome.common.JsonData;
import com.lzw.securitydome.param.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    //@Autowired
    //private UserService userService;

    @GetMapping("/login")
    public JsonData login( LoginRequest loginRequest, HttpSession session) {
        System.out.println("===========login=====");
        
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
   
        return JsonData.success();
    }



    @PreAuthorize("isAuthenticated()")
    @PostMapping("/logout")
    public JsonData logout() {
        SecurityContextHolder.clearContext();
        return JsonData.success();
    }


}
