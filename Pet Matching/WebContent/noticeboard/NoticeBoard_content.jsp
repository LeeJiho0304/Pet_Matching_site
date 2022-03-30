<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
      <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>NoticeBoard Content</title>
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
function noapply(notice_state) {
    if (notice_state == 1) {
       alert("�̹� ��Ī �Ϸ�� ���Դϴ�. ��û�� �� �����ϴ�.");
       return false;
    }
    return true;
}

function callupdate(curUserId, content_user_id) {
    if (curUserId != content_user_id) {
       alert("������ �ۼ��� �۸� ������ �� �ֽ��ϴ�.");
       return false;
    }
    return true;
}

function calldelete(curUserId, content_user_id) {
    if (curUserId != content_user_id) {
       alert("������ �ۼ��� �۸� ������ �� �ֽ��ϴ�.");
       return false;
    }
    return confirm("���� �����Ͻðڽ��ϱ�?");
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
                    <a class="navbar-brand" href="<c:url value='/noticeboard/list'>
                     	</c:url>">
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
                   <li class="active-link">
                        <a href="<c:url value='/noticeboard/list'>
                     		</c:url>" ><i class="fa fa-desktop "></i>��ü �Խ��� ����<hr><i class="fa fa-clipboard " aria-hidden="true"></i>�Խñ� ��</a>
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
                        <h2>�Խñ� ���� : ${content.list_title}</h2>
                    </div>
                </div>
                <!-- /. ROW  -->
                <hr/>
              
               	<c:if test="${content.notice_state == 1}">
                	<font color="red"><c:out value="�� �Խñ��� �̹�  matching �Ϸ�� ���Դϴ�." /></font>
            	</c:if>
                <h4>�ۼ��� : ${content.user_id}</h4>
                <h4>�ۼ��� : ${content.list_date}</h4>
        
                <!-- /. ROW  -->
                <hr>
                <div class="row">
                    <div class="col-lg-6 col-md-6">
                        <h4>�� ����</h4>
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>����</th>
                                    <th>ǰ��</th>
                                    <th>����</th>
                                    <th>����</th>
                                    <th>��������</th>
                                    <th>����</th>
                                    <th>�̸�</th>
                                    <th>��Ź�Ⱓ</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>${content.pet_kinds}</td>
                                    <td>${content.pet_race}</td>
                                    <td>${content.pet_age}</td>
                                    <td>${content.pet_gender}</td>
                                    <td>${content.pet_vaccine}</td>
                                    <td>${content.pet_price}</td>
                                    <td>${content.pet_name}</td>
                                    <td>${content.entrust_term}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                    
                <!-- /. ROW  -->
                <hr>
                <div class="row">
                    <div class="col-lg-4 col-md-4">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                   	�о� ���� �� ��Ī ����
                            </div>
                            <div class="panel-body">
                                <p>${content.list_content}</p>
                            </div>
                            <div class="panel-footer">
                                   	�ڼ��� ���� ������ <a href="<c:url value='/user/view'>
                                    <c:param name='userId' value='${content.user_id}'/>
                                    <c:param name='list_id' value='${content.list_id}'/>
                     				</c:url>">${content.user_id}</a>�� ���� �ٶ��ϴ�.
                            </div>
                        </div>
                    </div>
                 </div>
                
                <!-- /. ROW  -->
                <hr />
                <div class="row">
                    <div class="col-lg-6 col-md-6">
                    	<a href="<c:url value='/noticeboard/update/form'>
	                            <c:param name='list_id' value='${content.list_id}'/>
	                        	</c:url>" class="btn btn-default" 
	                            onclick="return callupdate('${curUserId}','${content.user_id}');">����</a>
                        <a href="<c:url value='/noticeboard/delete'>
                                <c:param name='list_id' value='${content.list_id}'/>
                        		</c:url>" class="btn btn-danger" 
                              	onclick="return calldelete('${curUserId}','${content.user_id}');">����</a>
                        <a href="<c:url value='/matching/insert'>
	                            <c:param name='user_id' value='${curUserId}'/>
	                            <c:param name='pet_id' value='${content.pet_id}'/>
	                            <c:param name='list_id' value='${content.list_id}'/>
	                     		</c:url>"  class="btn btn-primary" onclick="return noapply('${content.notice_state}');">��û</a>
                        <a href="<c:url value='/noticeboard/list'>
                     			</c:url>" class="btn btn-success">���</a>
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