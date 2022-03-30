package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.UserNotFoundException;
import model.Member;
import model.dao.MemberDAO;

public class ViewUserController implements Controller {
	
	private MemberDAO memberDAO = new MemberDAO();

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	// 로그인 여부 확인
    	if (!UserSessionUtils.isLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }
    	
		String userId = request.getParameter("userId");
		String list_id = request.getParameter("list_id");
		
		try {
			Member member = memberDAO.findUser(userId);			
			if (member == null) {
				 throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
			}			
			request.setAttribute("member", member);		// 사용자 정보 저장	
			request.setAttribute("list_id", list_id);
			return "/user/view.jsp";				// 사용자 보기 화면으로 이동
			
		} catch (Exception e) {			
			request.setAttribute("exception", e);			
	        return "redirect:/noticeboard/list";					
		}
		
	}
	
}
