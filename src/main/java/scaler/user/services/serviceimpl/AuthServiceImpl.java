package scaler.user.services.serviceimpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;
import scaler.user.dtos.LoginRequestDTO;
import scaler.user.dtos.SessionDTO;
import scaler.user.dtos.SignUpRequestDTO;
import scaler.user.dtos.UserDTO;
import scaler.user.enums.SessionStatus;
import scaler.user.models.Session;
import scaler.user.models.User;
import scaler.user.repository.SessionRepository;
import scaler.user.repository.UserRepository;
import scaler.user.services.AuthService;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

import org.apache.commons.lang3.RandomStringUtils;

import javax.crypto.SecretKey;

import static scaler.user.enums.SessionStatus.ACTIVE;

@Service
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private  SessionRepository sessionRepository;

    public AuthServiceImpl(UserRepository userRepository,BCryptPasswordEncoder bCryptPasswordEncoder,
                           SessionRepository sessionRepository){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.sessionRepository = sessionRepository;
    }
    @Override
    public ResponseEntity<UserDTO> login(LoginRequestDTO logindetails) {

        Optional<User> optionalUser = userRepository.findByEmail(logindetails.getEmail());
        if (optionalUser.isEmpty()) {
            return null;
        }
        User user = optionalUser.get();
        if (!bCryptPasswordEncoder.matches(logindetails.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong userName and password");
        }

//        String token = RandomStringUtils.randomAlphanumeric(30);


        // Create a test key suitable for the desired HMAC-SHA algorithm:
        MacAlgorithm alg = Jwts.SIG.HS256; //or HS384 or HS256
        SecretKey key = alg.key().build();

//        String message = "{\n" +
//                "  \"email\": \"soodharshal@gmail.com\",\n" +
//                "  \"roles\": [\"mentor\",\"TA\"],\n" +
//                "  \"expirationDate\": \"31December2024\"\n" +
//                "}";
//
//      byte[] content = message.getBytes(StandardCharsets.UTF_8);
        Map<String, Object> jsonForJWT = new HashMap<>();
        jsonForJWT.put("email",user.getEmail());
        jsonForJWT.put("roles",user.getRoles());
        jsonForJWT.put("createdAT",new Date());
        jsonForJWT.put("expiryAt", new Date(LocalDate.now().plusDays(3).toEpochDay()));
// Create the compact JWS:
        String jws = Jwts.builder().claims(jsonForJWT).signWith(key,alg).compact();
//
//// Parse the compact JWS:
//        content = Jwts.parser().verifyWith(key).build().parseSignedContent(jws).getPayload();
//
//        assert message.equals(new String(content, StandardCharsets.UTF_8));


        Session session = new Session();
        session.setToken(jws);
        session.setUser(user);
        session.setSessionStatus(SessionStatus.ACTIVE);
        Date expiresAt = new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
        session.setExpiresAt(expiresAt);
        validateSession(session);
//        sessionRepository.save(session);

        UserDTO userDTO = UserDTO.from(user);
        MultiValueMapAdapter<String,String> headers= new MultiValueMapAdapter<>(new HashMap<>());
        HttpHeaders httpHeaders = new HttpHeaders();
        headers.add(httpHeaders.SET_COOKIE,"auth-token:" + jws);
        ResponseEntity<UserDTO> response = new ResponseEntity<>(userDTO,headers, HttpStatus.OK);
        return response;
    }

    @Override
    public User signUp(SignUpRequestDTO signUpRequestDTO) {
        User user = new User();
        user.setName(signUpRequestDTO.getName());
        user.setEmail(signUpRequestDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(signUpRequestDTO.getPassword()));
       return userRepository.save(user);

    }
    private void validateSession(Session session) {
        // Validate sessionStatus
        if (session.getSessionStatus() == null || (!session.getSessionStatus().equals(SessionStatus.ACTIVE) && !session.getSessionStatus().equals(SessionStatus.INACTIVE))) {
            throw new RuntimeException("Invalid session status");
        }
        // Validate expiresAt
        if (session.getExpiresAt() == null || session.getExpiresAt().before(new Date())) {
            throw new RuntimeException("Invalid expiration date");
        }
        Jwts.parser().build().equals(session.getToken());
    }

    public ResponseEntity<Void> logout(String token, String userId) {

        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Uuid(token, UUID.fromString(userId));

        if (sessionOptional.isEmpty()) {
            return null;
        }

        Session session = sessionOptional.get();

        session.setSessionStatus(SessionStatus.ENDED);

        sessionRepository.save(session);

        return ResponseEntity.ok().build();
    }


}

