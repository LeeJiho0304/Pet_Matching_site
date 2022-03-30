package controller.aftercare;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Aftercare;
import model.dao.AftercareDAO;

public class ContentAftercareController implements Controller{
private AftercareDAO aftercareDAO = new AftercareDAO();
	
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		
		//게시글 상세내용 불러오기
		String ac_list_id = request.getParameter("ac_list_id");
		Aftercare aftercare = aftercareDAO.getContentAfterBoard(ac_list_id);
		request.setAttribute("aftercare", aftercare);
		request.setAttribute("owner_id", request.getParameter("owner_id"));
		request.setAttribute("app_id", request.getParameter("app_id"));
		request.setAttribute("curUserId", 
				UserSessionUtils.getUserFromSession(request.getSession()));
		return "/aftercare/AftercareContent.jsp";
		
	}
}
