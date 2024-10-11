package scaler.user.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import scaler.user.dtos.*;
import scaler.user.models.User;
import scaler.user.services.AuthService;
import scaler.user.services.UserService;

@Controller
@RequestMapping("auth")
public class AuthController {
    private AuthService authService;


    public AuthController(AuthService authService){
        this.authService = authService;
    }


    @GetMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody LoginRequestDTO loginRequestDTO){
         return authService.login(loginRequestDTO);
    }

    @GetMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO){
        User user = authService.signUp(signUpRequestDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto request) {
        return authService.logout(request.getToken(), request.getUserId());
    }
//
//    @PostMapping("/login")
//    public ResponseEntity<Boolean> loginUser(@RequestBody UserDetailLoginRequestDTO userDetails){
//      return null;
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<String> createSession(){
//        return  null;
//    }

//    @PostMapping("/create")
//    public ResponseEntity<Boolean> loginUser(@RequestBody UserDetailLoginRequestDTO userDetails){
//        Boolean isAuthenticatedUser =  userService.loginUser(userDetails);
//        if(isAuthenticatedUser){
//            return new ResponseEntity<>(isAuthenticatedUser, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(isAuthenticatedUser, HttpStatus.NOT_FOUND);
//    }
}
