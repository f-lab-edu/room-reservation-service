package com.ryan.roomreservationservice.controller.room;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface RoomDtoMapper {
    List<RoomDto.GetRoomListResponse> of(List<RoomDto.GetRoomListResponse> result);
}
