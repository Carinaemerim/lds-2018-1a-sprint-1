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
(107, 'orientador4', '$2a$10$Qji2/icFWIGGQEAv8bbwNuKGrSZyiUUPqE/0SNO2M.BpU.NA6xPhW', 'Darth Vader 4','yodadv4@stars.wars','Masters Unidentified Jedi, Garro, Qui-Gon Jinn', 'Deflect Force Lightning, Strategic Mastery, Acting Skills, Indomitable Will, Battle Meditation, Sensing Death And Force-aided Acrobatics.',  TRUE, 0, 'Professor'),
(108, 'user5', '$2a$10$Qji2/icFWIGGQEAv8bbwNuKGrSZyiUUPqE/0SNO2M.BpU.NA6xPhW', 'Zezinho Fulano de Tal','yodouser@stars.wars','Slave Unidentified Jedi, Strategic Mastery, Acting Skills, Indomitable Will, Battle Meditation','Sensing Death And Force-aided Acrobatics.',  TRUE, 0, 'Student'),
(109, 'user6', '$2a$10$Qji2/icFWIGGQEAv8bbwNuKGrSZyiUUPqE/0SNO2M.BpU.NA6xPhW', 'Luizinho vai Sambá','yodouser109@stars.wars','Slave Unidentified Jedi, Strategic Mastery, Acting Skills, Indomitable Will, Battle Meditation','Sensing Death And Force-aided Acrobatics.',  TRUE, 0, 'Student');

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
(108, 1),
(109, 1),
(100, 3),
(102, 3),
(103, 3),
(104, 3),
(108, 3),
(109, 3),
(101, 4),
(105, 4),
(106, 4),
(107, 4);


-- Course

insert into COURSE (ID, TITLE) values
(300,'Análise e Desenvolvimento de Sistemas'),
(301,'Automação Industrial'),
(302,'Logística'),
(303,'Licenciatura em Matemática');


insert into ACADEMIC_YEAR (ID, TITLE) values
(498,'2016/02'),
(499,'2017/01'),
(500,'2017/02'),
(501,'2018/01');

-- DOCUMENT
insert into DOCUMENT (id, document_Type, title,is_Final) values (200, 2, 'TAC sobre o melhor framework do mundo', 'false');
insert into DOCUMENT (id, document_Type, title,is_Final) values (400, 2, 'TAC sobre engenharia de software', 'false');

insert into TERM_PAPER (id, theme, title, advisor_id, author_id, description, ACADEMIC_YEAR_ID ) values
(999, 'Antigo Spring Boot', 'O ANTIGO Louco TCC sobre o melhor framework do mundo', 101, 100, 'Uma breve descrição do trabalho TCC sobre o melhor framework do mundo', 500),
(1000, 'Spring Boot', 'O Louco TCC sobre o melhor framework do mundo', 101, 100, 'Uma breve descrição do trabalho TCC sobre o melhor framework do mundo', 501),
(1001, 'Engenharia de Software', 'TCC sobre o impacto de scrum no mundo', 101, 102, 'Uma breve descrição do trabalho TCC sobre o impacto de scrum no mundo', 501),
(1002, 'Engenharia de Software', 'Scrum é a vida Jedi', 101, 103, 'Uma breve descrição do trabalho Scrum é a vida Jedi', 501),
(1003, 'Inteligência Artificial', 'Modelo estatístico para avaliar o desempenho de um sistema de correção de erro quântico mais promissor que você ja viu', 101, 104,'Uma breve descrição do trabalho Modelo estatístico para avaliar o desempenho de um sistema de correção de erro quântico', 501),
(1004, 'Engenharia de Software', 'Sistemas ERP: características, custos e tendências', 101, 108,'Uma breve descrição do trabalho Sistemas ERP: características, custos e tendências', 501),
(1005, 'Arquitetura de Computadores', 'Como o Hardware evoluiu em comparação a degradação mental dos humanos', 101, 109,'Uma breve descrição desse trabalho magnífico', 501);



insert into DOCUMENT (id, document_type, is_final, term_paper_id, file_id )	values
(97,0,1,999, 0),
(98,0,0,1000, 0),
(99,0,0,1000, 0),
(101,0,1,1000, 0),
(102,0,1,1001, 0),
(103,1,1,1000, 0),
(104,2,1,1000, 0),
(105,1,1,1001, 0),
(106,0,1,1003, 0),
(107,0,1,1004, 0),
(108,0,1,1005, 0),
(109,1,1,1005, 0),
(110,2,1,1005, 0);


