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
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="template_id")
    long templateId;
    @Column(nullable = false, unique = true)
    String templateName;
    @Column(nullable = false)
    String templateSubject;
    @Column(nullable = false)
    String templateBody;
    @Column(nullable = false)
    String templateText;
}
