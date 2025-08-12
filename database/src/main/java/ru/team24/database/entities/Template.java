package ru.team24.database.entities;

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
@Table(name = "templates")
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="template_id")
    private Long templateId;

    @Column(nullable = false, unique = true)
    private String templateName;

    @Column(nullable = false)
    private String templateSubject;

    @Column(nullable = false)
    private String templateBody;

    @Column(nullable = false)
    private String templateText;
}
