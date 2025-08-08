package ru.team24.database.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="role_id")
    private long roleId;
    @Column(nullable = false)
    private String roleName;
    private boolean viewingMyRequests = false;
    private boolean viewingAllRequests = false;
    private boolean creatingRequests = false;
    private boolean creatingAdmins = false;
}
