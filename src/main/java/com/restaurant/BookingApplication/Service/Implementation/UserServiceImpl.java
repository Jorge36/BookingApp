package com.restaurant.BookingApplication.Service.Implementation;

import com.restaurant.BookingApplication.Controller.Status;
import com.restaurant.BookingApplication.Dto.*;
import com.restaurant.BookingApplication.Entity.AuthenticationToken;
import com.restaurant.BookingApplication.Entity.User;
import com.restaurant.BookingApplication.Exception.AuthenticationFailException;
import com.restaurant.BookingApplication.Exception.CustomException;
import com.restaurant.BookingApplication.Repository.AuthenticationTokenRepository;
import com.restaurant.BookingApplication.Repository.UserRepository;
import com.restaurant.BookingApplication.Service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Optional;
import java.security.MessageDigest;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationTokenRepository authenticationTokenRepository;

    @Override
    @Transactional
    public ResponseEntity<ResponseDto> registerUser(RegisterDto registerDto) {

        // check if user already existed
        if (Objects.nonNull(userRepository.findByEmail(registerDto.getEmail()))) {

            throw new CustomException("email already present");

        }

        // Create user
        User user = new User(

                registerDto.getId(),
                registerDto.getFullName(),
                registerDto.getEmail(),
                registerDto.getMobilePhone(),
                encryptPassword(registerDto.getPassword()),
                Boolean.FALSE
        );

        // hash the password
        // Not done

        // save user
        userRepository.save(user);

        // create token and save token
        // if we add the token in postman a bearer token , we have tp add the word Bearer as prefix.
        // For example: Bearer + " " + token code
        // if we add the token in postman in the header as authorization, we don't need to add the word Bearer
        authenticationTokenRepository.save(new AuthenticationToken(user));

        return new ResponseEntity(new ResponseDto(Status.SUCCESS, "user created successfully"), HttpStatus.OK);

    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDto> login(LoginDto loginDto) {

        // find user by email
        User user = userRepository.findByEmail(loginDto.getEmail());

        if (Objects.isNull(user)) {
            throw new AuthenticationFailException("email is not valid");
        }

        // hash password
        // Not done

        // compare pwd with the one in the DB
        // if pwds match
        if (!encryptPassword(loginDto.getPassword()).equals(user.getPassword())) {
            // it pwds does not match -> error
            throw new AuthenticationFailException("wrong password");
        }

        // retrieve token
        AuthenticationToken authenticationToken = authenticationTokenRepository.findByUser(user);

        if (Objects.isNull(authenticationToken)) {
            throw new CustomException("token is not present");
        }

        // check if the user is not already logged in
        if (user.getLoggedIn().equals(Boolean.TRUE)) {
            // send it to the user
            throw new AuthenticationFailException("user is already logged in");
        }

        // Update logged_in to true in database, user table
        user.setLoggedIn(Boolean.TRUE);
        userRepository.updateLoggedIn(user.getLoggedIn(), user.getId());

        // send it to the user
        return new ResponseEntity(new ResponseDto(Status.SUCCESS, authenticationToken.getToken()), HttpStatus.OK);

    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDto> logout(String bearerToken) {
        AuthenticationToken authenticationToken = authenticationTokenRepository.findByToken(bearerToken);
        // check if token exists
        if (authenticationToken == null) {
            throw new CustomException("token is not present");
        }

        Optional<User> user = userRepository.findById(authenticationToken.getUser().getId());
        // check if user exists
        if (!user.isPresent()) {
            throw new AuthenticationFailException("user does not exist");
        }
        // check if user is not logged in
        if (user.get().getLoggedIn().equals(Boolean.FALSE)) {
            // send it to the user
            throw new AuthenticationFailException("user is not logged in");
        }

        // Update logged_in to false in database, user table
        user.get().setLoggedIn(Boolean.FALSE);
        userRepository.updateLoggedIn(user.get().getLoggedIn(), user.get().getId());

        return new ResponseEntity(new ResponseDto(Status.SUCCESS, "user was logged out successfully"), HttpStatus.OK);

    }

    private String encryptPassword(String password) {

        try
        {
            /* MessageDigest instance for MD5. */
            MessageDigest m = MessageDigest.getInstance("MD5");

            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(password.getBytes());

            /* Convert the hash value into bytes */
            byte[] bytes = m.digest();

            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            StringBuilder s = new StringBuilder();

            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            /* Complete hashed password in hexadecimal format */
            return s.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

}
