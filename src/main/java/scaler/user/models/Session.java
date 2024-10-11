package scaler.user.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scaler.user.enums.SessionStatus;

import java.util.Date;

@Entity(name ="Session")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session extends BaseEntity {
    private String token;

    private Date expiresAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private SessionStatus sessionStatus;
}
