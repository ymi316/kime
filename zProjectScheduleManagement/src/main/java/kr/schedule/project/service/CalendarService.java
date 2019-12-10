package kr.schedule.project.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kr.schedule.project.dao.CalendarDAO;
import kr.schedule.project.vo.CalendarVO;

@Service
public class CalendarService {
	
	@Autowired
	private CalendarDAO calendarDAO;
	
	private static final Logger logger = Logger.getLogger(CalendarService.class);
	
	public List<CalendarVO> readJson(String filepath){
		FileInputStream fis = null;
		List<CalendarVO> list = null;		
		try {
			fis = new FileInputStream(filepath);
			InputStreamReader isr = new InputStreamReader(fis);
			Gson gson = new Gson();
			list = gson.fromJson(isr, new TypeToken<List<CalendarVO>>() {}.getType());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		return list;		
	}
	
	//calendar를 json파일로 저장하기
	public void saveJSON(String path, String userid) {
		logger.info("saveJSON : "+userid+"\n");
		// 저장경로와 저장할 VO를 인수로 받는다.
		PrintWriter pw = null;	
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		List<CalendarVO> list = calendarDAO.selectByUserid(map);
		logger.info("saveJSON : "+list+"\n");
		try {
			pw = new PrintWriter(path);
			Gson gson = new Gson();
			if(!list.isEmpty()) gson.toJson(list, pw); // 읽는건 from 저장은 to			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			pw.close(); // 꼭 닫아주기
		}
	}

	public List<CalendarVO> selectByUserid(String userid) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		List<CalendarVO> list = null;
		if(calendarDAO.selectCount(map)>0) {
			list = calendarDAO.selectByUserid(map);
		}				
		return list;
	}

	public void update(CalendarVO vo) {
		if(vo.isAllDay()) vo.setDayNumber(1);
		else vo.setDayNumber(0);
		calendarDAO.update(vo);
	}
	// 삭제
	public void delete(int _id) {
		calendarDAO.delete(_id);
	}
	// 삽입
	public void insert(CalendarVO vo) {
		if(vo.isAllDay()) vo.setDayNumber(1);
		else vo.setDayNumber(0);
		calendarDAO.insert(vo);
	}
}
