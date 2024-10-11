package scaler.user.dtos;

import lombok.Getter;
import lombok.Setter;
import scaler.user.enums.SessionStatus;
import scaler.user.models.User;

import java.util.Date;

@Getter
@Setter
public class SessionDTO {
    private String token;
    private Date expiresAt;
    private User user;
    private SessionStatus sessionStatus;
}
