<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
      <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Candidate List</title>
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
                    <a class="navbar-brand" href="<c:url value='/noticeboard/list'></c:url>">
                        <img src="http://eclass.dongduk.ac.kr/ilosfiles/editor-file/1E5A53C8347A5850C9/201712/795852CE30795855C836785053CD.png" height="60" width="60"/>
                    </a>
                    <span class="logo-spn" >
                   <a href="<c:url value='/noticeboard/list' />">��������������</a>
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
                     			</c:url>" ><i class="fa fa-desktop "></i>��ü �Խ��� ����</a>
                    </li>
                    <li>
                        <a href="<c:url value='/mine/mypage'>
                     	</c:url>"><i class="fa fa-user "></i>���� ������ </a>
                    </li>
                    <li> 
                        <a href="<c:url value='/matching/myapplicationlist'>
                     			</c:url>"><i class="fa fa-table "></i>���� ��û�� ��� ���� </a>
                    </li>
                    <li  class="active-link">
                       <form name = "form" method="POST" action = "<c:url value='/noticeboard/list' />">
                          	<i class="fa fa-edit "></i><input type="submit" value = "���� �Խñ� ����" class="br"/>
                     		<input type = "hidden" name = "user_id" value = "${curUserId}"/>
                     		<hr><i class="fa fa-users " aria-hidden="true"></i>�ĺ��� ��� ����
                  		</form>
                    </li>
                    <li>
                    	 <a href="<c:url value='/noticeboard/recommended'>
					      </c:url>"><i class="fa fa-star "></i>��õ �Խñ� </a>
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
                        <h2>�ĺ��� ���</h2>
                    </div>
                </div>
                <!-- /. ROW  -->
                <hr />
                   
                <div class="col-lg-6 col-md-6">
                    <br>
                    <div class="col-lg-6 col-md-6">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>�ĺ��� ID</th>
                                    <th>��Ī�ϱ�</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<% int i = 1; %>
                            	<c:forEach var="user" items="${user_list}">
                            	<tr>
                            		<td><% out.print(i); i++; %></td>
									<td>
                                    	<a href="<c:url value='/user/view'>
					   							 <c:param name='userId' value='${user.userId}'/>
			 		 							 </c:url>">${user.userId}</a>
			  						</td>
                                    <td>
                                    	<form name = "form" method="POST" action = "<c:url value='/matching/update'/>">
											<input type = "hidden" name = "list_id" value = "${candidate.list_id}"/>
											<input type = "hidden" name = "applicant" value = "${user.userId}"/>
											<input type="submit" value = "Ŭ��" class="btn btn-primary"/>
										</form>
							  		</td>
                                </tr>
                            	</c:forEach>
                            </tbody>
                        </table>
                        <form name = "form" method="POST" action = "<c:url value='/noticeboard/list' />">
                    		<input type="submit" value = "�ڷΰ���" class='btn btn-primary'/>
							<input type = "hidden" name = "user_id" value = "${curUserId}"/>
						</form>

						<br>
						<!-- exception ��ü�� �Ѿ�� ��� �����޽��� ��� -->
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
                    &copy;  2017 DataBase Programming Team Project |  by: �̾Ʒ� �̽ž� ����ȣ ������
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