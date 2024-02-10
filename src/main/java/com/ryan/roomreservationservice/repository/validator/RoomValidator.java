package com.ryan.roomreservationservice.repository.validator;

import com.ryan.roomreservationservice.domain.Room;
import com.ryan.roomreservationservice.util.enums.ErrorType;
import com.ryan.roomreservationservice.util.enums.StatusCode;
import com.ryan.roomreservationservice.util.exception.CommonException;
import com.ryan.roomreservationservice.util.exception.ErrorMessage;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoomValidator {

    public void assertRoomNotExist(Optional<Room> room) {
        room.orElseThrow(() -> CommonException.builder()
                .errorType(ErrorType.DEVELOPER)
                .status(StatusCode.FAIL.getStatusCode())
                .clientErrorMessage(ErrorMessage.NOT_EXIST_ROOM)
                .build());
    }
}
