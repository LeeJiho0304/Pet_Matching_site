package controller.noticeboard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.dao.NoticeBoardDAO;
import model.dao.PetDAO;
import model.Content;
import model.Pet;

public class UpdateNoticeBoardController implements Controller{

	private NoticeBoardDAO noticeboardDAO = new NoticeBoardDAO();
	private PetDAO petDAO = new PetDAO();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	String curUserId = UserSessionUtils.getUserFromSession(request.getSession());
    	
    	if (request.getMethod().equals("GET")) {   // GET request: 게시글 정보 수정 form 요청
    		String list_id = request.getParameter("list_id");
    		Content noticeboard = noticeboardDAO.getContentNoticeBoard(list_id);
	        List<Pet> pet_list = petDAO.getAllMyPetList(curUserId);

	        if (curUserId == null) {
	        	// 삭제하려는 id와 login id가 다를 경우 사용자 보기 화면으로 오류 메세지를 전달
	            request.setAttribute("updateFailed", true);
	            request.setAttribute("exception", new IllegalStateException("다른 사용자가 작성한 게시글의 정보를 수정할 수 없습니다."));            
	            return "redirect:/noticeboard/list";      // 사용자 보기 화면으로 이동 (forwarding)
	        }
	        
	        request.setAttribute("pet_list", pet_list);
	        request.setAttribute("content", noticeboard);         // 펫 정보와 게시글 정보를 request 객체에 저장                     
	        return "/noticeboard/UpdateNoticeBoardForm.jsp";      // 검색한 게시글 정보를 수정하는 form으로 전송     
	    }
    	
    	// POST request (수정된 게시글 정보가 parameter로 전송됨)
	    Content updateNoticeboard = new Content(
	    		request.getParameter("list_id"),
	            request.getParameter("list_title"),
	            request.getParameter("list_date"),
	            request.getParameter("pet_id"),
	            request.getParameter("list_content"));
	    
	    noticeboardDAO.updateNoticeboard(updateNoticeboard);
	    return "redirect:/noticeboard/list";
	    
    }
}