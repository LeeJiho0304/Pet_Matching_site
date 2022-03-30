package controller.aftercare;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Aftercare;
import model.dao.AftercareDAO;

public class InsertAftercareController implements Controller {
	
	private AftercareDAO aftercareDAO = new AftercareDAO();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       
    	 Aftercare aftercare = new Aftercare(
			 null,
			 request.getParameter("pet_id"),
			 request.getParameter("ac_list_content"),
			 request.getParameter("ac_list_date"),
			 request.getParameter("ac_list_title"),
			 UserSessionUtils.getUserFromSession(request.getSession())
			 );
    		
		try {	    			
			aftercareDAO.insertAftercare(aftercare);    			
			
	        
		} catch (Exception e) {		
            request.setAttribute("exception", e);
		}
		
		return "/aftercare/list";	
		
    }
}