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
			// ���� DAO�� ȣ���Ͽ� �α��� �۾� ���� 	
			Member member = memberDAO.findUser(userId);			
			if (member == null) {
				throw new UserNotFoundException(userId + "�� �������� �ʴ� ���̵��Դϴ�.");
			}			
			if (!member.matchPassword(password)) {
				throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			}			
			
			// ���ǿ� ����� ���̵� ����
			HttpSession session = request.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
            
            return "redirect:/noticeboard/list";
            
		} catch (Exception e) {
			/* UserNotFoundException�̳� PasswordMismatchException �߻� ��
			 * �ٽ� login form�� ����ڿ��� �����ϰ� ���� �޼����� ���
			 */
            request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
            return "/user/loginForm.jsp";			
		}
		
    }
}
