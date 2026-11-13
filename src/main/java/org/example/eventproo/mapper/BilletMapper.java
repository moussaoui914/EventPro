package org.example.eventproo.mapper;

import org.example.eventproo.dto.response.BilletResponse;
import org.example.eventproo.entity.Billet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BilletMapper {
    @Mapping(target = "message", ignore = true)
    BilletResponse toResponse(Billet billet);
}