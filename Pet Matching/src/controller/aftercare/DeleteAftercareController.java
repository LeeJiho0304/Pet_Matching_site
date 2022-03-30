package controller.aftercare;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.dao.AftercareDAO;

public class DeleteAftercareController implements Controller{

	private AftercareDAO aftercareDAO = new AftercareDAO();

    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	
		String ac_list_id = request.getParameter("ac_list_id");
		String writer = request.getParameter("writer");
		
		if(writer.equals(UserSessionUtils.getUserFromSession(request.getSession()))) {
			aftercareDAO.deleteAftercare(ac_list_id);
		}				
		return "/aftercare/list";
    }
    
}