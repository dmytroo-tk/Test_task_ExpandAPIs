package com.example.test_task_expandapis.rest;

import com.example.test_task_expandapis.model.User;
import com.example.test_task_expandapis.model.dto.UserDto;
import com.example.test_task_expandapis.service.UserService;
import com.example.test_task_expandapis.token.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/add")
    public ResponseEntity<?> signUp(@RequestBody UserDto userDto) {
        if (userService.existByUsername(userDto.username())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Choose different username");
        }

        User user = new User();
        user.setUsername(userDto.username());
        user.setPassword(passwordEncoder.encode(userDto.password()));

        userService.addUser(user);

        return ResponseEntity.ok(String.format("'%s' was created", user.getUsername()));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> signIn(@RequestBody UserDto userDto) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.username(), userDto.password()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(jwt);
    }
}
