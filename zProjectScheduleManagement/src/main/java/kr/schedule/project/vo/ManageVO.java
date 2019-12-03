package kr.schedule.project.vo;
/*
 *  idx            INT             NOT NULL, 
    id             VARCHAR2(50)    NOT NULL, 
    joindate       TIMESTAMP       NOT NULL, 
    login_check    INT             NOT NULL, 
    lev          INT             NOT NULL, 
    CONSTRAINT MEMBER_MANAGE_PK PRIMARY KEY 
 * */

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@Data
public class ManageVO implements Serializable{
	private static final long serialVersionUID = 8296477458296830677L;
	private int idx;
	private String id;
	private Date joindate;
	private int login_check;
	private int lev;
}
