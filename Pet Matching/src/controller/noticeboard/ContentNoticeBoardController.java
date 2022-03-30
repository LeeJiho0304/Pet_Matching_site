package controller.noticeboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import controller.user.UserSessionUtils;
import model.Content;
import model.dao.NoticeBoardDAO;

public class ContentNoticeBoardController implements Controller{
	
	private NoticeBoardDAO noticeboardDAO = new NoticeBoardDAO();
	
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		
		//게시글 상세내용 불러오기
		String list_id = request.getParameter("list_id");
		Content content = noticeboardDAO.getContentNoticeBoard(list_id);
		request.setAttribute("content", content);
		request.setAttribute("curUserId", UserSessionUtils.getUserFromSession(request.getSession()));
		return "/noticeboard/NoticeBoard_content.jsp";
		
	}

}