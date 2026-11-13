package org.example.eventproo.mapper;

import org.example.eventproo.dto.response.ReservationResponse;
import org.example.eventproo.entity.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationResponse toResponse(Reservation reservation);
}