package scaler.user.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity(name ="users")
@Getter
@Setter
@AllArgsConstructor
public class User extends BaseEntity{
    private String name;
    private String email;
    private String password;

    @ManyToMany
    private Set<Role> roles = new HashSet<>();
    public User() {

    }
}
