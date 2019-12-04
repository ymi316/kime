package kr.schedule.project.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CalendarVO implements Serializable{
	private static final long serialVersionUID = 8676502576174328213L;
	private int _id;
	private String title;
	private String description;
	private String start;
	private String end;
	private String type;
	private String username;
	private String backgroundColor; 
	private String textColor;
	private boolean allDay;
}
