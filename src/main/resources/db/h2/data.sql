INSERT INTO room (zone_code, room_status, room_name, room_size)
VALUES ('Asia/Seoul', 'EXPOSURE_POSSIBLE', '레드룸1', '4'),
       ('Asia/Seoul', 'EXPOSURE_POSSIBLE', '그린룸1', '6'),
       ('Asia/Seoul', 'EXPOSURE_POSSIBLE', '블루룸1', '8'),
       ('Asia/Seoul', 'EXPOSURE_POSSIBLE', '화이트룸1', '4'),
       ('Asia/Seoul', 'EXPOSURE_POSSIBLE', '블렉룸1', '6'),
       ('Asia/Seoul', 'EXPOSURE_POSSIBLE', '피플룸1', '8');

-- H2
INSERT INTO accommodation (room_id, reservation_status, reservation_date)
SELECT room_id,
       'AVAILABLE' AS reservation_status,
       DATEADD('DAY', num, CURRENT_DATE()) AS reservation_date
FROM GENERATE_SERIES(1, 30) AS nums(num)
         CROSS JOIN
     (SELECT TOP 30 room_id FROM room) AS rooms
WHERE DATEADD('DAY', num, CURRENT_DATE()) <= DATEADD('DAY', 30, CURRENT_DATE())



