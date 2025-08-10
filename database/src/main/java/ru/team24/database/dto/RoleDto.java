package ru.team24.database.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    private long roleId;

    private String roleName;

    private boolean viewingMyRequests = false;
    private boolean viewingAllRequests = false;
    private boolean creatingRequests = false;
    private boolean creatingAdmins = false;
}
