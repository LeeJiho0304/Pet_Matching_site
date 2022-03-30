package controller.mine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Pet;
import model.dao.PetDAO;

public class InsertPetController implements Controller {

   private static final Logger log = LoggerFactory.getLogger(UpdatePetController.class);
   private PetDAO petDAO = new PetDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       
    	 Pet pet = new Pet(
              null,
              null,
              request.getParameter("pet_name"),
              request.getParameter("kinds"),
              request.getParameter("race"),
              Integer.parseInt(request.getParameter("age")),
              request.getParameter("pet_gender"),
              request.getParameter("vaccine"),
              request.getParameter("diagnosis_date"),
              Integer.parseInt(request.getParameter("price")));   
		
        log.debug("Create User : {}", pet);

		try {
			petDAO.insertPet(pet, UserSessionUtils.getUserFromSession(request.getSession()));
			return "redirect:/mine/petlist";	//  redirect
	        
		} catch (Exception e) {		// forwarding
            request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("pet", pet);
			return "/mine/InsertPetForm.jsp";
		}
      
    }

}
