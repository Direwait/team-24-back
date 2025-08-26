package ru.team24.database.domain.general.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.team24.database.domain.general.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(long userId);

    boolean existsUserByUserMail(String userMail);

    Optional<User> findByUserMail(String userMail);

    Page<User> findAllByUserIsActiveOrderByUserCreatedAtDesc(boolean userIsActive, Pageable pageable);

    Page<User> findAllByUserIsActiveAndRole_RoleNameOrderByUserCreatedAtDesc(boolean userIsActive,
                                                                             String roleRoleName,
                                                                             Pageable pageable);
}
