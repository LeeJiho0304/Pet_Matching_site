package controller.aftercare;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import controller.user.UserSessionUtils;
import model.Aftercare;
import model.dao.AftercareDAO;

public class ListAftercareController implements Controller {
	
	// private static final int countPerPage = 100;	// 한 화면에 출력할 사용자 수
	private AftercareDAO aftercareDAO = new AftercareDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		// 로그인 여부 확인
    	if (!UserSessionUtils.isLogined(request.getSession())) {
            return "redirect:/user/login/form";		// login form 요청으로 redirect
        }

    	String owner_id = request.getParameter("owner_id");
    	String app_id = request.getParameter("app_id");
    	String pet_id = request.getParameter("pet_id");
    	
    	
    	List<Aftercare> aftercare_list = aftercareDAO.getAllAfterCareBoardList(pet_id);
		request.setAttribute("aftercare_list", aftercare_list);
		
		request.setAttribute("owner_id", owner_id);
		request.setAttribute("app_id", app_id);
		request.setAttribute("pet_id", pet_id);
		
		request.setAttribute("curUserId", UserSessionUtils.getUserFromSession(request.getSession()));		

		// 사후관리게시판 리스트 화면으로 이동(forwarding)
		return "/aftercare/AllAftercareNoticeBoard_list.jsp";        
    }
}
