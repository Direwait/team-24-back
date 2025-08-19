package ru.team24.database.domain.admin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.team24.database.domain.general.entity.User;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "templates")
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "template_id" )
    private Long templateId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @Column(name = "template_subject", nullable = false)
    private String templateSubject;

    @Column(name = "template_body", nullable = false)
    private String templateBody;

    @Column(name = "template_created_at")
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date templateCreatedAt = new Date();

    @Column(name = "template_updated_at")
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private Date templateUpdatedAt;

    @Column(name = "template_is_active")
    private boolean templateIsActive = true;
}
