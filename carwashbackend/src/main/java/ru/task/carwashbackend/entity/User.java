package ru.task.carwashbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    private UUID userId;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String userPassword;
    @Column(name = "role")
    private String userRole;

    public User(UUID userId, String name, String userPassword, String userRole) {
        this.userId = userId;
        this.name = name;
        this.userPassword = userPassword;
        this.userRole = userRole;
    }
}
