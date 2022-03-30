package controller.mine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Pet;
import model.dao.PetDAO;

public class ContentPetController implements Controller{
	private PetDAO petDAO = new PetDAO();
	
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		
		String pet_id = request.getParameter("pet_id");
		Pet pet = petDAO.findPet(pet_id);
		request.setAttribute("pet", pet);
		request.setAttribute("curUserId", 
				UserSessionUtils.getUserFromSession(request.getSession()));
		return "/mine/PetContent.jsp";
		
	}

}
