package controller.aftercare;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;

public class InsertAftercareFormController implements Controller{

	private String forwardUrl;
	
	public InsertAftercareFormController (String forwardUrl) {
		 if (forwardUrl == null) {
            throw new NullPointerException("forwardUrl is null. 이동할 URL을 입력하세요.");
        }
        this.forwardUrl = forwardUrl;
	}
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setAttribute("pet_id", req.getParameter("pet_id"));
	 	req.setAttribute("owner_id", req.getParameter("owner_id"));
	 	req.setAttribute("app_id", req.getParameter("app_id"));
	 	return forwardUrl;
	}
	
}