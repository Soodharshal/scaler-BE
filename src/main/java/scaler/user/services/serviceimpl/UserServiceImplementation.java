package scaler.user.services.serviceimpl;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import scaler.user.dtos.UserDetailLoginRequestDTO;
import scaler.user.dtos.UserDetailRequestDTO;
import scaler.user.models.Session;
import scaler.user.models.User;
import scaler.user.repository.SessionRepository;
import scaler.user.repository.UserRepository;
import scaler.user.services.UserService;

import java.util.Optional;
import java.util.UUID;


@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public  UserServiceImplementation(UserRepository userRepository,
                                      SessionRepository sessionRepository,
                                      BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository= userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    public void createUser(UserDetailRequestDTO userDetailRequestDTO) {
      User user = new User();
      user.setName(userDetailRequestDTO.getName());
      user.setEmail(userDetailRequestDTO.getEmail());
      user.setPassword(userDetailRequestDTO.getPassword());
      userRepository.save(user);
    }

    @Override
    public Boolean loginUser(UserDetailLoginRequestDTO userDetailRequestDTO) {
       Optional<User> userRepositoryByEmailAndPassword =  userRepository.findByEmailAndPassword(userDetailRequestDTO.getEmail(),userDetailRequestDTO.getPassword());

       if(userRepositoryByEmailAndPassword.isPresent()){
           return true;
       }
        UserDetailRequestDTO userDetailRequestDTO1 = new UserDetailRequestDTO();
       userDetailRequestDTO1.setEmail(userDetailRequestDTO.getEmail());
        userDetailRequestDTO1.setName("userRepositoryByEmailAndPassword.get()");
        userDetailRequestDTO1.setPassword(bCryptPasswordEncoder.encode(userDetailRequestDTO.getPassword()));
        createUser(userDetailRequestDTO1);
        return false;
    }

    @Override
    public Boolean createSession(String userId) {
        String token = new ObjectIdGenerators.UUIDGenerator().toString();
        UUID sameUuid = UUID.fromString(userId);
        Optional<User> user = userRepository.findById(sameUuid);
        if(user.isPresent()) {
            Session session = new Session();
            session.setUser(user.get());
            session.setToken(token);
            sessionRepository.save(session);
            return  true;
        }
        else return false;
    }
}
