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
    	
    	if (request.getMethod().equals("GET")) {   // GET request: �Խñ� ���� ���� form ��û
    		String list_id = request.getParameter("list_id");
    		Content noticeboard = noticeboardDAO.getContentNoticeBoard(list_id);
	        List<Pet> pet_list = petDAO.getAllMyPetList(curUserId);

	        if (curUserId == null) {
	        	// �����Ϸ��� id�� login id�� �ٸ� ��� ����� ���� ȭ������ ���� �޼����� ����
	            request.setAttribute("updateFailed", true);
	            request.setAttribute("exception", new IllegalStateException("�ٸ� ����ڰ� �ۼ��� �Խñ��� ������ ������ �� �����ϴ�."));            
	            return "redirect:/noticeboard/list";      // ����� ���� ȭ������ �̵� (forwarding)
	        }
	        
	        request.setAttribute("pet_list", pet_list);
	        request.setAttribute("content", noticeboard);         // �� ������ �Խñ� ������ request ��ü�� ����                     
	        return "/noticeboard/UpdateNoticeBoardForm.jsp";      // �˻��� �Խñ� ������ �����ϴ� form���� ����     
	    }
    	
    	// POST request (������ �Խñ� ������ parameter�� ���۵�)
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