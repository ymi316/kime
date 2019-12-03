package kr.schedule.project.service;

import java.util.HashMap;
import java.util.Random;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import kr.schedule.project.dao.MemberDAO;
import kr.schedule.project.vo.MemberVO;

@Service
public class MemberService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MemberService.class);

	@Autowired
	private MemberDAO memberDao;
	@Autowired
	private JavaMailSender mailSender;
	
	public int getCount() {
		return memberDao.getCount();
	}

	public void insert(MemberVO vo) {
		// 1. 데이터 검증
		if (vo == null || vo.getM_id() == null)
			return;
		// 2. DB 저장
		memberDao.insert(vo);
		// 3. 메일발송(회원가입 축하메일발송)
		// JavaMailSender mailSender = getMailSender();
		sendEmail(vo);
	}
	public void sendEmail(MemberVO memberVO) {
        MimeMessagePreparator preparator = getMessagePreparator(memberVO);
 
        try {
            mailSender.send(preparator);
            System.out.println("메일 보내기 성공 **********");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
 
    private MimeMessagePreparator getMessagePreparator(final MemberVO memberVO) {
 
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
 
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setFrom("customerserivces@yourshop.com");
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(memberVO.getM_id()));
                mimeMessage.setText(memberVO.getM_name()
                        + "님, 반갑습니다.<br> " 
                		+ "회원가입을 완료하려면 다음 링크를 클릭해 인증하시길 바랍니다.<br>"
                        + "<a href='http://localhost:8080/member/member/confirm?userid="+memberVO.getM_id()+"'>인증</a>");
                mimeMessage.setSubject("회원 가입을 축하드립니다.");
            }
        };
        return preparator;
    } 
    
    
	/*
	 * 메일 보내기 객체를 코드로 만들면 XML로 대체 가능하다
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("sungnam201908@gmail.com");
		mailSender.setPassword("woaldjqtek2");

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.debug", "true");// Prints out everything on screen
		mailSender.setJavaMailProperties(javaMailProperties);
		
		return mailSender;
	}
	 */
    
	public MemberVO selectByUserid(HashMap<String, String> map) {
		return memberDao.selectByUserid(map);
	}
	
	public MemberVO selectByEmail(HashMap<String, String> map) {
		return memberDao.selectByEmail(map);
	}
	
    // 가입 후 인증하기
    public void confirm(String userid) {
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("userid", userid);
    	MemberVO vo = memberDao.selectByUserid(map);
    	// DB 변경
    	map.put("use", "2"); // 인증 된경우 2로 하기로 했음
    	//if(vo!=null) {
    	//	memberDao.updateUse(map);
    	//}
    }
	
	//로그인 처리
	public MemberVO loginOk(MemberVO memberVO) {
		MemberVO vo = null;
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("m_id", memberVO.getM_id());
		vo = memberDao.selectByUserid(map);
		if(vo!=null) {
			if(!vo.getM_pwd().equals(memberVO.getM_pwd())) {
				vo = null;
			}
		}
		return vo;
	}
	
	public MemberVO idSearch(MemberVO memberVO) {
		MemberVO vo = null;
		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("username", memberVO.getUsername());
//		map.put("phone", memberVO.getPhone());
//		vo = memberDao.selectByUserName(map);
		return vo;
	}
	public MemberVO pwSearch(MemberVO memberVO) {
		logger.info(memberVO);
		MemberVO vo = null;
		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("userid", memberVO.getUserid());
		vo = memberDao.selectByUserid(map);
		logger.info(vo);
		if(!(vo!=null && vo.getM_name().equals(memberVO.getM_name()) && vo.getM_phone().equals(memberVO.getM_phone()))) {
			vo=null;
		}else {
			//임시 비밀번호를 만들어서 메일을 발송
			StringBuilder sb = new StringBuilder();
			// 1. 임시 비번 생성
			Random rnd = new Random();
			for(int i=0;i<4;i++) {
				sb.append((char)(rnd.nextInt(26)+'A')+""); // 대문자 생성
				sb.append((char)(rnd.nextInt(26)+'a')+""); // 소문자 생성
				sb.append((char)(rnd.nextInt(10)+'0')+""); // 숫자 생성				
			}
			final String password = sb.toString();
			// 2. 임시 비번으로 DB를 업데이트 하고
			map.put("password", password);
//			memberDao.updatePassword(map);
			// 3. 메일 발송		
			final MemberVO vo2 = vo;
	        MimeMessagePreparator preparator = new MimeMessagePreparator() {
	        	public void prepare(MimeMessage mimeMessage) throws Exception {
	                mimeMessage.setFrom("sungnam201908@gmail.com");
	                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(vo2.getM_id()));
	                mimeMessage.setText(vo2.getM_id()
	                        + "님, 반갑습니다.<br> " 
	                		+ "아래 임시 비밀번호를 이용해 로그인 하세요<br>'"
	                        + password+"'");
	                mimeMessage.setSubject("임시 비밀 번호 입니다.");
	            }
	        };	 			
		}
		return vo;
	}
	
	
}
