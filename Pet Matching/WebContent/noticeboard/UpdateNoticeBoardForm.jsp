<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>NoticeBoard Update Form</title>
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
                    <a class="navbar-brand" href="<c:url value='noticeboard/list'>
                     	</c:url>">
                        <img src="http://eclass.dongduk.ac.kr/ilosfiles/editor-file/1E5A53C8347A5850C9/201712/795852CE30795855C836785053CD.png" height="60" width="60"/>
                    </a>
                    <span class="logo-spn" >
                   		<a href="<c:url value='noticeboard/list' />">��������������</a>
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
                        <a href="<c:url value='/noticeboard/list'>
                     	</c:url>" ><i class="fa fa-desktop "></i>��ü �Խ��� ����<hr><i class="fa fa-plus " aria-hidden="true"></i>�Խñ� ���� </a>
                    </li>
                    <li>
                        <a href="<c:url value='/mine/mypage'>
                     	</c:url>"><i class="fa fa-user "></i>���� ������ </a>
                    </li>
                    <li> 
                        <a href="<c:url value='/matching/myapplicationlist'>
                     	</c:url>"><i class="fa fa-table "></i>���� ��û�� ��� ���� </a>
                    </li>
                    <li>
                       	<form name = "form" method="POST" action = "<c:url value='/noticeboard/list' />">
                        	<i class="fa fa-edit "></i><input type="submit" value = "���� �Խñ� ����" class="ar"/>
                     		<input type = "hidden" name = "user_id" value = "${curUserId}"/>
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
                        <h2>�Խñ� ����</h2>
                    </div>
                </div>
                <!-- /. ROW  -->
                <hr>
               	<div class="row">
                   <div class="col-lg-6 col-md-6">
                      <form name="form" method="POST" action="<c:url value='/noticeboard/update' />">
                      	<input type="hidden" name="list_id" value="${content.list_id }"/>
               
                        <table class="table table-striped table-bordered table-hover">
                             <tbody>
                                 <tr><td colspan="2">�� ����</td></tr>
                                 <tr>
                                   <td>����</td>
                                   <td><input type="text" class="form-control" style="width:240" name="list_title" value="${content.list_title}"></td>
                                </tr>
                                 <tr>
                                   <td>�ۼ���</td>
                                   <td><input type="text" class="form-control" style="width:240" name="list_date" value="${content.list_date}"></td>
                                </tr>
                                <tr>
                                   <td>�� ����</td>
                                   <td><select name="pet_id">
		                                   <option value="${content.pet_id}" selected>${content.pet_name}</option>
		                                   <c:forEach var="pet" items="${pet_list}">
		                                   <c:if test="${content.pet_id != pet.pet_id}">
		                                   <option value="${pet.pet_id}">${pet.pet_name}</option>
		                                   </c:if>
		                                   </c:forEach>
										</select>
									</td>
                                </tr>
                  
                                <tr><td colspan="2">�о� ���� �� ��Ī ����</td></tr>
                                <tr>
                                   <td colspan="2">
                                      <p><textarea cols="240" rows="20" class="form-control" style="width:240" name="list_content">${content.list_content}</textarea></p>
                                   </td>
                                </tr>
                                <tr>
                                   <td colspan="2" align=center>
	                                   <input type="button" class="btn btn-primary" value="����" onClick="submit()">
	                                   <input type="button" class="btn btn-default" value="�ڷΰ���" onClick="goBack('<c:url value='/noticeboard/content'>
	                               			<c:param name='list_id' value='${content.list_id}'/>
									      	</c:url>')"> &nbsp;
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