<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login</title>
   	<!-- BOOTSTRAP STYLES-->
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" />
    <!-- FONTAWESOME STYLES-->
    <link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet" />
    <!-- CUSTOM STYLES-->
    <link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet" />
    <!-- GOOGLE FONTS-->
   	<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
   	
<script>
function login() {
   if (form.userId.value == "") {
      alert("사용자 ID를 입력하십시오.");
      form.userId.focus();
      return false;
   } 
   if (form.password.value == "") {
      alert("비밀번호를 입력하십시오.");
      form.password.focus();
      return false;
   }      
   form.submit();
}

function userCreate(targetUri) {
   form.action = targetUri;
   form.submit();
}
</script>

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
                        <a href="<c:url value='/user/login'>
                     </c:url>" ><i class="fa fa-sign-in "></i>로그인</a>
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
                       	<!-- login form  -->
                  		<form name="form" method="POST" action="<c:url value='/user/login' />">
                           <table class="table table-striped table-bordered table-hover">
                               <thead>
                                   <tr>
                                       <th>ID</th>
                                       <th><input type="text" class="form-control" style="width:240" name="userId"></th>
                                   </tr>
                               </thead>
                               <tbody>
                                   <tr>
                                       	<td>PASSWORD</td>
                                       	<td>
                                          <input type="password" class="form-control" style="width:240" name="password">
                                       	</td>
                                   </tr>
                                   <tr>
                                       	<td colspan="2" align=center>
                                       		<input type="button" class="btn btn-primary" value="로그인" onClick="login()"> &nbsp;
                              				<input type="button" class="btn btn-default" value="회원가입" onClick="userCreate('<c:url value='/user/register/form' />')">
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