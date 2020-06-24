INSERT INTO usr (user_id, first_name, last_name, role_name, password)
VALUES ('aaaa', 'Aaa', 'Aaa', 'USER', '{bcrypt}$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK');
INSERT INTO usr (user_id, first_name, last_name, role_name, password)
VALUES ('bbbb', 'Bbb', 'Bbb', 'USER', '{bcrypt}$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK');
INSERT INTO usr (user_id, first_name, last_name, role_name, password)
VALUES ('cccc', 'Ccc', 'Ccc', 'ADMIN', '{bcrypt}$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK');
--
INSERT INTO meeting_room (room_name) VALUES ('Tagore');
INSERT INTO meeting_room (room_name) VALUES ('Vivekananda');
INSERT INTO meeting_room (room_name) VALUES ('Gandhi');
INSERT INTO meeting_room (room_name) VALUES ('Netaji');
INSERT INTO meeting_room (room_name) VALUES ('Bhagat');
INSERT INTO meeting_room (room_name) VALUES ('Chandrashekhar');
INSERT INTO meeting_room (room_name) VALUES ('Chittaranjan');
--

INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE, (SELECT room_id FROM meeting_room WHERE room_name = 'Tagore'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 1 DAY), (SELECT room_id FROM meeting_room WHERE room_name = 'Tagore'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 2 DAY), (SELECT room_id FROM meeting_room WHERE room_name = 'Tagore'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 3 DAY), (SELECT room_id FROM meeting_room WHERE room_name = 'Tagore'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 4 DAY), (SELECT room_id FROM meeting_room WHERE room_name = 'Tagore'));

INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE, (SELECT room_id FROM meeting_room WHERE room_name = 'Vivekananda'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 1 DAY), (SELECT room_id FROM meeting_room WHERE room_name = 'Vivekananda'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 2 DAY), (SELECT room_id FROM meeting_room WHERE room_name = 'Vivekananda'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 3 DAY), (SELECT room_id FROM meeting_room WHERE room_name = 'Vivekananda'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 4 DAY), (SELECT room_id FROM meeting_room WHERE room_name = 'Vivekananda'));

INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE, (SELECT room_id FROM meeting_room WHERE room_name = 'Gandhi'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 1 DAY), (SELECT room_id FROM meeting_room WHERE room_name = 'Gandhi'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 2 DAY), (SELECT room_id FROM meeting_room WHERE room_name = 'Gandhi'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 3 DAY), (SELECT room_id FROM meeting_room WHERE room_name = 'Gandhi'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 4 DAY), (SELECT room_id FROM meeting_room WHERE room_name = 'Gandhi'));

INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE, (SELECT room_id FROM meeting_room WHERE room_name = 'Netaji'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 1 DAY), (SELECT room_id FROM meeting_room WHERE room_name = 'Netaji'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 2 DAY), (SELECT room_id FROM meeting_room WHERE room_name = 'Netaji'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 3 DAY), (SELECT room_id FROM meeting_room WHERE room_name = 'Netaji'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 4 DAY), (SELECT room_id FROM meeting_room WHERE room_name = 'Netaji'));

INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE, (SELECT room_id FROM meeting_room WHERE room_name = 'Bhagat'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 1 DAY), (SELECT room_id FROM meeting_room WHERE room_name = 'Bhagat'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 2 DAY), (SELECT room_id FROM meeting_room WHERE room_name = 'Bhagat'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 3 DAY), (SELECT room_id FROM meeting_room WHERE room_name = 'Bhagat'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 4 DAY), (SELECT room_id FROM meeting_room WHERE room_name = 'Bhagat'));

INSERT INTO reservable_room (reserved_date, room_id) VALUES (CURRENT_DATE, (SELECT room_id FROM meeting_room WHERE room_name = 'Chandrashekhar'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 1 DAY), (SELECT room_id FROM meeting_room WHERE room_name = 'Chandrashekhar'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 2 DAY), (SELECT room_id FROM meeting_room WHERE room_name = 'Chandrashekhar'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 3 DAY), (SELECT room_id FROM meeting_room WHERE room_name = 'Chandrashekhar'));
INSERT INTO reservable_room (reserved_date, room_id) VALUES (DATE_ADD(CURRENT_DATE, INTERVAL 4 DAY), (SELECT room_id FROM meeting_room WHERE room_name = 'Chandrashekhar'));
