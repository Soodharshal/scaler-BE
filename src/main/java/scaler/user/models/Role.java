package scaler.user.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name ="Role")
@Getter
@Setter
@NoArgsConstructor
public class Role extends BaseEntity{
    String roleName;
}
