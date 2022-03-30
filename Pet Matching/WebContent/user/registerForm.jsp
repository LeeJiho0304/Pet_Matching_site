<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Register_Form</title>
   	<!-- BOOTSTRAP STYLES-->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" />
    <!-- FONTAWESOME STYLES-->
    <link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet" />
    <!-- CUSTOM STYLES-->
    <link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet" />
    <!-- GOOGLE FONTS-->
   	<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
</head>

<script>
function submit() { 
	form.submit();
}
function goBack(targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>

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
                    <a class="navbar-brand" href="<c:url value='noticeboard/list'>
                     	</c:url>">
                        <img src="http://eclass.dongduk.ac.kr/ilosfiles/editor-file/1E5A53C8347A5850C9/201712/795852CE30795855C836785053CD.png" height="60" width="60"/>
                    </a>
                    <span class="logo-spn" >
                   		<a href="<c:url value='noticeboard/list' />">유기하지말아조</a>
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
                   <li class="active-link">
                        <a href="<c:url value='/user/register/form'>
                     </c:url>" ><i class="fa fa-plus "></i>회원 가입</a>
                    </li>
                </ul>
           </div>
       </nav>
   </div>
   
   
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper" >
          	<div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                    </div>
                </div>

                <!-- /. ROW  -->
                <hr>
                <div class="row">
                    <div class="col-lg-6 col-md-6">                  
                        <!-- registration form  -->
						<form name="form" method="POST" action="<c:url value='/user/register' />">						    
						       <table class="table table-striped table-bordered table-hover">
						       <thead>
                                   <tr>
                                       <th>ID</th>
                                       <th><input type="text" class="form-control" style="width:240" name="user_id"></th>
                                   </tr>
                               </thead>
						       <tbody>
						        	<tr>
                                       <td>이름</td>
                                       <td>
                                          <input type="text" class="form-control" style="width:240" name="user_name">
                                       </td>
                                   </tr>
                                   <tr>
                                       <td>비밀번호</td>
                                       <td>
                                          <input type="password" class="form-control" style="width:240" name="pwd">
                                       </td>
                                   </tr>
                                   <tr>
                                       <td>주민번호</td>
                                       <td>
                                          <input type="text" class="form-control" style="width:240" name="res_id">
                                       </td>
                                   </tr>
                                   <tr>
                                       <td>나이</td>
                                       <td>
                                          <input type="text" class="form-control" style="width:240" name="user_age">
                                       </td>
                                   </tr>
                                   <tr>
                                       <td>성별</td>
                                       <td>
                                          <input type="text" class="form-control" style="width:240" name="user_gender">
                                       </td>
                                   </tr>
                                   <tr>
                                       <td>가족구성원</td>
                                       <td>
                                          <input type="text" class="form-control" style="width:240" name="family_member">
                                       </td>
                                   </tr>
                                   <tr>
                                       <td>펫수</td>
                                       <td>
                                          <input type="text" class="form-control" style="width:240" name="pet_cnt">
                                       </td>
                                   </tr>
                                    <tr>
                                       <td>주소</td>
                                       <td>
                                          <input type="text" class="form-control" style="width:240" name="address">
                                       </td>
                                   </tr>
                                   <tr>
                                       <td>핸드폰 번호</td>
                                       <td>
                                          <input type="text" class="form-control" style="width:240" name="phone">
                                       </td>
                                   </tr>
                                   <tr>
                                       <td>직업</td>
                                       <td>
                                          <input type="text" class="form-control" style="width:240" name="job">
                                       </td>
                                   </tr>
                                   <tr>
                                       <td>자기소개</td>
                                       <td>
                                          <input type="text" class="form-control" style="width:240" name="profile">
                                       </td>
                                   </tr>
                                    <tr>
                                       <td colspan="2" align=center>
	                                       <input type="button" class="btn btn-primary" value="회원가입" onClick="submit()">
	                                       <input type="button" class="btn btn-default" value="뒤로가기" onClick="goBack('<c:url value='/user/login/form' />')"> &nbsp;
                                       </td>
                                   </tr>
                               </tbody>
						    </table>
						</form>
                    </div>
                  </div>
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