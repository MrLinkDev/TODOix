package ru.link.todoix.Entities;

import lombok.Data;
import ru.link.todoix.Enums.Role;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "user", schema = "todoix_app", catalog = "todoix")
@Data
public class UserEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Basic
    @Column(name = "login")
    private String name;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
}
