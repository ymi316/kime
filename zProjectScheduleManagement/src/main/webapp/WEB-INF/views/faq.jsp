<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">    
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Memory Planner - FAQ</title>
	
	  <!-- Bootstrap core CSS -->
	  <link href="${pageContext.request.contextPath }/resources/vendor-boot/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	
	  <!-- Custom styles for this template -->
	  <link href="${pageContext.request.contextPath }/resources/vendor-boot/css/modern-business.css" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/jquery.fancybox.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/fonts/flaticon/font/flaticon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/fonts/icomoon/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/aos.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css">
</head>

<body>

  <!-- Navigation -->
  
    <div class="site-mobile-menu site-navbar-target">
      <div class="site-mobile-menu-header">
        <div class="site-mobile-menu-close mt-3">
          <span class="icon-close2 js-menu-toggle"></span>
        </div>
      </div>
      <div class="site-mobile-menu-body"></div>
    </div>
    <header class="site-navbar py-4 js-sticky-header site-navbar-target" role="banner">

      <div class="container">
        <div class="row align-items-center">          
          <div class="col-6 col-xl-2">
            <h1 class="mb-0 site-logo"><a href="${pageContext.request.contextPath }/" class="mb-0">Memory</a></h1>
          </div> 
          <div class="col-12 col-md-10 d-none d-xl-block">
            <nav class="site-navigation position-relative text-right" role="navigation">

              <ul class="site-menu main-menu js-clone-nav mr-auto d-none d-lg-block">
                <li><a href="${pageContext.request.contextPath }/" class="nav-link">Home</a></li>
                <li><a href="${pageContext.request.contextPath }/#about-section" class="nav-link">About Us</a></li>
                <li> <a href="#" class="nav-link">FAQ</a></li>
              </ul>
            </nav>
          </div>
          <div class="col-6 d-inline-block d-xl-none ml-md-0 py-3" style="position: relative; top: 3px;"><a href="#" class="site-menu-toggle js-menu-toggle float-right"><span class="icon-menu h3"></span></a></div>
        </div>
      </div>
    </header>

  <!-- Page Content -->
  <div class="container">

    <!-- Page Heading/Breadcrumbs -->
    <h1 class="mt-4 mb-3">FAQ
      <small> - Memory Planner 사용법</small> 
    </h1>

	
    <c:if test="${not empty sessionScope.vo}">
    <ol class="breadcrumb">
      <li class="breadcrumb-item">
        <a href="${pageContext.request.contextPath }/faq/faqEdit">글 작성하기</a>
      </li>
    </ol>
	</c:if>
    <div class="mb-4" id="accordion" role="tablist" aria-multiselectable="true">
    
	<c:forEach items="${selectAll }" var="vo" >
      <div class="card">
        <div class="card-header" role="tab" id="heading${vo.idx }">
          <h5 class="mb-0">
            <a data-toggle="collapse" data-parent="#accordion" href="#collapse${vo.idx }" aria-expanded="false" aria-controls="collapse${vo.idx }">${vo.title }</a>
          </h5>
        </div>

	    <div id="collapse${vo.idx }" class="collapse show" role="tabpanel" aria-labelledby="heading${vo.idx }">
	       <div class="card-body">
	       	${vo.content }
	        <c:if test="${not empty sessionScope.vo}">
		    	<a href="${pageContext.request.contextPath }/faq/faqView?idx=${vo.idx }" >글 수정</a>		  
			</c:if>
	       </div>    
	    </div>
	  </div>    
	</c:forEach>
	</div>	
  </div>
  <!-- /.container -->

  <!-- Footer -->
    <footer class="site-footer">
      <div class="container">
        <div class="row pt-5 mt-5 text-center">
          <div class="col-md-12">
            <div class="border-top pt-5">
              <p>
            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
            <i class="icon-heart text-danger" aria-hidden="true"></i>
            Copyright &copy;LeeYM <script>document.write(new Date().getFullYear());</script> All rights reserved
            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
            </p>
        
            </div>
          </div>
          
        </div>
      </div>
    </footer>

  <!-- Bootstrap core JavaScript -->
  <script src="${pageContext.request.contextPath }/resources/vendor-boot/jquery/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath }/resources/vendor-boot/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>

</html>
