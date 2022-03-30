<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
      <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>My NoticeBoard</title>
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
                	<li >
                        <a href="<c:url value='/noticeboard/list'>
					      </c:url>" ><i class="fa fa-desktop "></i>전체 게시판 보기</a>
                    </li>
                    <li> 
                        <a href="<c:url value='/mine/mypage'>
					      </c:url>"><i class="fa fa-user "></i>마이 페이지 </a>
                    </li>
                    <li> 
                        <a href="<c:url value='/matching/myapplicationlist'>
					      </c:url>"><i class="fa fa-table "></i>내가 신청한 목록 보기 </a>
                    </li>
                    <li class="active-link">
                    	<form name = "form" method="POST" action = "<c:url value='/noticeboard/list' />">
                    		<i class="fa fa-edit "></i><input type="submit" value = "나의 게시글 보기" class="br"/>
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
                        <h2>나의 게시글 목록</h2>
                    </div>
                </div>

                <!-- /. ROW  -->
                <hr>
                <div class="row">
                    <div class="col-lg-6 col-md-6">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>제목</th>
                                    <th>작성일</th>
                                    <th>매칭 상태</th>
                                    <th>신청자 보러가기</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<% int i = 1; %>
                            	<c:forEach var="content" items="${content_list}">
                                <tr>
                                    <td><% out.print(i); i++; %></td>
                                    <td>
	                                    <a href="<c:url value='/noticeboard/content'>
						  					<c:param name='list_id' value='${content.list_id}'/>
				 		 					</c:url>">
				  							${content.list_title} </a></td>
                                    <td>${content.list_date}</td>
                                    <td>
                                    	<c:if test="${content.notice_state == 1}">
					      					<font color="blue"><c:out value="매칭 완료" /></font></c:if>
					      				<c:if test="${content.notice_state == 0}">
					     					<font color="black"><c:out value="매칭 대기중" /></font></c:if>
				     				</td>
                                    <td>
	                                    <a href="<c:url value='/matching/candidatelist'>
						   					<c:param name='list_id' value='${content.list_id}'/>
						   					<c:param name='user_id' value='${curUserId}'/>
					 		 				</c:url>" class="btn btn-primary">
					  						클릭</a>
			  						</td>
                                </tr>
                                </c:forEach> 
                            </tbody>
                        </table>
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