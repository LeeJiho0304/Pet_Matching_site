<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
      <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>My Page</title>
   <!-- BOOTSTRAP STYLES-->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" />
     <!-- FONTAWESOME STYLES-->
    <link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet" />
        <!-- CUSTOM STYLES-->
    <link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet" />
     <!-- GOOGLE FONTS-->
   <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
</head>
<body>
    <div id="wrapper">
         <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="adjust-nav">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="<c:url value='/noticeboard/list'>
					      </c:url>">
                        <img src="http://eclass.dongduk.ac.kr/ilosfiles/editor-file/1E5A53C8347A5850C9/201712/795852CE30795855C836785053CD.png" height="60" width="60"/>
                    </a>
                    <span class="logo-spn" >
                   <a href="<c:url value='/noticeboard/list' />">유기하지말아조</a>
                    </span>
                </div>
              
                 <span class="logout-spn" >
                  <a href="<c:url value='/user/logout' />">LOGOUT</a>
                 </span>
            </div>
        </div>
        
        
        <!-- /. NAV TOP  -->
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
                	<li>
                        <a href="<c:url value='/noticeboard/list'>
					      </c:url>" ><i class="fa fa-desktop "></i>전체 게시판 보기</a>
                    </li>
                    <li class="active-link">
                        <a href="<c:url value='/mine/mypage'>
					      </c:url>"><i class="fa fa-user "></i>마이 페이지 </a>
                    </li>
                    <li> 
                        <a href="<c:url value='/matching/myapplicationlist'>
					      </c:url>"><i class="fa fa-table "></i>내가 신청한 목록 보기 </a>
                    </li>
                    <li>
                    	<form name = "form" method="POST" action = "<c:url value='/noticeboard/list' />">
                    		<i class="fa fa-edit "></i><input type="submit" value = "나의 게시글 보기" class="ar"/>
							<input type = "hidden" name = "user_id" value = "${curUserId}"/>
						</form>
                    </li>
                    <li>
                    	 <a href="<c:url value='/noticeboard/recommended'>
					      </c:url>"><i class="fa fa-star "></i>추천 게시글 </a>
                    </li>
                </ul>
             </div>
          </nav>
      </div>

        
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper" >
          <div id="page-inner">
				<!-- /. ROW  --> 
                  <div class="row text-center pad-top">
	                  <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
	                      <div class="div-square">
	                           <a href="<c:url value='/user/update'>
	                     		  </c:url>">
			                      <i class="fa fa-user fa-5x" aria-hidden="true"></i>
			                      <h4><br>내 정보 수정</h4></a>
	                      </div>
	                  </div>
	                  
	                 <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
	                      <div class="div-square">
	                           <a href="<c:url value='/mine/petlist'>
			                      </c:url>">
			                      <i class="fa fa-paw fa-5x" aria-hidden="true"></i>
			                      <h4><br>내 펫 관리</h4></a>
	                      </div>
	                  </div>
	                  
	            	  <div class="col-lg-2 col-md-2 col-sm-2 col-xs-6">
	                      <div class="div-square">
	                        	<a href="<c:url value='/matching/mymatching'>
		                           </c:url>">
		                           <i class="fa fa-heart fa-5x"></i>
			                       <h4>나의 매칭 <br>리스트</h4></a>
	                      </div>
	                  </div>
                <!-- /. ROW  --> 
   				</div>
            <!-- /. PAGE INNER  -->
            </div>
        <!-- /. PAGE WRAPPER  -->
        </div>
				
    	<div class="footer">
             <div class="row">
                <div class="col-lg-12" >
                     &copy;  2017 DataBase Programming Team Project |  by: 이아륜 이신애 이지호 위혜진
                </div>
        	</div>
        </div>
          

   <!-- /. WRAPPER  -->
    <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
    <!-- JQUERY SCRIPTS -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
    <!-- BOOTSTRAP SCRIPTS -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <!-- CUSTOM SCRIPTS -->
    <script src="${pageContext.request.contextPath}/js/custom.js"></script>
    
   
</body>
</html>