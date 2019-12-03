<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">

    <!-- Title Page-->
    <title>회원가입</title>

    <!-- Icons font CSS-->
    <link href="${pageContext.request.contextPath }/resources/vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <link href="${pageContext.request.contextPath }/resources/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <!-- Font special for pages-->
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">

    <!-- Vendor CSS-->
    <link href="${pageContext.request.contextPath }/resources/vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="${pageContext.request.contextPath }/resources/vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link href="${pageContext.request.contextPath }/resources/css/main2.css" rel="stylesheet" media="all">
    
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    
	<script type="text/javascript">
	
	//폼 검증하는 함수
	function formCheck(){
		var data = $("#m_id").val();
		if(!data || data.trim().length==0){
			alert('아이디는 반드시 입력해야합니다.');
			$("#m_id").val("");
			$("#m_id").focus();
			return false;
		}

		var data = $("#m_pwd").val();
		if(!data || data.trim().length==0){
			alert('비밀번호는 반드시 입력해야 합니다.');
			$("#m_pwd").val("");
			$("#m_pwd").focus();
			return false;
		}
		var data = $("#m_pwd2").val();
		if(!data || data.trim().length==0){
			alert('비밀번호 확인는 반드시 입력해야 합니다.');
			$("#m_pwd2").val("");
			$("#m_pwd2").focus();
			return false;
		}
		if($("#m_pwd").val() != $("#m_pwd2").val()){
			alert('비밀번호가 일치하지 않습니다.');
			$("#m_pwd2").focus();
			return false;
		}

		var data = $("#m_email").val();
		if(!data || data.trim().length==0){
			alert('이메일은 반드시 입력해야합니다.');
			$("#m_email").val("");
			$("#m_email").focus();
			return false;
		}
		
		if(!validateEmail(data)){
			alert('이메일 형식이 아닙니다.');
			$("#m_email").focus();
			return false;
		}
		
		if($("#emailCheck").css('color')=='rgb(255, 0, 0)'){
			$("#m_email").val("");
			$("#emailCheck").html("");
			$("#m_email").focus();
			alert('사용 불가능한 이메일입니다.');
			return false;
		}		
		
		var data = $("#m_name").val();
		if(!data || data.trim().length==0){
			alert('사용자 이름은 반드시 입력해야 합니다.');
			$("#m_name").val("");
			$("#m_name").focus();
			return false;
		}
		var data = $("#m_nick").val();
		if(!data || data.trim().length==0){
			alert('사용자 별명은 반드시 입력해야 합니다.');
			$("#m_nick").val("");
			$("#m_nick").focus();
			return false;
		}
		var data = $("#m_birth").val();
		if(!data || data.trim().length==0){
			alert('생년월일은 반드시 입력해야 합니다.');
			$("#m_birth").val("");
			$("#m_birth").focus();
			return false;
		}
		var data = $("#m_phone").val();
		if(!data || data.trim().length==0){
			alert('전화번호는 반드시 입력해야 합니다.');
			$("#m_phone").val("");
			$("#m_phone").focus();
			return false;
		}
		
		if(!validatePhone(data) || data.trim().length==0){
			alert('000-0000-0000의 형식으로 입력해주십시오.');
			$("#m_phone").focus();
			return false;
		}
		
		
		return true;
	}
	
	function validateEmail(email) {
		var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
		return re.test(email);
	}
	function validatePhone(m_phone){
		var re = /^\d{2,3}-\d{3,4}-\d{4}$/;
		return re.test(m_phone);
	}
	function emailCheck(){
		var value= $('#m_email').val();
		if(!validateEmail(value)){
			 $('#emailCheck').css('color','red').html("이메일 형식으로 입력해주세요.");
		}
		else if(value.length>5){
			 $.ajax({
			     type:"POST",
			     url:"${pageContext.request.contextPath }/m/checkSignupEmail",
			     data:{
			        "m_email":value
			     },
 				 dataType:'json',
			     success:function(data){
			            if(data==1){
			               $('#emailCheck').css('color','red').html("사용 불가능한 이메일입니다.");
			           	}else{
			           		$('#emailCheck').css('color','green').html("사용가능한 이메일입니다.");
			            }
			         }
			    }); 
		}else{
			$('#emailCheck').html('');
		}
	}
	</script>
</head>

<body>
    <div class="page-wrapper bg-red p-t-180 p-b-100 font-robo" style="background-image: url(${pageContext.request.contextPath }/resources/images/hero_1.jpg);" >
        <div class="wrapper wrapper--w960">
            <div class="card card-2">
                <div class="card-body">
                    <h2 class="title" style="color: #007BFF">회원가입</h2>
                    <form method="POST" action="${pageContext.request.contextPath }/m/joinOk" onsubmit="return formCheck();">
                        <div class="input-group">
                            <input class="input--style-2" type="text" placeholder="사용자ID" name="m_id" id="m_id" >
                        </div>                
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                            		<input class="input--style-2" type="password" placeholder="비밀번호 입력" name="m_pwd" id="m_pwd">
                        		</div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                            		<input class="input--style-2" type="password" placeholder="비밀번호 확인" name="m_pwd2" id="m_pwd2" >
                        		</div>
                            </div>
                        </div>
                        <div class="input-group">
                            <input class="input--style-2" type="text" placeholder="사용자 이메일" name="m_email" id="m_email" onkeyup="emailCheck();" >
                            <span id="emailCheck"></span>
                        </div>          
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                            		<input class="input--style-2" type="text" placeholder="이름 입력" name="m_name" id="m_name">
                        		</div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                            		<input class="input--style-2" type="text" placeholder="별명 " name="m_nick" id="m_nick">
                        		</div>
                            </div>
                        </div>
                        
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <input class="input--style-2 js-datepicker" type="text" placeholder="생일" name="m_birth" id="m_birth">
                                    <i class="zmdi zmdi-calendar-note input-icon js-btn-calendar"></i>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                            		<input class="input--style-2" type="text" placeholder="전화번호(000-0000-0000)" name="m_phone" id="m_phone" >
                        		</div>
                            </div>                            
                        </div>
                        
                        <div class="p-t-30" >
                            <button class="btn btn--radius btn--blue" type="button" onclick="location.href=''">돌아가기</button>
                            <button class="btn btn--radius btn--blue" type="submit">회원가입</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- Jquery JS-->
    <script src="${pageContext.request.contextPath }/resources/vendor/jquery/jquery.min.js"></script>
    <!-- Vendor JS-->
    <script src="${pageContext.request.contextPath }/resources/vendor/select2/select2.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/vendor/datepicker/moment.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/vendor/datepicker/daterangepicker.js"></script>
    <!-- Main JS-->
    <script src="${pageContext.request.contextPath }/resources/js/global.js"></script>

</body><!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>
<!-- end document-->