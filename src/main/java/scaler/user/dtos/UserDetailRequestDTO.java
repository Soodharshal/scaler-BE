package scaler.user.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailRequestDTO {
    private String name;
    private String email;
    private String password;
}
