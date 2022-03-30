package controller.matching;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.CompleteMatching;
import model.dao.MatchingDAO;

public class ListCompleteMatchingController implements Controller {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	if (!UserSessionUtils.isLogined(request.getSession())) {
            return "redirect:/user/login/form";		
        }
    	
    	String curUserId = UserSessionUtils.getUserFromSession(request.getSession());
    	MatchingDAO matchingDAO = new MatchingDAO();
    	List<CompleteMatching> matchingList = matchingDAO.getMyMatchingList(curUserId);
    	
		request.setAttribute("matchingList", matchingList);				
		request.setAttribute("curUserId", curUserId);		

		return "/matching/MyMatching_list.jsp";   
    }
    
}