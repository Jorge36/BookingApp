package com.restaurant.BookingApplication.Controller;

import com.restaurant.BookingApplication.Dto.*;
import com.restaurant.BookingApplication.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/register")
    public ResponseEntity<ResponseDto> registerUser(@RequestBody RegisterDto registerDto) {

        return userService.registerUser(registerDto);

    }

    @PostMapping(path = "/login")
    public ResponseEntity<ResponseDto> loginUser(@RequestBody LoginDto loginDto) {

        return userService.login(loginDto);

    }

    @PostMapping(path = "/logout")
    public ResponseEntity<ResponseDto> logoutUser(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");

        return userService.logout(bearerToken);

    }

}
