package controller.noticeboard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import controller.user.UserSessionUtils;
import model.Content;
import model.dao.NoticeBoardDAO;

public class ListNoticeBoardController implements Controller{
	
	private NoticeBoardDAO noticeboardDAO = new NoticeBoardDAO();
	
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		
		// �α��� ���� Ȯ��
    	if (!UserSessionUtils.isLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form ��û���� redirect
        }
		
		if (request.getMethod().equals("GET")) {	// GET request: ��ü �Խñ� ����
			List<Content> content_list = noticeboardDAO.getAllNoticeBoardList();
			request.setAttribute("content_list", content_list);
			request.setAttribute("curUserId", UserSessionUtils.getUserFromSession(request.getSession()));
			return "/noticeboard/AllNoticeBoard_list.jsp";
		}
		
		//POST ���� �ۼ��� �Խñ� ��� �ҷ�����
		List<Content> content_list = noticeboardDAO.getMyNoticeBoardList(request.getParameter("user_id"));
		request.setAttribute("content_list", content_list);
		request.setAttribute("curUserId", UserSessionUtils.getUserFromSession(request.getSession()));
		return "/noticeboard/MyNoticeBoard_list.jsp";
		
	}

}