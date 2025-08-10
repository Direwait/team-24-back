package ru.team24.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private long userId;

    @OneToOne
    @JoinColumn(name="role_id", referencedColumnName = "role_id", nullable = false)
    private Role roleId;

    @Column(nullable = false, unique = true)
    private String userMail;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private String userFirstName;

    @Column(nullable = false)
    private String userLastName;

    private Date userCreatedAt = new Date();

    public long getRoleId() {
        return roleId.getRoleId();
    }
    public void setRoleId(long roleId) {
        this.roleId.setRoleId(roleId);
    }
}