insert into EVALUATION_BOARD (id, document_id) values
(99, 97),
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
(99, 101),
(101, 101),
(102, 101),
(103, 101),
(104, 101),
(105, 101),
(106, 101),
(107, 101),
(104, 105),
(103, 105),
(108, 101),
(109, 101),
(104, 106),
(110, 101);


--Evaluation
INSERT INTO  EVALUATION (ID ,EVALUATION_TYPE, DOCUMENT_ID , CONSIDERATIONS, STATUS, APPRAISER_ID, IS_FINAL ) VALUES (499, 'Advice', 97, 'Consideracoes do tema estao aqui. bom tema!', 1, 101, TRUE);
INSERT INTO  EVALUATION (ID ,EVALUATION_TYPE, DOCUMENT_ID , CONSIDERATIONS, STATUS, APPRAISER_ID, IS_FINAL ) VALUES (500, 'Advice', 101, 'Consideracoes do tema estao aqui. bom tema!', 0, 101, TRUE);
INSERT INTO  EVALUATION (ID ,EVALUATION_TYPE, DOCUMENT_ID , CONSIDERATIONS, STATUS, APPRAISER_ID ) VALUES (501, 'Advice', 105, 'Consideracoes do Proposta estao aqui. Melhor tentar novamente!', 2, 101);
INSERT INTO  EVALUATION (ID ,EVALUATION_TYPE, DOCUMENT_ID , CONSIDERATIONS, STATUS, APPRAISER_ID, IS_FINAL ) VALUES (502, 'Advice', 103, 'Consideracoes do Proposta estao aqui. Parabéns!!', 0, 101, TRUE);

INSERT INTO  EVALUATION (ID ,EVALUATION_TYPE, DOCUMENT_ID , CONSIDERATIONS ,ADEQUACY_OF_PRESENTATION ,CLOSING_EXPECTED_TIME ,CONCLUSION ,DEVELOPMENT_IN_LOGICAL_SEQUENCE ,EXPERIENCED_SOLUTION ,LITERATURE_REVIEW ,METHODOLOGY ,OBJECTIVE ,PRESENTATION ,SUBJECT_DOMAIN ,THEORETICAL_APPROACH ,VOCABULARY, APPRAISER_ID, IS_FINAL )
  VALUES (503, 'Grade', 104,'condisedariosn do tcc ok foi bom',7,8,7,8,7,8,9,6,5,4,3,7, 101, TRUE);
INSERT INTO  EVALUATION (ID ,EVALUATION_TYPE, DOCUMENT_ID , CONSIDERATIONS, STATUS, APPRAISER_ID, IS_FINAL ) VALUES (504, 'Advice', 102, 'Bom tema, parabéns!', 0, 101, TRUE);
INSERT INTO  EVALUATION (ID ,EVALUATION_TYPE, DOCUMENT_ID , CONSIDERATIONS, STATUS, APPRAISER_ID ) VALUES (505, 'Advice', 107, 'Não está de acordo com o documento do PPC. Tema Inadequado!', 1, 101);
INSERT INTO  EVALUATION (ID ,EVALUATION_TYPE, DOCUMENT_ID , CONSIDERATIONS ,ADEQUACY_OF_PRESENTATION ,CLOSING_EXPECTED_TIME ,CONCLUSION ,DEVELOPMENT_IN_LOGICAL_SEQUENCE ,EXPERIENCED_SOLUTION ,LITERATURE_REVIEW ,METHODOLOGY ,OBJECTIVE ,PRESENTATION ,SUBJECT_DOMAIN ,THEORETICAL_APPROACH ,VOCABULARY, APPRAISER_ID  )
  VALUES (506, 'Grade', 104,'condisedariosn do tcc ok foi bom orientador 2',0,0,0,0,0,0,0,0,0,9,9,7, 105);
INSERT INTO  EVALUATION (ID ,EVALUATION_TYPE, DOCUMENT_ID , CONSIDERATIONS, STATUS, APPRAISER_ID, IS_FINAL ) VALUES (507, 'Advice', 103, 'Consideracoes do Proposta estao aqui. Parabéns!!', 0, 105, TRUE);

