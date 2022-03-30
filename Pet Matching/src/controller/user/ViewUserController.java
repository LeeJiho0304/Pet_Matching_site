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
    	// �α��� ���� Ȯ��
    	if (!UserSessionUtils.isLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form ��û���� redirect
        }
    	
		String userId = request.getParameter("userId");
		String list_id = request.getParameter("list_id");
		
		try {
			Member member = memberDAO.findUser(userId);			
			if (member == null) {
				 throw new UserNotFoundException(userId + "�� �������� �ʴ� ���̵��Դϴ�.");
			}			
			request.setAttribute("member", member);		// ����� ���� ����	
			request.setAttribute("list_id", list_id);
			return "/user/view.jsp";				// ����� ���� ȭ������ �̵�
			
		} catch (Exception e) {			
			request.setAttribute("exception", e);			
	        return "redirect:/noticeboard/list";					
		}
		
	}
	
}
