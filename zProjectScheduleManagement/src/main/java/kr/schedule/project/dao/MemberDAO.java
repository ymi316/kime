package kr.schedule.project.dao;

import java.util.HashMap;
import java.util.List;

import kr.schedule.project.vo.MemberVO;

public interface MemberDAO {
	// 1. 저장
	void insert(MemberVO vo);
	// 2. 1개 읽기(idx로 얻기)
	MemberVO selectByIdx(int idx);
	// 3. 수정
	void update(MemberVO vo); 
	// 4. 삭제
	void delete(int idx);
	// 5. 모두읽기
	List<MemberVO> selectList();
	// 6. 개수 얻기
	int getCount();
	// 7. 1개 읽기(userid로 얻기) : 로그인, 비밀번호 찾기(이메일로 보내기)
	MemberVO selectByUserid(HashMap<String,String> map);
	// 7. 1개 읽기(user_email로 얻기) : 로그인, 비밀번호 찾기(이메일로 보내기)	
	MemberVO selectByEmail(HashMap<String,String> map);
}
 