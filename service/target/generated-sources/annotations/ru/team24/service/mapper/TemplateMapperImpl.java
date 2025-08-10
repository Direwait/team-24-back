package ru.team24.service.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.team24.database.dto.TemplateDto;
import ru.team24.database.entities.Template;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-10T00:08:39+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class TemplateMapperImpl implements TemplateMapper {

    @Override
    public Template dtoToEntity(TemplateDto candidateDto) {
        if ( candidateDto == null ) {
            return null;
        }

        Template template = new Template();

        template.setTemplateId( candidateDto.getTemplateId() );
        template.setTemplateName( candidateDto.getTemplateName() );
        template.setTemplateSubject( candidateDto.getTemplateSubject() );
        template.setTemplateBody( candidateDto.getTemplateBody() );
        template.setTemplateText( candidateDto.getTemplateText() );

        return template;
    }

    @Override
    public TemplateDto entityToDto(Template candidate) {
        if ( candidate == null ) {
            return null;
        }

        TemplateDto templateDto = new TemplateDto();

        templateDto.setTemplateId( candidate.getTemplateId() );
        templateDto.setTemplateName( candidate.getTemplateName() );
        templateDto.setTemplateSubject( candidate.getTemplateSubject() );
        templateDto.setTemplateBody( candidate.getTemplateBody() );
        templateDto.setTemplateText( candidate.getTemplateText() );

        return templateDto;
    }
}
