package scaler.user.dtos;

import lombok.Getter;
import lombok.Setter;
import scaler.user.models.User;

import java.util.HashSet;

@Getter
@Setter
public class UserDTO {
    private String email;
    private HashSet<RoleDTO> roleDTOS = new HashSet<>();
    public static UserDTO from(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setEmail(user.getEmail());
//        userDto.setRoles(user.getRoles());

        return userDto;
    }
}
