package controller.noticeboard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Pet;
import model.dao.PetDAO;

public class InsertNoticeboardFormController implements Controller{
	
	private String forwardUrl;
	private PetDAO petDAO = new PetDAO();
	
	public InsertNoticeboardFormController(String forwardUrl) {
		
		if (forwardUrl == null) {
			throw new NullPointerException("forwardUrl is null. 이동할 URL을 입력하세요.");
		}
		this.forwardUrl = forwardUrl;

	}
		
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		List<Pet> pet_list = petDAO.getAllMyPetList(UserSessionUtils.getUserFromSession(req.getSession()));
		req.setAttribute("pet_list", pet_list);
		req.setAttribute("curUserId", UserSessionUtils.getUserFromSession(req.getSession()));
		return forwardUrl;
		
	}
	
}