package kr.schedule.project.dao;

import java.util.HashMap;
import java.util.List;

import kr.schedule.project.vo.CalendarVO;
import kr.schedule.project.vo.MemberVO;

public interface CalendarDAO {
	List<CalendarVO> selectByUserid(HashMap<String,String> map);
	// 수정
	void update(CalendarVO vo); 
	// 삭제
	void delete(HashMap<String,String> map);
	// 삽입
	void insert(CalendarVO vo);
}
