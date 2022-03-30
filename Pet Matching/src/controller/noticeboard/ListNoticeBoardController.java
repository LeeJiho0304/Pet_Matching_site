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
		
		// 로그인 여부 확인
    	if (!UserSessionUtils.isLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }
		
		if (request.getMethod().equals("GET")) {	// GET request: 전체 게시글 보기
			List<Content> content_list = noticeboardDAO.getAllNoticeBoardList();
			request.setAttribute("content_list", content_list);
			request.setAttribute("curUserId", UserSessionUtils.getUserFromSession(request.getSession()));
			return "/noticeboard/AllNoticeBoard_list.jsp";
		}
		
		//POST 내가 작성한 게시글 목록 불러오기
		List<Content> content_list = noticeboardDAO.getMyNoticeBoardList(request.getParameter("user_id"));
		request.setAttribute("content_list", content_list);
		request.setAttribute("curUserId", UserSessionUtils.getUserFromSession(request.getSession()));
		return "/noticeboard/MyNoticeBoard_list.jsp";
		
	}

}