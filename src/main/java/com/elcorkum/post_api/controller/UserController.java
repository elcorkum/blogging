package com.elcorkum.post_api.controller;


import com.elcorkum.post_api.entity.User;
import com.elcorkum.post_api.response.ResponseHandler;
import com.elcorkum.post_api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> createNewUser(@RequestBody User user){
        try {
            User newUser = userService.createUser(user);
            if(user != null)
                logger.info("UserController createNewUser() success {}", newUser);
            return ResponseHandler.responseBuilder("User saved successfully", HttpStatus.CREATED, newUser);
        } catch (Exception e) {
            logger.info("UserController createNewUser() failed request");
            return ResponseHandler.responseBuilder("User not saved", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getExistingUser(@PathVariable Long userId){
        try {
            User user = userService.getUser(userId);
            if (user != null)
                logger.info("UserController getExistingUser() SUCCESS! {}", user);
            return ResponseHandler.responseBuilder("User retrieved successfully", HttpStatus.OK, user);
        } catch (Exception e) {
            logger.info("UserController getExistingUser() failed request");
            return ResponseHandler.responseBuilder("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        try {
            Iterable<User> allUsers = userService.getUsers();
            if(allUsers.iterator().hasNext())
                logger.info("UserController getAllUsers() SUCCESS! {}", allUsers);
            return ResponseHandler.responseBuilder("Users retrieved successfully", HttpStatus.OK, allUsers);
        } catch (Exception e) {
            logger.info("UserController getAllUsers() failed request");
            return ResponseHandler.responseBuilder("Users not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateExistingUser(@PathVariable Long userId, @RequestBody User user){
        try {
            User updatedUser = userService.updateUser(userId, user);
            if(updatedUser != null)
                logger.info("UserController updateExistingUser() SUCCESS! {}", updatedUser);
            return ResponseHandler.responseBuilder("User update successful", HttpStatus.ACCEPTED, updatedUser);
        } catch (Exception e) {
            logger.info("UserController updateExistingUser() failed request");
            return ResponseHandler.responseBuilder("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteExistingUser(@PathVariable Long userId){
        try {
            userService.deleteUser(userId);
            logger.info("UserController deleteExistingUser() successful delete");
            return ResponseHandler.responseBuilder("User deleted successfully", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.info("UserController deleteExistingUser() failed delete");
            return ResponseHandler.responseBuilder("User not found", HttpStatus.NOT_FOUND);
        }
    }

}
