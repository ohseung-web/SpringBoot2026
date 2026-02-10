create table replyBoard(
num int auto_increment primary key,
writer varchar(20),
email varchar(50),
subject varchar(50),
password varchar(10),
reg_date datetime default now(),
ref int,
re_step int,
re_level int,
readcount int default 0,
content varchar(1000)
);

INSERT INTO replyBoard
(writer, email, subject, password, reg_date, ref, re_step, re_level, readcount, content)
VALUES
('오티', 'oti@test.com', '원글1', '1234', NOW(), 1, 1, 1, 0, '원글1 내용입니다'),
('달래', 'dalae@test.com', '원글2', '1234', NOW(), 2, 1, 1, 0, '원글2 내용입니다'),
('길동', 'gildong@test.com', '원글3', '1234', NOW(), 3, 1, 1, 0, '원글3 내용입니다'),
('관리자', 'admin@test.com', '원글4', '1234', NOW(), 4, 1, 1, 0, '원글4 내용입니다'),
('나리', 'nari@test.com', '원글5', '1234', NOW(), 5, 1, 1, 0, '원글5 내용입니다');