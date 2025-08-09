package ru.team24.service.mapper;


import org.mapstruct.Mapper;
import ru.team24.database.dto.TemplateDto;
import ru.team24.database.entities.Template;

@Mapper(componentModel = "spring")
public interface TemplateMapper {
    Template dtoToEntity(TemplateDto candidateDto);

    TemplateDto entityToDto(Template candidate);
}
