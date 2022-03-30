package controller.aftercare;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.dao.AftercareDAO;
import model.Aftercare;

public class UpdateAftercareController implements Controller{

	private AftercareDAO aftercareDAO = new AftercareDAO();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	String curUserId = UserSessionUtils.getUserFromSession(request.getSession());
    	
    	if (request.getMethod().equals("GET")) {   // GET request: 사후관리 게시글 수정 form 요청
    		String ac_list_id = request.getParameter("ac_list_id");
	        Aftercare aftercare = aftercareDAO.getContentAfterBoard(ac_list_id);
	
	        if (curUserId == null) {
	            // 작성자의 id가 login id와 다를 경우 사용자 보기 화면으로 오류 메세지를 전달
	            request.setAttribute("updateFailed", true);
	            request.setAttribute("exception", 
	                  new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다."));            
	            return "/aftercare/list";      // 사후관리 게시글 목록 화면으로 이동 (forwarding)
	         }
	    
	         request.setAttribute("aftercare", aftercare);   
	         request.setAttribute("pet_id", request.getParameter("pet_id"));
	         request.setAttribute("owner_id", request.getParameter("owner_id"));
	         request.setAttribute("app_id", request.getParameter("app_id"));
	         return "/aftercare/UpdateAftercareForm.jsp";  //검색한 게시글 정보를 사후관리게시글수정form으로 전송
       }
       
       // POST request (수정된 사후관리 게시글 정보가 parameter로 전송됨)
       Aftercare updateAftercare = new Aftercare(
    		  request.getParameter("ac_list_id"),
    		  request.getParameter("pet_id"),
    		  request.getParameter("ac_list_content"),
    		  request.getParameter("ac_list_date"),
              request.getParameter("ac_list_title"),
              request.getParameter("writer")
    		   );     
       aftercareDAO.updateAftercare(updateAftercare, curUserId);       
       return "/aftercare/list";
       
    }
    
}