package ru.team24.database.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long roleId;

    @Column(nullable = false)
    private String roleName;

    private boolean viewingMyRequests = false;
    private boolean viewingAllRequests = false;
    private boolean creatingRequests = false;
    private boolean creatingAdmins = false;
}
