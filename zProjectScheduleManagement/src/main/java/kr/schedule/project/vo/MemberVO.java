package kr.schedule.project.vo;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/*
 *  idx             INT             NOT NULL, 
    m_name          VARCHAR2(50)    NOT NULL, 
    m_id            VARCHAR2(50)    NOT NULL, 
    m_pwd           VARCHAR2(50)    NOT NULL, 
    m_phone         VARCHAR2(50)    NOT NULL, 
    m_email         VARCHAR2(50)    NOT NULL, 
    m_nick          VARCHAR2(50)    NULL, 
    m_jender        INT             NULL, 
    m_birth         TIMESTAMP       NULL, 
    m_last_login    TIMESTAMP       NULL
 */

@Data
@XmlRootElement
public class MemberVO implements Serializable {
	private static final long serialVersionUID = -2142112263908327470L;
	private int idx;
	private String m_name;
	private String m_id;
	private String m_pwd;
	private String m_phone;
	private String m_email;
	private String m_nick;
	private int m_jender;
	private Date m_birth;
	private Date m_last_login;
}
