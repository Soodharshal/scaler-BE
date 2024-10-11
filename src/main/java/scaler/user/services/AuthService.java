package scaler.user.services;

import org.springframework.http.ResponseEntity;
import scaler.user.dtos.LoginRequestDTO;
import scaler.user.dtos.SignUpRequestDTO;
import scaler.user.dtos.UserDTO;
import scaler.user.models.User;

public interface AuthService {
    ResponseEntity<UserDTO> login(LoginRequestDTO logindetails);
    User signUp(SignUpRequestDTO signUpRequestDTO);

    ResponseEntity<Void> logout(String token, String userId);
}
