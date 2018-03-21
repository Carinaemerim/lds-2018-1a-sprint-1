-- EMPLOYEE PHOTOS
insert into file(id, content, content_type, filename) values
(0,  FILE_READ('./src/main/resources/static/photos/face.jpg'), 'image/jpeg', 'face.jpg');

--AUTHENTICATION
INSERT into user(id, username, password, name, email, experience, skill, active, picture_id) VALUES
(100, 'user', '$2a$10$Qji2/icFWIGGQEAv8bbwNuKGrSZyiUUPqE/0SNO2M.BpU.NA6xPhW', 'Master Yoda','yoda@stars.wars','Masters Unidentified Jedi, Garro, Qui-Gon Jinn', 'Deflect Force Lightning, Strategic Mastery, Acting Skills, Indomitable Will, Battle Meditation, Sensing Death And Force-aided Acrobatics.',  TRUE, 0);
INSERT into user(id, username, password, name, email, experience, skill, active, picture_id) VALUES
(101, 'orientador', '$2a$10$Qji2/icFWIGGQEAv8bbwNuKGrSZyiUUPqE/0SNO2M.BpU.NA6xPhW', 'Darth Vader','yoda@stars.wars','Masters Unidentified Jedi, Garro, Qui-Gon Jinn', 'Deflect Force Lightning, Strategic Mastery, Acting Skills, Indomitable Will, Battle Meditation, Sensing Death And Force-aided Acrobatics.',  TRUE, 0);



--ROLES
insert into role(id, role) values
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

-- USER_ROLES
insert into user_roles (user_id, roles_id) values
(100, 1),
(101, 1);


-- TERM_PAPER
insert into TERM_PAPER (id, theme, title, advisor_id, author_id) values
(100, 'Spring Boot', 'TCC sobre o melhor framework do mundo', 100, 101),
(101, 'Engenharia de Software', 'TCC sobre o impacto de scrum no mundo', 100, 101);

insert into DOCUMENT (id, document_type, is_final, term_paper_id)	values
(101,0,1,100),
(102,0,1,101);


insert into EVALUATION_BOARD (id, document_id) values
(101, 101),
(102, 102);

insert into EVALUATION_BOARD_PROFESSORS (EVALUATION_BOARD_ID, PROFESSORS_ID) values
(101, 101);