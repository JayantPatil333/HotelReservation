package com.zuul.controller;

import com.zuul.model.AuthenticationRequest;
import com.zuul.model.AuthenticationResponse;
import com.zuul.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.zuul.jwt.JwtUtil;

@RestController
@RequestMapping("/v1")
public class SecurityController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(method = RequestMethod.GET)
    public String getString(){
        return "Hello There !!!!";
    }

    @RequestMapping( value = "/getToken", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity authenticate(@RequestBody AuthenticationRequest request) throws Exception{
        try {
            // Authenticate user name and password
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        }
        catch (BadCredentialsException e){
            throw new Exception("Invalid Username or Password.", e);
        }

        // We need User details to generate Token.
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        return  ResponseEntity.ok(new AuthenticationResponse(jwtUtil.generateToken(userDetails)));
    }
}
