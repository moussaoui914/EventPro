package org.example.eventproo.mapper;

import org.example.eventproo.dto.request.EvenementCreateRequest;
import org.example.eventproo.entity.CategorieBillet;
import org.example.eventproo.entity.Evenement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EvenementMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "statut", ignore = true)
    @Mapping(target = "organisateur", ignore = true)
    Evenement toEntity(EvenementCreateRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "evenement", ignore = true)
    @Mapping(target = "placesReservees", ignore = true)
    @Mapping(target = "placesVendues", ignore = true)
    CategorieBillet toCategorieEntity(EvenementCreateRequest.CategorieRequest request);
}