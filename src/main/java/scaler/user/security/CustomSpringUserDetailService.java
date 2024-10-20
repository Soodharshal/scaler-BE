package scaler.user.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import scaler.user.models.User;
import scaler.user.repository.UserRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomSpringUserDetailService implements UserDetailsService {

    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepository.findByEmail(username);
                if(userOptional.isEmpty()){
                    throw new UsernameNotFoundException("User does not exist");
                }
                User user = userOptional.get();
               return new CustomSpringUserDetails(user);
      }
}
