package controller.noticeboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.NoticeBoard;
import model.dao.NoticeBoardDAO;

public class InsertNoticeBoardController implements Controller{
	
	private NoticeBoardDAO noticeboardDAO = new NoticeBoardDAO();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	NoticeBoard noticeboard = new NoticeBoard(
    			null,
    			request.getParameter("pet_id"),
    			request.getParameter("list_title"),
    			request.getParameter("list_content"),
    			request.getParameter("list_date"),
    			0,
				null,
				0,
				null);
    	
    	try {
			noticeboardDAO.insertNoticeBoard(noticeboard);
			return "redirect:/noticeboard/list";
			
		} catch (Exception e) {
            request.setAttribute("exception", e);
			return "redirect:/noticeboard/list";
		}
    	
    }
    
}