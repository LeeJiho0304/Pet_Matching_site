package controller.aftercare;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.dao.AftercareDAO;
import model.Aftercare;

public class UpdateAftercareController implements Controller{

	private AftercareDAO aftercareDAO = new AftercareDAO();

    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	String curUserId = UserSessionUtils.getUserFromSession(request.getSession());
    	
    	if (request.getMethod().equals("GET")) {   // GET request: ���İ��� �Խñ� ���� form ��û
    		String ac_list_id = request.getParameter("ac_list_id");
	        Aftercare aftercare = aftercareDAO.getContentAfterBoard(ac_list_id);
	
	        if (curUserId == null) {
	            // �ۼ����� id�� login id�� �ٸ� ��� ����� ���� ȭ������ ���� �޼����� ����
	            request.setAttribute("updateFailed", true);
	            request.setAttribute("exception", 
	                  new IllegalStateException("�ٸ� ������� ������ ������ �� �����ϴ�."));            
	            return "/aftercare/list";      // ���İ��� �Խñ� ��� ȭ������ �̵� (forwarding)
	         }
	    
	         request.setAttribute("aftercare", aftercare);   
	         request.setAttribute("pet_id", request.getParameter("pet_id"));
	         request.setAttribute("owner_id", request.getParameter("owner_id"));
	         request.setAttribute("app_id", request.getParameter("app_id"));
	         return "/aftercare/UpdateAftercareForm.jsp";  //�˻��� �Խñ� ������ ���İ����Խñۼ���form���� ����
       }
       
       // POST request (������ ���İ��� �Խñ� ������ parameter�� ���۵�)
       Aftercare updateAftercare = new Aftercare(
    		  request.getParameter("ac_list_id"),
    		  request.getParameter("pet_id"),
    		  request.getParameter("ac_list_content"),
    		  request.getParameter("ac_list_date"),
              request.getParameter("ac_list_title"),
              request.getParameter("writer")
    		   );     
       aftercareDAO.updateAftercare(updateAftercare, curUserId);       
       return "/aftercare/list";
       
    }
    
}