INSERT INTO  EVALUATION (ID ,EVALUATION_TYPE, DOCUMENT_ID , CONSIDERATIONS, STATUS, APPRAISER_ID, IS_FINAL ) VALUES (508, 'Advice', 108, 'Consideracoes do Proposta estao aqui. Parabéns Bommmm hein!!', 0, 101, TRUE);
INSERT INTO  EVALUATION (ID ,EVALUATION_TYPE, DOCUMENT_ID , CONSIDERATIONS, STATUS, APPRAISER_ID, IS_FINAL ) VALUES (509, 'Advice', 109, 'Consideracoes do Proposta estao aqui. Satisfatório!!', 0, 101, TRUE);
-- SET EVALUATION_BOARD_ID IN Document
UPDATE DOCUMENT SET EVALUATION_BOARD_ID = 99  WHERE ID=  97;
UPDATE DOCUMENT SET EVALUATION_BOARD_ID = 101  WHERE ID=  101;
UPDATE DOCUMENT SET EVALUATION_BOARD_ID = 102  WHERE ID=  102;
UPDATE DOCUMENT SET EVALUATION_BOARD_ID = 103  WHERE ID=  103;
UPDATE DOCUMENT SET EVALUATION_BOARD_ID = 104  WHERE ID=  104;
UPDATE DOCUMENT SET EVALUATION_BOARD_ID = 105  WHERE ID=  105;
UPDATE DOCUMENT SET EVALUATION_BOARD_ID = 106  WHERE ID=  106;
UPDATE DOCUMENT SET EVALUATION_BOARD_ID = 107  WHERE ID=  107;
UPDATE DOCUMENT SET EVALUATION_BOARD_ID = 108  WHERE ID=  108;
UPDATE DOCUMENT SET EVALUATION_BOARD_ID = 109  WHERE ID=  109;
UPDATE DOCUMENT SET EVALUATION_BOARD_ID = 110  WHERE ID=  110;

