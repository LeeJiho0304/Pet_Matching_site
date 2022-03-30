package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Content;
import model.NoticeBoard;

public class NoticeBoardDAO {
	
	private JDBCUtil jdbcUtil = null;
	
	public NoticeBoardDAO() {
		jdbcUtil = new JDBCUtil();	
	}
	
	public List<Content> getMyRecommendedList(String mostFavorite) throws SQLException{
		
		String sql = "SELECT noticeboard.list_id as list_id, noticeboard.list_title as list_title, "
	            + "noticeboard.list_date as list_date, pet.user_id as user_id "
	            + "FROM noticeboard join pet on noticeboard.pet_id = pet.pet_id "
	            + "where pet.race = ?";
	    jdbcUtil.setSqlAndParameters(sql,new Object[] {mostFavorite});

	    List<Content> rec_content_list = new ArrayList<Content>();
	    try {
	         ResultSet rs = jdbcUtil.executeQuery();      
	         
	         while (rs.next()) {         
	            Content content = new Content();
	            content.setList_id(rs.getString("list_id"));
	            content.setList_title(rs.getString("list_title"));
	            content.setList_date(rs.getString("list_date"));
	            content.setUser_id(rs.getString("user_id"));
	            rec_content_list.add(content);
	         }
	         return rec_content_list;
	         
	     } catch (Exception ex) {
	         ex.printStackTrace();
	         
	     } finally {
	         jdbcUtil.close();      
	     }
	     return null;
	}
	
	public List<Content> getMyNoticeBoardList(String userId)throws SQLException{
		
		String sql = "SELECT noticeboard.list_id as list_id, noticeboard.list_title as list_title, noticeboard.list_date as list_date, noticeboard.notice_state as notice_state "
    			+ "FROM noticeboard join pet on noticeboard.pet_id = pet.pet_id "
    			+ "WHERE pet.user_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	

		List<Content> content_list = new ArrayList<Content>();
		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			while (rs.next()) {					
				Content content = new Content();
				content.setList_id(rs.getString("list_id"));
				content.setList_title(rs.getString("list_title"));
				content.setList_date(rs.getString("list_date"));
				content.setNotice_state(rs.getInt("notice_state"));
				content_list.add(content);
			}
			return content_list;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	
	public NoticeBoard findNoticeboard(String list_id) throws SQLException {
		
        String sql = "SELECT list_title, list_content "
        			+ "FROM noticeboard WHERE list_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {list_id});	

		try {
			ResultSet rs = jdbcUtil.executeQuery();	
			if (rs.next()) {						
				NoticeBoard noticeboard = new NoticeBoard(						
						rs.getString("list_title"),
						rs.getString("list_content")
				);
				return noticeboard;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	public int insertNoticeBoard(NoticeBoard noticeboard) throws SQLException {
		String sql = "INSERT INTO noticeboard VALUES (list_id_sec.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {noticeboard.getPet_id(), noticeboard.getList_title(), noticeboard.getList_content(), noticeboard.getList_date(), noticeboard.getView_cnt(), noticeboard.getEntrust_term(), 0, noticeboard.getCategory()};
		jdbcUtil.setSqlAndParameters(sql, param);	
		
		try {				
			int result = jdbcUtil.executeUpdate();	
			return result;
			
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
			
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return 0;			
	}
	
	public int deleteNoticeboard(String list_id, String userId) throws SQLException {
		
		String sql = "DELETE FROM noticeboard n "
				+ "WHERE n.pet_id IN ( select p.pet_id from pet p where p.user_id = ? ) and n.list_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId, list_id});

		try {
			int result = jdbcUtil.executeUpdate();
			return result;
			
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
			
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}
	
	public void updateNoticeboard (Content updateNoticeboard) throws SQLException{
	
		String sql = "UPDATE noticeboard "
				+ "SET list_content=?, list_title=?, list_date=?, pet_id=? "
				+ "WHERE list_id =?";
		Object[] param = new Object[] {updateNoticeboard.getList_content(), updateNoticeboard.getList_title(), 
				updateNoticeboard.getList_date(), updateNoticeboard.getPet_id(), updateNoticeboard.getList_id()};	
		jdbcUtil.setSqlAndParameters(sql, param);	
		
		try {
		
			jdbcUtil.executeUpdate();	
			return;
			
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
			
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();	
		}
	}
	
	public List<Content> getAllNoticeBoardList() throws SQLException{
		
		String sql = "SELECT noticeboard.list_id as list_id, noticeboard.list_title as list_title, "
				+ "noticeboard.list_date as list_date, pet.user_id as user_id "
    			+ "FROM noticeboard join pet on noticeboard.pet_id = pet.pet_id ";
		jdbcUtil.setSqlAndParameters(sql,new Object[] {});	

		List<Content> content_list = new ArrayList<Content>();
		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			
			while (rs.next()) {			
				Content content = new Content();
				content.setList_id(rs.getString("list_id"));
				content.setList_title(rs.getString("list_title"));
				content.setList_date(rs.getString("list_date"));
				content.setUser_id(rs.getString("user_id"));
				content_list.add(content);
			}
			return content_list;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	public Content getContentNoticeBoard(String list_id) throws SQLException{
		
		String sql = "SELECT noticeboard.category as category, pet.pet_id as pet_id, pet.kinds as kinds, pet.race as race, noticeboard.list_content as list_content, "
				+ "pet.age as age, pet.pet_gender as pet_gender, "
				+ "pet.vaccine as vaccine, pet.price as price, pet.pet_name as pet_name, "
				+ "noticeboard.entrust_term as entrust_term, "
				+ "noticeboard.list_id as list_id, noticeboard.list_title as list_title, noticeboard.list_date as list_date, "
				+ "pet.user_id as user_id, noticeboard.notice_state as notice_state "
    			+ "FROM noticeboard join pet on noticeboard.pet_id = pet.pet_id "
				+ "WHERE noticeboard.list_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {list_id});	

		Content content = new Content();
		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			
			while (rs.next()) {
				content.setPet_id(rs.getString("pet_id"));
				content.setPet_kinds(rs.getString("kinds"));
				content.setPet_race(rs.getString("race"));
				content.setPet_age(Integer.parseInt(rs.getString("age")));
				content.setPet_gender(rs.getString("pet_gender"));
				content.setPet_vaccine(rs.getString("vaccine"));
				content.setPet_price(rs.getString("price"));
				content.setPet_name(rs.getString("pet_name"));
				content.setList_content(rs.getString("list_content"));
				content.setEntrust_term(rs.getString("entrust_term"));
				content.setCategory(rs.getString("category"));
				content.setList_id(rs.getString("list_id"));
				content.setList_title(rs.getString("list_title"));
				content.setList_date(rs.getString("list_date"));
				content.setUser_id(rs.getString("user_id"));
				content.setNotice_state(Integer.parseInt(rs.getString("notice_state")));;
			}
			return content;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
}
