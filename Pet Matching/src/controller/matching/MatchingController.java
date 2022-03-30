package controller.matching;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Matching;
import model.dao.MatchingDAO;

public class MatchingController implements Controller {

	private static final Logger log = LoggerFactory.getLogger(ListApplicationController.class);
	private MatchingDAO matchingDAO = new MatchingDAO();
	
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String curUserId = UserSessionUtils.getUserFromSession(request.getSession());
		if (curUserId == null) {
			request.setAttribute("exception", new IllegalStateException("�α��� �� �̿� �����մϴ�."));            
			return "redirect:/user/login/form";
		}
		
		if (request.getMethod().equals("GET")) {//��û�ڰ� ��û�Ҷ�
			
			Matching insert_mat = new Matching(					
					request.getParameter("pet_id"),
						null, 
					request.getParameter("user_id"),
					0, 
					request.getParameter("list_id")					
			);
		    log.debug("Create Matching : {}", insert_mat);
			matchingDAO.insertMaching(insert_mat);
			
			return "redirect:/matching/myapplicationlist";	//���� �� '���� ��û�� ��� ����'ȭ������ redirect
		}
		//POST ������ ��û�ڸ� �����Ͽ� ��Ī�� �ɶ�
		String applicant = request.getParameter("applicant");
		String list_id = request.getParameter("list_id");
	    log.debug("Create Matching : {}", list_id);
	    matchingDAO.UpdateMatching(applicant, list_id);
	    
	    return "/matching/mymatching";	//���� �� '���� ��Ī ��� ����'ȭ������ redirect
		
	}
}
