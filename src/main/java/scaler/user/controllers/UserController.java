package scaler.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import scaler.user.dtos.UserDetailLoginRequestDTO;
import scaler.user.dtos.UserDetailRequestDTO;
import scaler.user.services.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService){
            this.userService = userService;
    }

    @PostMapping()
    public void createUser(@RequestBody UserDetailRequestDTO userDetails){
        userService.createUser(userDetails);
    }

    @PostMapping("/create")
    public ResponseEntity<Boolean> loginUser(@RequestBody UserDetailLoginRequestDTO userDetails){
       Boolean isAuthenticatedUser =  userService.loginUser(userDetails);
       if(isAuthenticatedUser){
              return new ResponseEntity<>(isAuthenticatedUser, HttpStatus.OK);
       }
       return new ResponseEntity<>(isAuthenticatedUser, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/session")
    public ResponseEntity<String> createSession(){
        String userId = "ace01a8b-e48b-4c64-8604-15b9a2bfb6cd";
        Boolean isSessionCreated = userService.createSession(userId);
        if(isSessionCreated) return new ResponseEntity<>("Session created for that user "+userId, HttpStatus.OK);
        return new ResponseEntity<>("User id is Invalid " +userId, HttpStatus.NOT_FOUND);
    }
}
