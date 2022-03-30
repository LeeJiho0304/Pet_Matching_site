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
		// �α��� ���� Ȯ��
    	if (!UserSessionUtils.isLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form ��û���� redirect
        }
    	
    	String list_id = request.getParameter("list_id");
    	String user_id = request.getParameter("user_id");
		Candidate candidate = matchingDAO.getCandidateList(user_id, list_id);
		List<Member> user_list = candidate.getUser_list();
		// candidate ��ü�� ���� �α����� ����� ID�� request�� �����Ͽ� ����
		request.setAttribute("user_list", user_list);
		request.setAttribute("candidate", candidate);
		request.setAttribute("curUserId", UserSessionUtils.getUserFromSession(request.getSession()));		
	
		// �ĺ��� ����Ʈ ȭ������ �̵�(forwarding)
		return "/matching/Candidate_list.jsp";
	}
	
}