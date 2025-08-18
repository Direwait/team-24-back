package ru.team24.database.domain.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.team24.database.domain.admin.entity.Template;

import java.util.Optional;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {
    Optional<Template> findByTemplateId(long templateId);
    Optional<Template> findTopByOrderByTemplateIdDesc();

    @Query("SELECT MAX(t.templateId) FROM Template t")
    long getRecentTemplateId();
}
