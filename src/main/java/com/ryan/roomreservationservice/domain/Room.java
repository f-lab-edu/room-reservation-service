package com.ryan.roomreservationservice.domain;

import com.ryan.roomreservationservice.util.enums.RoomStatus;
import com.ryan.roomreservationservice.util.enums.RoomStatusConverter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table( name ="room" )
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id" )
    private Long roomId;

    @Column(name = "zone_code" )
    private String zoneCode;

    @Convert(converter = RoomStatusConverter.class)
    @Column(name = "room_status" )
    private RoomStatus roomStatus;

    @Column(name = "room_name" )
    private String roomName;

    @Column(name = "room_size" )
    private String roomSize;

    @Builder
    public Room(Long roomId, String zoneCode, RoomStatus roomStatus, String roomName, String roomSize) {
        this.roomId = roomId;
        this.zoneCode = zoneCode;
        this.roomStatus = roomStatus;
        this.roomName = roomName;
        this.roomSize = roomSize;
    }
}