--Schedule
INSERT INTO  TASK (ID ,PERIOD, TITLE, DESCRIPTION , DEADLINE, TYPE_EVALUATION) VALUES (100, '2018/01', 'Definir Orientador', 'Encontrar ´professor com interresse na area', PARSEDATETIME('24/03/2018','dd/MM/yyyy'), 1);
INSERT INTO  TASK (ID ,PERIOD, TITLE, DESCRIPTION , DEADLINE, TYPE_EVALUATION) VALUES (200, '2018/01', 'Entregar proposta de TCC', 'documento para a coordenação', PARSEDATETIME('22/03/2018','dd/MM/yyyy'), 2);
INSERT INTO  TASK (ID ,PERIOD, TITLE, DESCRIPTION , DEADLINE, TYPE_EVALUATION) VALUES (300, '2018/01','Entregar Monografia', 'Formatar parte do trabalho de acordo com regras ABNT', PARSEDATETIME('22/03/2018','dd/MM/yyyy'), 0);
INSERT INTO  TASK (ID ,PERIOD, TITLE, DESCRIPTION , DEADLINE, TYPE_EVALUATION) VALUES (110, '2018/01', 'task 1', 'Encontrar ´professor com interresse na area', PARSEDATETIME('24/03/2018','dd/MM/yyyy'), 1);
INSERT INTO  TASK (ID ,PERIOD, TITLE, DESCRIPTION , DEADLINE, TYPE_EVALUATION) VALUES (220, '2018/01', 'task 2', 'documento para a coordenação', PARSEDATETIME('22/03/2018','dd/MM/yyyy'), 2);
INSERT INTO  TASK (ID ,PERIOD, TITLE, DESCRIPTION , DEADLINE, TYPE_EVALUATION) VALUES (330, '2018/01','task 3', 'Formatar parte do trabalho de acordo com regras ABNT', PARSEDATETIME('22/03/2018','dd/MM/yyyy'), 0);
INSERT INTO  TASK (ID ,PERIOD, TITLE, DESCRIPTION , DEADLINE, TYPE_EVALUATION) VALUES (140, '2018/01', 'task 4', 'Encontrar ´professor com interresse na area', PARSEDATETIME('24/03/2018','dd/MM/yyyy'), 1);
INSERT INTO  TASK (ID ,PERIOD, TITLE, DESCRIPTION , DEADLINE, TYPE_EVALUATION) VALUES (250, '2018/01', 'task 5', 'documento para a coordenação', PARSEDATETIME('22/03/2018','dd/MM/yyyy'), 2);
INSERT INTO  TASK (ID ,PERIOD, TITLE, DESCRIPTION , DEADLINE, TYPE_EVALUATION) VALUES (360, '2018/01','task 6', 'Formatar parte do trabalho de acordo com regras ABNT', PARSEDATETIME('22/03/2018','dd/MM/yyyy'), 0);
INSERT INTO  TASK (ID ,PERIOD, TITLE, DESCRIPTION , DEADLINE, TYPE_EVALUATION) VALUES (170, '2018/01', 'ask 7', 'Encontrar ´professor com interresse na area', PARSEDATETIME('24/03/2018','dd/MM/yyyy'), 1);
INSERT INTO  TASK (ID ,PERIOD, TITLE, DESCRIPTION , DEADLINE, TYPE_EVALUATION) VALUES (280, '2018/01', 'paginacao 1', 'documento para a coordenação', PARSEDATETIME('25/04/2018','dd/MM/yyyy'), 2);
INSERT INTO  TASK (ID ,PERIOD, TITLE, DESCRIPTION , DEADLINE, TYPE_EVALUATION) VALUES (390, '2018/01','paginacao 2', 'Formatar parte do trabalho de acordo com regras ABNT', PARSEDATETIME('15/04/2018','dd/MM/yyyy'), 0);
INSERT INTO  TASK (ID ,PERIOD, TITLE, DESCRIPTION , DEADLINE, TYPE_EVALUATION) VALUES (1100, '2018/01', 'paginacao 3', 'Encontrar ´professor com interresse na area', PARSEDATETIME('18/04/2018','dd/MM/yyyy'), 1);
INSERT INTO  TASK (ID ,PERIOD, TITLE, DESCRIPTION , DEADLINE, TYPE_EVALUATION) VALUES (2110, '2018/01', 'paginacao 4', 'documento para a coordenação', PARSEDATETIME('06/04/2018','dd/MM/yyyy'), 2);
INSERT INTO  TASK (ID ,PERIOD, TITLE, DESCRIPTION , DEADLINE, TYPE_EVALUATION) VALUES (3120, '2018/01','Entregar Monografia', 'Formatar parte do trabalho de acordo com regras ABNT', PARSEDATETIME('22/03/2018','dd/MM/yyyy'), 0);
INSERT INTO  TASK (ID ,PERIOD, TITLE, DESCRIPTION , DEADLINE, TYPE_EVALUATION) VALUES (1130, '2018/01', 'Definir Orientador', 'Encontrar ´professor com interresse na area', PARSEDATETIME('24/03/2018','dd/MM/yyyy'), 1);
INSERT INTO  TASK (ID ,PERIOD, TITLE, DESCRIPTION , DEADLINE, TYPE_EVALUATION) VALUES (2140, '2018/01', 'Entregar proposta de TCC', 'documento para a coordenação', PARSEDATETIME('22/03/2018','dd/MM/yyyy'), 2);
INSERT INTO  TASK (ID ,PERIOD, TITLE, DESCRIPTION , DEADLINE, TYPE_EVALUATION) VALUES (3150, '2018/01','Entregar Monografia', 'Formatar parte do trabalho de acordo com regras ABNT', PARSEDATETIME('22/03/2018','dd/MM/yyyy'), 0);
INSERT INTO  TASK (ID ,PERIOD, TITLE, DESCRIPTION , DEADLINE, TYPE_EVALUATION) VALUES (101, '2018/02', 'Definir Orientador', 'Encontrar ´professor com interresse na area', PARSEDATETIME('30/03/2018','dd/MM/yyyy'), 1);
INSERT INTO  TASK (ID ,PERIOD, TITLE, DESCRIPTION , DEADLINE, TYPE_EVALUATION) VALUES (202, '2018/02', 'Entregar proposta de TCC', 'documento para a coordenação', PARSEDATETIME('21/03/2018','dd/MM/yyyy'), 2);
INSERT INTO  TASK (ID ,PERIOD, TITLE, DESCRIPTION , DEADLINE, TYPE_EVALUATION) VALUES (303, '2018/02','Entregar Monografia', 'Formatar parte do trabalho de acordo com regras ABNT', PARSEDATETIME('09/03/2018','dd/MM/yyyy'), 1);

--INSERT INTO MESSAGE(ID, CONTENT, DATE, VIEWED, RECEIVER_ID, SENDER_ID) VALUES (1, 'Commita tuas atualizações', '2018-03-21', true, 100, 101);
--INSERT INTO MESSAGE(ID, CONTENT, DATE, VIEWED, RECEIVER_ID, SENDER_ID) VALUES (2, 'blz', '2018-03-22', true, 101, 100);



