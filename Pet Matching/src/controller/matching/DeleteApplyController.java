package controller.matching;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.dao.MatchingDAO;

public class DeleteApplyController implements Controller {
	
	private MatchingDAO matchingDAO = new MatchingDAO();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	String list_id = request.getParameter("list_id");
    	matchingDAO.deleteApplyList(list_id, UserSessionUtils.getUserFromSession(request.getSession()));
    	return "redirect:/matching/myapplicationlist";
    	
    }
}
