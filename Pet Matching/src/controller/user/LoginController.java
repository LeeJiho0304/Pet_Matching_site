package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import model.Member;
import model.dao.MemberDAO;
import model.service.PasswordMismatchException;
import model.service.UserNotFoundException;

public class LoginController implements Controller {
	
	private MemberDAO memberDAO = new MemberDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		try {			
			// 직접 DAO를 호출하여 로그인 작업 수행 	
			Member member = memberDAO.findUser(userId);			
			if (member == null) {
				throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
			}			
			if (!member.matchPassword(password)) {
				throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
			}			
			
			// 세션에 사용자 이이디 저장
			HttpSession session = request.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
            
            return "redirect:/noticeboard/list";
            
		} catch (Exception e) {
			/* UserNotFoundException이나 PasswordMismatchException 발생 시
			 * 다시 login form을 사용자에게 전송하고 오류 메세지도 출력
			 */
            request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
            return "/user/loginForm.jsp";			
		}
		
    }
}
