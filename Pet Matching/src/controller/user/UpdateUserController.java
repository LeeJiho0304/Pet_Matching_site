package controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Member;
import model.dao.MemberDAO;

public class UpdateUserController implements Controller {
	
    private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);
    private MemberDAO memberDAO = new MemberDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       
       if (request.getMethod().equals("GET")) {   // GET request: ȸ������ ���� form ��û
    	   String curUserId = UserSessionUtils.getUserFromSession(request.getSession());
    	   Member member = memberDAO.findUser(curUserId);
         
    	   if (member == null)	// user not found!
    		   return "redirect:/user/login/form";

    	   request.setAttribute("member", member);   // ����� ������ request ��ü�� ���� 
    	   return "/user/updateForm.jsp";      // �˻��� ����� ������ ȸ���������� form���� ���� 
       }
       
       // POST request (ȸ�������� parameter�� ���۵�)
       request.setCharacterEncoding("UTF-8");
       Member updateUser = new Member(
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
             request.getParameter("profile")
       );
       log.debug("Update User : {}", updateUser);

       memberDAO.update(updateUser, request.getParameter("userId"));       
        return "redirect:/mine/mypage";      
    }
}
