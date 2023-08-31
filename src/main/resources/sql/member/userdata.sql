
INSERT INTO `user` (`user_id`,  `user_name`, `user_password`, `user_phone`, `user_gender`, `user_date`, `user_mbti`,`user_img`)
VALUES
    ('john_doe','John Doe', 'password123', '123-456-7890', 'M', '2023-08-21', 'ENTJ','default1.png'),
    ('jane_smith', 'Jane Smith', 'pass4321', '987-654-3210', 'F', '2023-08-21', 'INFP','default2.png'),
    ('alex_brown', 'Alex Brown', 'securepwd', '555-555-5555', 'M', '2023-08-21', 'INTJ','default1.png'),
    ('test', 'yeong', '1234', '555-555-5555', 'M', '2023-08-21', 'INTJ','default1.png'),
    ('sample@example.com', '샘플', '1234', '555-555-5555', 'M', '2023-08-21', 'INTJ','default1.png');
select * from user;

INSERT INTO `qna` (`qna_writer`,  `qna_title`, `qna_content`, `qna_date`, `qna_answer`)
VALUES
    ('john_doe','제목1', '내용은 이것입니다1.', '2023-08-21', '답변은 아직입니다1.'),
    ('seokjin','제목2', '내용은 이것입니다2.', '2023-08-21', '답변은 아직입니다2.'),
    ('john_doe','제목3', '내용은 이것입니다3.', '2023-08-21', '답변은 아직입니다3.'),
    ('seokjin','제목4', '내용은 이것입니다4.', '2023-08-21', '답변은 아직입니다4.'),
    ('john_doe','제목5', '내용은 이것입니다5.', '2023-08-21', '답변은 아직입니다5.');

select * from qna;

