package ru.team24.service.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.team24.database.domain.admin.entity.Template;
import ru.team24.service.dto.TemplateDto;

@Mapper(componentModel = "spring")
public interface TemplateMapper {

    @Mapping(target = "templateCreatedAt", ignore = true)
    @Mapping(source = "userId", target = "user.userId")
    Template dtoToEntity(TemplateDto candidateDto);

    @Mapping(source = "user.userId", target = "userId")
    TemplateDto entityToDto(Template candidate);

}
