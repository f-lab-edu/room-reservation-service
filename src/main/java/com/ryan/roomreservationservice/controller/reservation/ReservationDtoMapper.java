package com.ryan.roomreservationservice.controller.reservation;

import com.ryan.roomreservationservice.service.reservation.ReservationCommand;
import com.ryan.roomreservationservice.service.reservation.ReservationInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ReservationDtoMapper {
    ReservationCommand.GetReservationListRequest of(ReservationDto.GetReservationListRequest request);
    List<ReservationDto.ReserveResponse> of(List<ReservationInfo.GetReservationList> response);
    ReservationCommand.ReserveRequest of(ReservationDto.ReserveRequest request);
}
