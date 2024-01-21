package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.util.enums.RoomStatus;
import com.ryan.roomreservationservice.util.enums.RoomStatusConverter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Tolerate;

@Entity
@Builder
@Getter
@Table( name ="room" )
public class Room {

    @Tolerate
    public Room() {}

    // 고유 키
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id" )
    private Long roomId;

    // 지역코드
    @Column(name = "zone_code" )
    private String zoneCode;

    // 상태(Ex: 노출 가능, 노출 불가능, 예약 불가능)
    @Convert(converter = RoomStatusConverter.class)
    @Column(name = "room_status" )
    private RoomStatus roomStatus;

    // 객실 이름
    @Column(name = "room_name" )
    private String roomName;

    // 객실 크기
    @Column(name = "room_size" )
    private String roomSize;

}
