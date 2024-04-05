package com.SunbaseAssignment.CRUDAppForCustomer.Controllers;


import com.SunbaseAssignment.CRUDAppForCustomer.Entities.JWTRequest;
import com.SunbaseAssignment.CRUDAppForCustomer.Entities.JWTResponse;
import com.SunbaseAssignment.CRUDAppForCustomer.JWTClass.JWTHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class JWTAuthenticationController {

    private UserDetailsService userDetailsService;
    private AuthenticationManager manager;

    private JWTHelper helper;

    @PostMapping("/authenticate")

    public ResponseEntity<JWTResponse> login(@RequestBody JWTRequest request){

        //authenticate
        this.doAuthenticate(request.getUserName(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());

        String token = this.helper.generateToken(userDetails);

        JWTResponse response = JWTResponse.builder()
                .jwtToken(token)
                .userName(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Credentials Invalid !!");
        }

    }
}
