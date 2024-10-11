package scaler.user.services;

import org.springframework.stereotype.Service;
import scaler.user.dtos.UserDetailLoginRequestDTO;
import scaler.user.dtos.UserDetailRequestDTO;

@Service
public interface UserService {
   void createUser(UserDetailRequestDTO user);
   Boolean loginUser(UserDetailLoginRequestDTO user);
   Boolean createSession(String userId);


}
