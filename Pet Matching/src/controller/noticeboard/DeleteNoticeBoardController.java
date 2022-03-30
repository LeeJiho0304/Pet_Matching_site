package controller.noticeboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.dao.NoticeBoardDAO;

public class DeleteNoticeBoardController implements Controller {
	
	private NoticeBoardDAO noticeboardDAO = new NoticeBoardDAO();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	String list_id = request.getParameter("list_id");
    	noticeboardDAO.deleteNoticeboard(list_id, UserSessionUtils.getUserFromSession(request.getSession()));
    	return "redirect:/noticeboard/list";
    	
    }
}