package com.volkevich.InstaZoo.web;

import com.volkevich.InstaZoo.payload.reponse.JWTTokenSuccessResponse;
import com.volkevich.InstaZoo.payload.reponse.MessageResponse;
import com.volkevich.InstaZoo.payload.request.LoginRequest;
import com.volkevich.InstaZoo.payload.request.SignupRequest;
import com.volkevich.InstaZoo.security.JWTTokenProvider;
import com.volkevich.InstaZoo.security.SecurityConstants;
import com.volkevich.InstaZoo.services.UserService;
import com.volkevich.InstaZoo.validations.ResponseErrorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
@PreAuthorize("permitAll()")
public class AuthController {

        @Autowired
        private AuthenticationManager authenticationManager;
        @Autowired
        private JWTTokenProvider jwtTokenProvider;
        @Autowired
        private UserService userService;
        @Autowired
        private ResponseErrorValidator responseErrorValidator;


        public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult){
            ResponseEntity<Object> errors = responseErrorValidator.mapValidatorService(bindingResult);
            if (ObjectUtils.isEmpty(errors)) return errors;

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(), loginRequest.getPassword()
            ));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

            return ResponseEntity.ok(new JWTTokenSuccessResponse(true,jwt));
        }

        @PostMapping("/signup")
        public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult){
            ResponseEntity<Object> errors = responseErrorValidator.mapValidatorService(bindingResult);
            if (ObjectUtils.isEmpty(errors)) return errors;

            userService.createUser(signupRequest);
            return ResponseEntity.ok(new MessageResponse("User registered successfull"));
        }


}
