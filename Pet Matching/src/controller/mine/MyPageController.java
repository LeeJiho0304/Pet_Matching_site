package controller.mine;

import controller.Controller;
import controller.user.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyPageController implements Controller {
	
	private String forwardUrl;
	
	public MyPageController (String forwardUrl) {
		if (forwardUrl == null) {
            throw new NullPointerException("forwardUrl is null. 이동할 URL을 입력하세요.");
        }
        this.forwardUrl = forwardUrl;
	}
	
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    	req.setAttribute("curUserId", UserSessionUtils.getUserFromSession(req.getSession()));
    	return forwardUrl;
    }
    
}