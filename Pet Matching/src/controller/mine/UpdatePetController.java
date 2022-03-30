package controller.mine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.Pet;
import model.dao.PetDAO;

public class UpdatePetController implements Controller {
	
   private static final Logger log = LoggerFactory.getLogger(UpdatePetController.class);
   private PetDAO petDAO = new PetDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       
       if (request.getMethod().equals("GET")) {   // GET request    
         String pet_id = request.getParameter("pet_id");
 
         Pet pet = petDAO.findPet(pet_id);         
         if (pet == null)                      // user not found!
              return "redirect:/mine/petlist";

         request.setAttribute("pet", pet);
         request.setAttribute("pet_id", pet_id);              
         return "/mine/PetUpdateForm.jsp";      
       }
       
       // POST request 
       Pet updatePet = new Pet(
		  request.getParameter("pet_id"),
          request.getParameter("pet_name"),
          request.getParameter("kinds"),
          request.getParameter("race"),
          Integer.parseInt(request.getParameter("age")),
          request.getParameter("pet_gender"),
          request.getParameter("vaccine"),
          request.getParameter("diagnosis_date"),
          Integer.parseInt(request.getParameter("price"))); 
       
       log.debug("Update User : {}", updatePet);
       petDAO.updatePet(updatePet);       
       return "redirect:/mine/petlist";
    }
    
}