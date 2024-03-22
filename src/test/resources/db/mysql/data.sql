INSERT INTO room (zone_code, room_status, room_name, room_size)
VALUES ('Asia/Seoul', 'EXPOSURE_POSSIBLE', '레드룸1', '4'),
       ('Asia/Seoul', 'EXPOSURE_POSSIBLE', '그린룸1', '6'),
       ('Asia/Seoul', 'EXPOSURE_POSSIBLE', '블루룸1', '8'),
       ('Asia/Seoul', 'EXPOSURE_POSSIBLE', '화이트룸1', '4'),
       ('Asia/Seoul', 'EXPOSURE_POSSIBLE', '블렉룸1', '6'),
       ('Asia/Seoul', 'EXPOSURE_POSSIBLE', '피플룸1', '8');

-- MySQL
INSERT INTO accommodation (room_id, reservation_status, reservation_date)
SELECT room_id,
       'AVAILABLE' AS reservation_status,
       DATE_ADD(CURRENT_DATE(), INTERVAL nums.num DAY) AS reservation_date
FROM (SELECT @row := @row + 1 AS num FROM information_schema.columns, (SELECT @row := 0) r LIMIT 30) AS nums
         CROSS JOIN (SELECT room_id FROM room LIMIT 30) AS rooms
WHERE DATE_ADD(CURRENT_DATE(), INTERVAL nums.num DAY) <= DATE_ADD(CURRENT_DATE(), INTERVAL 30 DAY);

