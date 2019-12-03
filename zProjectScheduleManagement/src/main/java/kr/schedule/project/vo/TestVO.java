package kr.schedule.project.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestVO implements Serializable {
	private static final long serialVersionUID = 8857900507748744024L;
	
	private int idx; 
	private String name; 
	private int age;
}
