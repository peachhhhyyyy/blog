drop table reply;
drop sequence reply_SEQ;
purge recyclebin;

CREATE TABLE reply
(
    seq          NUMBER          NOT NULL, 
    name         VARCHAR2(20)    NULL, 
    pwd          VARCHAR2(20)    NULL, 
    content      VARCHAR2(390)    NULL, 
    rdate	    DATE           NULL,
    lev          NUMBER          NULL, 
    sunbun       NUMBER          NULL, 
    board_seq    NUMBER          NOT NULL REFERENCES BOARD(seq), 
    CONSTRAINT REPLY_PK PRIMARY KEY (seq)
);

CREATE SEQUENCE reply_SEQ
increment by 1 start with 1 nocache;

