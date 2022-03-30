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
       
       if (request.getMethod().equals("GET")) {   // GET request: 회원정보 수정 form 요청
    	   String curUserId = UserSessionUtils.getUserFromSession(request.getSession());
    	   Member member = memberDAO.findUser(curUserId);
         
    	   if (member == null)	// user not found!
    		   return "redirect:/user/login/form";

    	   request.setAttribute("member", member);   // 사용자 정보를 request 객체에 저장 
    	   return "/user/updateForm.jsp";      // 검색한 사용자 정보를 회원정보수정 form으로 전송 
       }
       
       // POST request (회원정보가 parameter로 전송됨)
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
