package controller.matching;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.MatNotice;
import model.dao.MatchingDAO;

public class ListApplicationController implements Controller {
	
	private MatchingDAO matchingDAO = new MatchingDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<MatNotice> apcList = matchingDAO.getMyApplicationList(UserSessionUtils.getUserFromSession(request.getSession()));
		
		// apcList 객체와 현재 로그인한 사용자 ID를 request에 저장하여 전달
		request.setAttribute("apcList", apcList);
		request.setAttribute("curUserId", UserSessionUtils.getUserFromSession(request.getSession()));		

		// 나의 신청 리스트 화면으로 이동(forwarding)
		return "/matching/MyApplication_list.jsp";
    }
    
}