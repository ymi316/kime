package kr.schedule.project.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.schedule.project.dao.TestDao;


@Service
public class TestService {

	@Autowired
	private TestDao testDAO;
	// mybatis 테스트 메서드
	public String today() {
		return testDAO.today(); 
	}   
	 
	public Integer mul(int num1,int num2){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("num1",num1);
		map.put("num2",num2);
		return testDAO.mul(map);
	} 
	
	public Integer sum(int num1,int num2,int num3){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("num1",num1);
		map.put("num2",num2);
		map.put("num3",num3);
		return testDAO.mul(map);
	}
}
