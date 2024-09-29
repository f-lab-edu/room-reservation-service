INSERT INTO room (zone_id, name, basic_price)
VALUES
    ('Asia/Seoul', '레드룸1', 150000),
    ('Asia/Seoul', '그린룸1', 180000),
    ('Asia/Seoul', '블루룸1', 200000),
    ('Asia/Seoul', '화이트룸1', 250000),
    ('Asia/Seoul', '블렉룸1', 225000),
    ('Asia/Seoul', '피플룸1', 250000);

INSERT INTO accommodation (room_id, status, price, accommodation_start_date, accommodation_end_date)
SELECT
    rooms.room_id,
    'AVAILABLE' AS status,
    rooms.basic_price,
    DATEADD('DAY', ROW_NUMBER() OVER (ORDER BY rooms.room_id) - 1, CURRENT_DATE()) AS accommodation_start_date,
    DATEADD('DAY', ROW_NUMBER() OVER (ORDER BY rooms.room_id), CURRENT_DATE()) AS accommodation_end_date
FROM
    room rooms
WHERE
    rooms.room_id IS NOT NULL;
