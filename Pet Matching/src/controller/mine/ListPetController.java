package controller.mine;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Pet;
import model.dao.PetDAO;

public class ListPetController implements Controller{
	
	private PetDAO petDAO = new PetDAO();
	
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {

    	if (!UserSessionUtils.isLogined(request.getSession())) {
            return "redirect:/user/login/form";	
        }
    	
		List<Pet> pet_list = petDAO.getAllMyPetList(UserSessionUtils.getUserFromSession(request.getSession()));
		request.setAttribute("pet_list", pet_list);
		request.setAttribute("curUserId", UserSessionUtils.getUserFromSession(request.getSession()));
		return "/mine/MyPetList.jsp";
		
	}
	
}