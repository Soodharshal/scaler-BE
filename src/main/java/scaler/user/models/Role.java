package scaler.user.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name ="Role")
@Getter
@Setter
@NoArgsConstructor
@JsonDeserialize(as = Role.class)
public class Role extends BaseEntity{
    String roleName;
}
