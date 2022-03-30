package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Aftercare;

public class AftercareDAO {
	
   private JDBCUtil jdbcUtil = null;
   
   public AftercareDAO() {
      jdbcUtil = new JDBCUtil();   // JDBCUtil ��ü ����
   }
   
   public List<Aftercare> getAllAfterCareBoardList(String pet_id) throws SQLException{
      
      String sql = "SELECT ac_list_id, ac_list_title, writer "
    		  + "FROM aftercare "
    		  + "WHERE pet_id = ?";
      jdbcUtil.setSqlAndParameters(sql,new Object[] {pet_id});   // JDBCUtil��ü�� query�� �Ķ���� ����

      List<Aftercare> aftercare_list = new ArrayList<Aftercare>();
      try {
    	  System.out.println(pet_id);
    	  ResultSet rs = jdbcUtil.executeQuery();      // query ����
    	  
    	  if(!rs.next()){
    		  System.out.println("null");
    		  return null;
    	  }
    	  
          do {        	 
            Aftercare aftercare = new Aftercare();
            aftercare.setAc_list_id(rs.getString("ac_list_id"));
            aftercare.setAc_list_title(rs.getString("ac_list_title"));
            aftercare.setWriter(rs.getString("writer"));            
            aftercare_list.add(aftercare);
          } while (rs.next());
          return aftercare_list;
         
      } catch (Exception ex) {
    	  ex.printStackTrace();
    	  
      } finally {
         jdbcUtil.close();      // resource ��ȯ
      }
      return null;
   }
   
   public int insertAftercare(Aftercare aftercare) throws SQLException {
	   
      String sql = "INSERT INTO aftercare VALUES (ac_list_id_sec.NEXTVAL, ?, ?, ?, ?, ?, ?)";      
      Object[] param = new Object[] {aftercare.getAc_list_content(), aftercare.getAc_list_date(), 
    		  		null, aftercare.getAc_list_title(), aftercare.getPet_id(), aftercare.getWriter()};            
      jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil��ü�� query�� �Ķ���� ����
                  
      try {            
         int result = jdbcUtil.executeUpdate();   // insert query ����
         return result;
         
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
         
      } finally {      
         jdbcUtil.commit();
         jdbcUtil.close();   // resource ��ȯ
      }      
      return 0;         
   }

   public int updateAftercare(Aftercare aftercare, String CurUserId) throws SQLException {
       
      String sql = "UPDATE aftercare "
               + "SET ac_list_content=?, ac_list_title=?, ac_list_date=? "
               + "WHERE writer = ? and ac_list_id = ?";
      Object[] param = new Object[] {aftercare.getAc_list_content(), aftercare.getAc_list_title(), 
    		  		aftercare.getAc_list_date(), CurUserId, aftercare.getAc_list_id()};            
      jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil��ü�� query�� �Ķ���� ����
         
      try {            
         int result = jdbcUtil.executeUpdate();   // update query ����
         return result;
         
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
         
      } finally {
         jdbcUtil.commit();
         jdbcUtil.close();   // resource ��ȯ
      }      
      return 0;
   }
   
   public int deleteAftercare(String ac_list_id) throws SQLException {
	   
      String sql = "DELETE FROM aftercare WHERE ac_list_id=?";      
      jdbcUtil.setSqlAndParameters(sql, new Object[] {ac_list_id});   // JDBCUtil��ü�� query�� �Ķ���� ����
      
      try {            
         int result = jdbcUtil.executeUpdate();   // delete query ����
         return result;
         
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
         
      } finally {
         jdbcUtil.commit();
         jdbcUtil.close();   // resource ��ȯ
      }
      return 0;
   }
   
   public Aftercare getContentAfterBoard(String ac_list_id) {
	   
      String sql = "SELECT ac_list_id, pet_id, ac_list_content, ac_list_date, ac_list_title, writer "
            + "FROM aftercare "
            + "WHERE ac_list_id=? ";      
      jdbcUtil.setSqlAndParameters(sql, new Object[] {ac_list_id});   // JDBCUtil��ü�� query�� �Ķ���� ����

      Aftercare aftercare = new Aftercare();
      try {
         ResultSet rs = jdbcUtil.executeQuery();      // query ����
         
         while (rs.next()) {
            aftercare.setAc_list_id(rs.getString("ac_list_id"));
            aftercare.setPet_id(rs.getString("pet_id"));
            aftercare.setAc_list_content(rs.getString("ac_list_content"));
            aftercare.setAc_list_date(rs.getString("ac_list_date"));
            aftercare.setAc_list_title(rs.getString("ac_list_title"));
            aftercare.setWriter(rs.getString("writer"));
         }
         return aftercare;
         
      } catch (Exception ex) {
         ex.printStackTrace();
         
      } finally {
         jdbcUtil.close();      // resource ��ȯ
      }
      return null;
   }
   
}
