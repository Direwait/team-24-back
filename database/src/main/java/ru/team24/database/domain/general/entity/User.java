package ru.team24.database.domain.general.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @JoinColumn(name="role_id", referencedColumnName = "role_id", nullable = false)
    private Role role;


    @Column(name = "user_mail", nullable = false, unique = true)
    private String userMail;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Column(name = "user_first_name", nullable = false)
    private String userFirstName;

    @Column(name = "user_last_name", nullable = false)
    private String userLastName;

    @Column(name = "user_created_at")
    private Date userCreatedAt = new Date();

    @Column(name = "user_is_active")
    private boolean userIsActive = true;

    public long getRoleId() {
        return role.getRoleId();
    }
}
