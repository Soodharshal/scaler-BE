package dev.harshal.scaler.inheritanceDemo.singletable.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_ta")
public class TA extends User {

    private double avgRating;
}
