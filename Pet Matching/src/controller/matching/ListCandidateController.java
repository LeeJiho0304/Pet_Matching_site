package controller.matching;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import controller.user.UserSessionUtils;
import model.Candidate;
import model.Member;
import model.dao.MatchingDAO;

public class ListCandidateController implements Controller {
	
	private MatchingDAO matchingDAO = new MatchingDAO();
	
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		// 로그인 여부 확인
    	if (!UserSessionUtils.isLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }
    	
    	String list_id = request.getParameter("list_id");
    	String user_id = request.getParameter("user_id");
		Candidate candidate = matchingDAO.getCandidateList(user_id, list_id);
		List<Member> user_list = candidate.getUser_list();
		// candidate 객체와 현재 로그인한 사용자 ID를 request에 저장하여 전달
		request.setAttribute("user_list", user_list);
		request.setAttribute("candidate", candidate);
		request.setAttribute("curUserId", UserSessionUtils.getUserFromSession(request.getSession()));		
	
		// 후보자 리스트 화면으로 이동(forwarding)
		return "/matching/Candidate_list.jsp";
	}
	
}