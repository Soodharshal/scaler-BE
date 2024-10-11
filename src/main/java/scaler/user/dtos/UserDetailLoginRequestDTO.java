package scaler.user.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailLoginRequestDTO {
    private String email;
    private String password;
}
