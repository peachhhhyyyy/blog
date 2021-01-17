drop table board;
drop sequence board_SEQ;
drop table member;
drop sequence member_SEQ;
purge recyclebin;

CREATE TABLE member
(
    seq      NUMBER          NOT NULL, 
    name     VARCHAR2(20)    NULL, 
    email    VARCHAR2(30)    NULL, 
    pwd      VARCHAR2(20)    NULL, 
    rdate    DATE            NULL, 
    CONSTRAINT MEMBER_PK PRIMARY KEY (seq)
);

CREATE SEQUENCE member_SEQ
increment by 1 start with 1 nocache;

ALTER TABLE member
    ADD CONSTRAINT FK_member_name_board_name FOREIGN KEY (name, email)
        REFERENCES board (name, email)

insert into member values(member_SEQ.nextval, '±èÃ¶¼ö', 'kim@naver.com','1234', SYSDATE);

CREATE TABLE board
(
    seq        NUMBER           NOT NULL, 
    name       VARCHAR2(20)     NULL, 
    email      VARCHAR2(30)     NULL, 
    subject    VARCHAR2(100)    NULL, 
    content    VARCHAR2(390)    NULL, 
    rdate      DATE             NULL, 
    fname      VARCHAR2(50)     NULL, 
    ofname     VARCHAR2(50)     NULL, 
    fsize      NUMBER           NULL, 
    CONSTRAINT BOARD_PK PRIMARY KEY (seq)
);
/

CREATE SEQUENCE board_SEQ
increment by 1 start with 1 nocache;
INSERT INTO board (seq, name, email, subject, content, rdate) VALUES (board_SEQ.nextval, '±èÃ¶¼ö', 'kim@naver.com', 'Á¦¸ñ', '³»¿ë', sysdate);

select * from member;
select * from board;
commit;