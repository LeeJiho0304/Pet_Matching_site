package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Member;
import model.dao.MemberDAO;
import model.service.ExistingUserException;

public class RegisterUserController implements Controller {
	
	private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);
	private MemberDAO memberDAO = new MemberDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	Member member = new Member(
    		   request.getParameter("user_id"),
    		   request.getParameter("user_name"),
    		   request.getParameter("pwd"),
    		   request.getParameter("res_id"),
    		   Integer.parseInt(request.getParameter("user_age")),
    		   request.getParameter("user_gender"),
    		   request.getParameter("family_member"),
    		   Integer.parseInt(request.getParameter("pet_cnt")),
    		   request.getParameter("address"),
    		   request.getParameter("phone"),
    		   request.getParameter("job"),
    		   request.getParameter("profile"));
    	
    	log.debug("Create User : {}", member);
    	try {
    		if (memberDAO.existingUser(member.getUserId()) == true) {
    			throw new ExistingUserException(member.getUserId() + "�� �����ϴ� ���̵��Դϴ�.");
    		}
    		memberDAO.create(member);
    		return "redirect:/user/login/form";      // ���� �� �α��� ȭ������ redirect
         
    	} catch (ExistingUserException e) {      // ���� �߻� �� ȸ������ form���� forwarding
    		request.setAttribute("registerFailed", true);
    		request.setAttribute("exception", e);
    		request.setAttribute("member", member);
    		return "/user/registerForm.jsp";
	   }
       
    }
}
