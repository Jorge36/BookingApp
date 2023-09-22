package com.restaurant.BookingApplication.Service;

import com.restaurant.BookingApplication.Dto.*;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<ResponseDto> registerUser(RegisterDto registerDto);

    ResponseEntity<ResponseDto> login(LoginDto loginDto);

    ResponseEntity<ResponseDto> logout(String bearerToken);
}
