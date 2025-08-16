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
@Table(name = "templates")
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "template_id" )
    private Long templateId;

    @Column(name = "template_name", nullable = false, unique = true)
    private String templateName;

    @Column(name = "template_subject", nullable = false)
    private String templateSubject;

    @Column(name = "template_body", nullable = false)
    private String templateBody;

    @Column(name = "template_create_at")
    private Date templateCreatedAt = new Date();

    @Column(name = "template_update_at")
    private Date templateUpdatedAt;

    @Column(name = "template_is_active")
    private boolean templateIsActive = true;
}
