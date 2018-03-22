-- EMPLOYEE PHOTOS
insert into file(id, content, content_type, filename) values
(0,  FILE_READ('./src/main/resources/static/photos/face.jpg'), 'image/jpeg', 'face.jpg');

--AUTHENTICATION
INSERT into user(id, username, password, name, email, experience, skill, active, picture_id, user_type) VALUES
(100, 'user', '$2a$10$Qji2/icFWIGGQEAv8bbwNuKGrSZyiUUPqE/0SNO2M.BpU.NA6xPhW', 'Master Yoda','yoda@stars.wars','Masters Unidentified Jedi, Garro, Qui-Gon Jinn', 'Deflect Force Lightning, Strategic Mastery, Acting Skills, Indomitable Will, Battle Meditation, Sensing Death And Force-aided Acrobatics.',  TRUE, 0, 'Student'),
(101, 'orientador', '$2a$10$Qji2/icFWIGGQEAv8bbwNuKGrSZyiUUPqE/0SNO2M.BpU.NA6xPhW', 'Darth Vader','yoda@stars.wars','Masters Unidentified Jedi, Garro, Qui-Gon Jinn', 'Deflect Force Lightning, Strategic Mastery, Acting Skills, Indomitable Will, Battle Meditation, Sensing Death And Force-aided Acrobatics.',  TRUE, 0, 'Professor'),
(102, 'user2', '$2a$10$Qji2/icFWIGGQEAv8bbwNuKGrSZyiUUPqE/0SNO2M.BpU.NA6xPhW', 'Master Yoda 2','yoda@stars.wars','Masters Unidentified Jedi, Garro, Qui-Gon Jinn', 'Deflect Force Lightning, Strategic Mastery, Acting Skills, Indomitable Will, Battle Meditation, Sensing Death And Force-aided Acrobatics.',  TRUE, 0, 'Student'),
(103, 'user3', '$2a$10$Qji2/icFWIGGQEAv8bbwNuKGrSZyiUUPqE/0SNO2M.BpU.NA6xPhW', 'Master Yoda of Puppets 3','yoda3@stars.wars','Masters Unidentified Jedi, Strategic Mastery, Acting Skills',' Indomitable Will, Battle Meditation, Sensing Death And Force-aided Acrobatics.',  TRUE, 0, 'Student'),
(104, 'user4', '$2a$10$Qji2/icFWIGGQEAv8bbwNuKGrSZyiUUPqE/0SNO2M.BpU.NA6xPhW', 'Slave Yoda 4','yodo4@stars.wars','Slave Unidentified Jedi, Strategic Mastery, Acting Skills, Indomitable Will, Battle Meditation','Sensing Death And Force-aided Acrobatics.',  TRUE, 0, 'Student'),
(105, 'orientador2', '$2a$10$Qji2/icFWIGGQEAv8bbwNuKGrSZyiUUPqE/0SNO2M.BpU.NA6xPhW', 'Darth Slave Vader','darth1@stars.wars','Masters Unidentified Jedi, Garro, Qui-Gon Jinn', 'Deflect Force Lightning, Strategic Mastery, Acting Skills, Indomitable Will, Battle Meditation, Sensing Death And Force-aided Acrobatics.',  TRUE, 0, 'Professor'),
(106, 'orientador3', '$2a$10$Qji2/icFWIGGQEAv8bbwNuKGrSZyiUUPqE/0SNO2M.BpU.NA6xPhW', 'Darth Vader 3','yodadv3@stars.wars','Masters Unidentified Jedi, Garro, Qui-Gon Jinn', 'Deflect Force Lightning, Strategic Mastery, Acting Skills, Indomitable Will, Battle Meditation, Sensing Death And Force-aided Acrobatics.',  TRUE, 0, 'Professor'),
(107, 'orientador4', '$2a$10$Qji2/icFWIGGQEAv8bbwNuKGrSZyiUUPqE/0SNO2M.BpU.NA6xPhW', 'Darth Vader 4','yodadv4@stars.wars','Masters Unidentified Jedi, Garro, Qui-Gon Jinn', 'Deflect Force Lightning, Strategic Mastery, Acting Skills, Indomitable Will, Battle Meditation, Sensing Death And Force-aided Acrobatics.',  TRUE, 0, 'Professor');

--ROLES
insert into role(id, role) values
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN'),
(3, 'ROLE_STUDENT'),
(4, 'ROLE_PROFESSOR');

-- USER_ROLES
insert into user_roles (user_id, roles_id) values
(100, 1),
(101, 1),
(102, 1),
(103, 1),
(104, 1),
(105, 1),
(106, 1),
(107, 1),
(100, 3),
(102, 3),
(103, 3),
(104, 3),
(101, 4),
(105, 4),
(106, 4),
(107, 4);


-- TERM_PAPER

-- DOCUMENT
insert into DOCUMENT (id, document_Type, title,is_Final) values (200, 2, 'TAC sobre o melhor framework do mundo', 'false');
insert into DOCUMENT (id, document_Type, title,is_Final) values (400, 2, 'TAC sobre engenharia de software', 'false');

insert into TERM_PAPER (id, theme, title, advisor_id, author_id) values
(100, 'Spring Boot', 'TCC sobre o melhor framework do mundo', 101, 100),
(101, 'Engenharia de Software', 'TCC sobre o impacto de scrum no mundo', 101, 102),
(102, 'Engenharia de Software', 'Scrum é a vida Jedi', 101, 103),
(103, 'Inteligência Artificial', 'Modelo estatístico para avaliar o desempenho de um sistema de correção de erro quântico', 101, 104),
(104, 'Redes de computadores', 'Técnicas de seleção de nós em redes cooperativas cognitivas com compartilhamento espectral', 101, 105),
(105, 'Arquitetura de Computadores', '	Detecção de cantos em formas binárias planares e aplicação em recuperação de formas', 105, 106),
(106, 'Inteligência Artificial', 'Análise de imagens por meio da matriz de interdependência e da transformação estrutural multiescala', 106, 107),
(107, 'Inteligência Artificial', 'Análise do contexto e dos resultados da aprendizagem da avaliação educacional em um curso de graduação em engenharia', 105, 107),
(108, 'Inteligência Artificial', 'Detecção e segmentação de estruturas em imagens médicas de retina', 105, 107);


insert into DOCUMENT (id, document_type, is_final, term_paper_id, file_id)	values
(98,0,0,100, 0),
(99,0,0,100, 0),
(101,0,1,100, 0),
(102,0,1,101, 0),
(103,1,1,100, 0),
(104,2,1,100, 0),
(105,1,1,101, 0),
(106,0,1,103, 0),
(107,1,1,104, 0),
(108,1,1,105, 0),
(109,1,1,106, 0),
(110,1,1,107, 0),
(111,1,0,108, 0);


insert into EVALUATION_BOARD (id, document_id) values
(101, 101),
(102, 102),
(103, 103),
(104, 104),
(105, 105),
(106, 106),
(107, 107),
(108, 108),
(109, 109),
(110, 110);

insert into EVALUATION_BOARD_PROFESSORS (EVALUATION_BOARD_ID, PROFESSORS_ID) values
(101, 101),
(102, 101),
(101, 105),
(101, 106),
(103, 101),
(104, 101),
(105, 101),
(106, 101),
(107, 101),
(108, 105),
(109, 106),
(110, 105);

