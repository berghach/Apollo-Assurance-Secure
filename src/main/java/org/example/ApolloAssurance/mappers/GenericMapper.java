package org.example.ApolloAssurance.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenericMapper extends ModelMapper {

    public <Entity, EntityDTO> EntityDTO toDTO(Entity entity, Class<EntityDTO> dtoClass) {
        return this.map(entity, dtoClass);
    }

    public <Entity, EntityDTO> Entity fromDTO(EntityDTO dto, Class<Entity> entityClass) {
        return this.map(dto, entityClass);
    }

    public <Entity, EntityDTO> List<EntityDTO> toDTOList(List<Entity> entities, Class<EntityDTO> dtoClass) {
        return entities.stream().map(entity -> toDTO(entity, dtoClass)).collect(Collectors.toList());
    }

    public <Entity, EntityDTO> List<Entity> fromDTOList(List<EntityDTO> dtos, Class<Entity> entityClass) {
        return dtos.stream().map(dto -> fromDTO(dto, entityClass)).collect(Collectors.toList());
    }
}
