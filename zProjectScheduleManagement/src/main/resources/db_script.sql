/* 회원 관리 테이블 */
CREATE TABLE member_manage
(
    idx            INT             NOT NULL, 
    id             VARCHAR2(50)    NOT NULL, 
    joindate       TIMESTAMP       NOT NULL, 
    login_check    INT             NOT NULL, 
    lev          INT             NOT NULL, 
    CONSTRAINT MEMBER_MANAGE_PK PRIMARY KEY (idx),
    CONSTRAINT uk_id UNIQUE(id)
);

// INSERT 
INSERT INTO member_manage values(member_manage_SEQ.nextval,#{m_id},SYSDATE,0,0)

DROP TABLE member_manage;

SELECT * FROM member_manage; 

CREATE SEQUENCE member_manage_SEQ
START WITH 1
INCREMENT BY 1;

/* 회원용 테이블 */
CREATE TABLE schedule_member
(
    idx             INT             NOT NULL, 
    m_id            VARCHAR2(50)    NOT NULL, 
    m_pwd           VARCHAR2(50)    NOT NULL, 
    m_name          VARCHAR2(50)    NOT NULL, 
    m_phone         VARCHAR2(50)    NOT NULL, 
    m_email         VARCHAR2(50)    NOT NULL, 
    m_nick          VARCHAR2(50)    NULL, 
    m_jender        INT             DEFAULT 1, 
    m_birth         TIMESTAMP       NULL, 
    m_last_login    TIMESTAMP       NOT NULL, 
    CONSTRAINT SCHEDULE_MEMBER_PK PRIMARY KEY (idx),
    CONSTRAINT uk_m_id UNIQUE(m_id),
    CONSTRAINT uk_m_phone UNIQUE(m_phone),
    CONSTRAINT uk_m_email UNIQUE(m_email)
);

DROP TABLE schedule_member;

CREATE SEQUENCE schedule_member_SEQ
START WITH 1
INCREMENT BY 1;

DELETE schedule_member;
SELECT * FROM schedule_member;

CREATE TABLE schedule(
    "_id"              NUMBER             PRIMARY KEY, 
    title              VARCHAR2(50)    NOT NULL, 
    description        VARCHAR2(50)    NULL, 
    "start"            VARCHAR2(50)    NOT NULL, 
    "end"              VARCHAR2(50)    NULL, 
    "type"             VARCHAR2(50)    NULL, 
    username           VARCHAR2(50)    NOT NULL, 
    backgroundColor    VARCHAR2(50)    NULL, 
    textColor          VARCHAR2(50)    NULL, 
    allDay             NUMBER             NULL
);

CREATE SEQUENCE schedule_SEQ
START WITH 1
INCREMENT BY 1;

CREATE TABLE faq
(
    idx        INT              NOT NULL, 
    title      VARCHAR2(50)     NULL, 
    content    VARCHAR2(200)    NULL, 
    id         VARCHAR2(50)     NULL, 
    regdate    TIMESTAMP        NULL, 
    CONSTRAINT FAQ_PK PRIMARY KEY (idx)
);

CREATE SEQUENCE faq_SEQ
START WITH 1
INCREMENT BY 1;

INSERT INTO faq values(faq_SEQ.nextval,'title','content','1234',SYSDATE);

UPDATE FAQ SET TITLE ='updateTitle', CONTENT ='updateContent', REGDATE=SYSDATE WHERE IDX='1';

DELETE FROM faq WHERE idx='1';

SELECT * FROM faq;
SELECT * FROM faq WHERE idx='1';

COMMIT;

SELECT * FROM schedule;
DELETE FROM schedule;

INSERT INTO schedule VALUES (schedule_SEQ.nextval,'title 01', 'description 01', '2019-12-07T09:30', '2019-12-07T15:00', 'type 01', 'username 01', 'backgroundColor 01', 'textColor 01', 01);
INSERT INTO schedule VALUES (schedule_SEQ.nextval,'title 01', 'description 01', '2019-12-01T12:30', '2019-12-01T15:30', 'type 01', 'username 01', 'backgroundColor 01', 'textColor 01', 01);
INSERT INTO schedule VALUES (schedule_SEQ.nextval,'title 01', 'description 01', '2019-12-12', '2019-12-12', 'type 01', 'username 01', 'backgroundColor 01', 'textColor 01', 01);
INSERT INTO schedule VALUES (schedule_SEQ.nextval,'title 01', 'description 01', '2019-12-15', '2019-12-15', 'type 01', 'username 01', 'backgroundColor 01', 'textColor 01', 01);

UPDATE schedule SET username='1234';
select * from schedule where username='1234';
COMMIT;

INSERT INTO schedule VALUES (schedule_SEQ.nextval,'sdf', 'sdf','2019-12-17 16:45','2019-12-17 17:45'
		, '할일', '1234', '#D25565', '#ffffff', 0); 
	
SELECT _id,TITLE,DESCRIPTION,"start","end","type",USERNAME,BACKGROUNDCOLOR,TEXTCOLOR
FROM schedule,(SELECT "_id",DECODE(ALLDAY,1,'true',0,'false') ALLDAY2 FROM schedule) table2 WHERE table2."_id" = SCHEDULE."_id" and
username='1234';

SELECT "_id",DECODE(ALLDAY,1,'true',0,'false') ALLDAY FROM schedule;

SELECT "_id",DECODE(ALLDAY,1,'true',0,'false') ALLDAY,BACKGROUNDCOLOR,DESCRIPTION,"end","start",TEXTCOLOR,TITLE,"type",USERNAME
FROM schedule WHERE USERNAME='1234';

SELECT * FROM SCHEDULE;
SELECT "_id", TITLE,DESCRIPTION,"start","end","type",USERNAME,BACKGROUNDCOLOR,TEXTCOLOR,DECODE(ALLDAY,1,'true',0,'false') ALLDAY FROM SCHEDULE WHERE USERNAME='1234';