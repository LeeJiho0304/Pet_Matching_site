<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
      <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>My Matching List</title>
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
function submit(){
    form.submit();
}
function goBack(targetUri) {
	location.href=targetUri;
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
                    <a class="navbar-brand" href="<c:url value='/noticeboard/list'></c:url>">
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
					      </c:url>"><i class="fa fa-user "></i>마이 페이지<hr><i class="fa fa-heart " aria-hidden="true"></i>나의 매칭 리스트 </a>
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
                <div class="row">
                    <div class="col-md-12">
                        <h2>나의 매칭 목록</h2>
                    </div>
                </div>
                <!-- /. ROW  -->
                <hr />
                   
                <div class="col-lg-6 col-md-6">
                    <br>
                    <div class="col-lg-6 col-md-6">
                    <font color="blue"></font>
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>주인 ID</th>
                                    <th>신청자 ID</th>
                                    <th>펫</th>
                                    <th>사후관리</th>
                                </tr>
                            </thead>
                            <tbody>
	                            <% int i=1; %>
	                            <c:forEach var="comMatching" items="${matchingList}">
                            	<tr>
                            		<td><%out.print(i); i++; %></td>
                                    <td>${comMatching.owner_id}</td>
                                    <td>${comMatching.app_id}</td>
                                    <td>${comMatching.pet_name}</td>
                                    <td><a href="<c:url value='/aftercare/list'>
                                    		<c:param name='owner_id' value='${comMatching.owner_id}'/>
								            <c:param name='app_id' value='${comMatching.app_id}'/>
								            <c:param name='pet_id' value='${comMatching.pet_id}'/>
									      </c:url>"  class="btn btn-primary">입장</a>
									</td>
                                </tr>
                            	</c:forEach>
                            </tbody>
                        </table>
                        <input type="button" class="btn btn-default" value="뒤로가기" onClick="goBack('<c:url value='/mine/mypage' />')">
						<!-- exception 객체가 넘어온 경우 오류메시지 출력 -->
						<c:if test="${not empty exception}">
						  <font color="red"><c:out value="${exception.getMessage()}" /></font>
						</c:if>
                    </div>
                </div>
			</div>
         </div>
         
         
             
        <!-- /. PAGE WRAPPER  -->
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
