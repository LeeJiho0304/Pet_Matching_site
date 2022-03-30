package controller.mine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.dao.PetDAO;

public class DeletePetController implements Controller {
	
    private static final Logger log = LoggerFactory.getLogger(DeletePetController.class);
	private PetDAO petDAO = new PetDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String deleteId = request.getParameter("pet_id");
    	log.debug("Delete User : {}", deleteId);
    	
    	petDAO.deletePet(deleteId);	
		return "redirect:/mine/petlist";		
    }
    
}