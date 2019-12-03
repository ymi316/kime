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
