package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Pet;

public class PetDAO {
	
   private JDBCUtil jdbcUtil = null;
   
   public PetDAO() {         
      jdbcUtil = new JDBCUtil();   // JDBCUtil 객체 생성
   }
      
   public List<Pet> getAllMyPetList(String userId) throws SQLException{
      
      String sql = "SELECT pet_id, user_id, kinds, pet_name "
             + "FROM PET "
             + "WHERE user_id=?";
      jdbcUtil.setSqlAndParameters(sql,new Object[] {userId});   // JDBCUtil에 query문과 매개 변수 설정

      List<Pet> pet_list = new ArrayList<Pet>();
      try {
         ResultSet rs = jdbcUtil.executeQuery();      // query 실행
         while (rs.next()) {         
            Pet pet = new Pet();
            pet.setPet_id(rs.getString("pet_id"));
            pet.setUser_id(rs.getString("user_id"));
            pet.setPet_name(rs.getString("pet_name"));
            pet.setKinds(rs.getString("kinds"));            
            pet_list.add(pet);
         }
         return pet_list;
         
      } catch (Exception ex) {
         ex.printStackTrace();
         
      } finally {
         jdbcUtil.close();      // resource 반환
      }
      return null;
   }

   public Pet findPet(String pet_id) throws SQLException {
	   String sql = "SELECT pet_id, user_id, pet_name, kinds, race, age, pet_gender, vaccine, "
			   + "diagnosis_date, price "
               + "FROM PET WHERE pet_id=? ";
	   jdbcUtil.setSqlAndParameters(sql, new Object[] {pet_id});   // JDBCUtil에 query문과 매개 변수 설정
	   
	   try {
		   ResultSet rs = jdbcUtil.executeQuery();      // query 실행
		   if (rs.next()) {
			   Pet pet = new Pet(
	               rs.getString("pet_id"),
	               rs.getString("user_id"),
	               rs.getString("pet_name"),
	               rs.getString("kinds"),
	               rs.getString("race"),
	               rs.getInt("age"),
	               rs.getString("pet_gender"),
	               rs.getString("vaccine"),
	               rs.getString("diagnosis_date"),
	               rs.getInt("price"));
			   return pet;
         }
		   
      } catch (Exception ex) {
         ex.printStackTrace();
         
      } finally {
         jdbcUtil.close();      // resource 반환
      }
      return null;
   }
   
   public int insertPet(Pet pet, String userId) throws SQLException {
	   
      String sql = "INSERT INTO PET VALUES (pet_id_sec.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";   
      Object[] param = new Object[] { pet.getKinds(), pet.getRace(), pet.getAge(), pet.getPet_gender(), pet.getVaccine(),
                              pet.getDiagnosis_date(),pet.getPrice(), pet.getPet_name(), userId};
      jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil에 insert문과 매개 변수 설정
                  
      try {            
         int result = jdbcUtil.executeUpdate();   // insert문 실행
         return result;
         
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
         
      } finally {      
         jdbcUtil.commit();
         jdbcUtil.close();   // resource 반환
      }      
      return 0;         
   }

   public int updatePet(Pet pet) throws SQLException {
       
      String sql = "UPDATE pet "
               + "SET pet_name=?, kinds=?, race=?, age=?, pet_gender=?, vaccine=?, diagnosis_date=?, price=? "
               + "WHERE pet_id=?";
      Object[] param = new Object[] {pet.getPet_name(), pet.getKinds(), pet.getRace(), pet.getAge(),
    		  pet.getPet_gender(), pet.getVaccine(), pet.getDiagnosis_date(), pet.getPrice(), pet.getPet_id()};            
      jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil에 update문과 매개 변수 설정
         
      try {            
         int result = jdbcUtil.executeUpdate();   // update문 실행
         return result;
         
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      
      } finally {
         jdbcUtil.commit();
         jdbcUtil.close();   // resource 반환
      }      
      return 0;
   }
   
   public int deletePet(String pet_id) throws SQLException {
	   
      String sql = "DELETE FROM PET WHERE pet_id=?";      
      jdbcUtil.setSqlAndParameters(sql, new Object[] {pet_id});   // JDBCUtil에 delete문과 매개 변수 설정

      try {            
         int result = jdbcUtil.executeUpdate();   // delete문 실행
         return result;
         
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      
      } finally {
         jdbcUtil.commit();
         jdbcUtil.close();   // resource 반환
      }      
      return 0;
   }
   
}
