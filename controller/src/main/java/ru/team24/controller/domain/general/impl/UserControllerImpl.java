package ru.team24.controller.domain.general.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.team24.controller.domain.general.UserController;
import ru.team24.service.payload.request.AddNewUserRequest;
import ru.team24.service.domain.general.UserService;
import ru.team24.service.dto.user.UserDtoWithRoleDto;

@RequestMapping("/api/v1/users")
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/{userId}")
    public ResponseEntity<?> findByUserId(@PathVariable long userId) {
        return new ResponseEntity<>(userService.findByUserId(userId), HttpStatus.OK);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<?> softDeleteUserById(@PathVariable long userId) {
        userService.softDeleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/exists/{mail}")
    public ResponseEntity<?> existsByEmail(@PathVariable String mail) {
        if(userService.existsByUserMail(mail)){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<PagedModel<EntityModel<UserDtoWithRoleDto>>> getUsers(
            @RequestParam(required = false) String role,
            @PageableDefault Pageable pageable,
            PagedResourcesAssembler<UserDtoWithRoleDto> assembler) {

        Page<UserDtoWithRoleDto> currentPage = userService.findUsers(role, pageable);
        PagedModel<EntityModel<UserDtoWithRoleDto>> model = assembler.toModel(currentPage);

        return ResponseEntity.ok(model);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<?> addUser(@RequestBody AddNewUserRequest userRequest) {
        userRequest.setUserPassword(passwordEncoder.encode(userRequest.getUserPassword()));
        userService.addUser(userRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
