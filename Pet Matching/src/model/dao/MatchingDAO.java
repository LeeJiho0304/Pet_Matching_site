package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dao.JDBCUtil;
import model.Candidate;
import model.CompleteMatching;
import model.MatNotice;
import model.Matching;
import model.Member;

/**
 * matching ������ ���� �����ͺ��̽� �۾��� �����ϴ� DAO Ŭ����
 * MATCHING ���̺� matching ������ �߰�, ����, ����, �˻� ���� 
 */
public class MatchingDAO {
	
	private JDBCUtil jdbcUtil = null;
	
	public MatchingDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
	
	//matching ���̺� ���ο� matching ����
	public void insertMaching(Matching mat) throws SQLException {
		
		String sql = "INSERT INTO MATCHING VALUES (matching_id_sec.NEXTVAL, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] {mat.getPet_id(), mat.getMatching_date(),
									mat.getUser_id(), mat.getMatching_state(), mat.getList_id()}; 
		jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil �� insert���� �Ű� ���� ����
		
		try {
			
			jdbcUtil.executeUpdate();   // insert �� ����
			
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
			
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();   // resource ��ȯ
		}
		return;
	}
	
	public int deleteApplyList(String list_id, String userId) throws SQLException {
		
		String sql = "DELETE FROM matching "
				+ "WHERE list_id=? and user_id=? ";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {list_id, userId});

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
	
	// ������ matching ������ ����
	public void UpdateMatching(String applicant, String list_id) throws SQLException {
		
		String sql = "UPDATE MATCHING "
					+ "SET matching_state=1 "
					+ "WHERE user_id= '" + applicant + "' and list_id= '" + list_id + "'";
		
		String sql2 = "UPDATE MATCHING "
				+ "SET matching_state=-1 "
				+ "WHERE matching_state=0 and list_id= '" + list_id + "'";
		
		String sql3 = "UPDATE NOTICEBOARD "
				+ "SET notice_state = 1 "
				+ "WHERE list_id= '" + list_id + "'";
		
		String[] sqlarr ={sql, sql2, sql3};
		jdbcUtil.setSqlArr(sqlarr);	// JDBCUtil�� ���� �迭 ����
		
		try {				
			int[] result = jdbcUtil.executeBatch();	// �ϰ� ���� �޼ҵ� ����
			if(result[0]!=1 || result[2]!=1){//result[1]�� �ش��ϴ� sql2 ������ ������ �ȵ� ��쵵 �ֱ� ������ ���� (��û�ڰ� 1�� ���϶��� �ٸ� ��û�ڰ� �������� ������ ������ ���� �ʴ´�.)
				throw new Exception();
			}
			jdbcUtil.commit();

		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
			
		} finally {
			jdbcUtil.setAutocommit(true);//auto-commit mode ����
			jdbcUtil.close();   // resource ��ȯ
		}
		return;
	}
	
	//matching ���̺��� �ش��ϴ� matching ����
	public int deleteMatching(String matching_id) throws SQLException {
		
		String sql = "DELETE FROM MATCHING WHERE matching_id=?";
		Object[] param = new Object[] { matching_id };
		jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil�� delete���� �Ű� ���� ����

		try {            
			int result = jdbcUtil.executeUpdate();   // delete�� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}

	//��Ī ���̺��� ���� ��û�� ��� �������� ����
	public List<MatNotice> getMyApplicationList(String userId)throws SQLException{
		
	    String sql = "select d.list_id as list_id, d.list_title as list_title, matching.matching_state as matching_state, d.race as race "
	             + "from matching, (select pet.race as race, noticeboard.list_id as list_id, noticeboard.list_title as list_title, pet.pet_id as pet_id "
	                            + "from noticeboard join pet on noticeboard.pet_id = pet.pet_id) d "
	             + "where matching.pet_id = d.pet_id and matching.user_id = ?";
	    jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});   // JDBCUtil�� query���� �Ű� ���� ����

	    List<MatNotice> arr = new ArrayList<MatNotice>();
	    try {
	         ResultSet rs = jdbcUtil.executeQuery();      // query ����
	         while (rs.next()) {               
	            // ���� ��û�� ��Ī ��� ���� �߰�
	            MatNotice mn = new MatNotice(      // ��Ī ��ü�� �����Ͽ� ��Ī ��� ������ ����
	               rs.getString("list_id"),
	               rs.getString("list_title"),
	               rs.getInt("matching_state"),
	               rs.getString("race")
	            );
	            arr.add(mn);
	         }
	         return arr;
	         
	    } catch (Exception ex) {
	         ex.printStackTrace();
	         
	    } finally {
	         jdbcUtil.close();      // resource ��ȯ
	    }
	    return null;
	}

	//��Ī�� ���̺� ����Ʈ Ȯ��
	public List<CompleteMatching> getMyMatchingList(String userId)throws SQLException{
		
		String sql = "select pet.user_id as owner, d.user_id as apply, pet.pet_id as pet_id, pet.pet_name as pet_name "
    			+ "from pet, (select matching.pet_id, matching.user_id, matching.matching_state "
    					  + "from matching join noticeboard on matching.list_id = noticeboard.list_id )d "
    			+ "where pet.pet_id = d.pet_id and d.matching_state = 1 and (d.user_id = ? or pet.user_id = ?)";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId, userId});	// JDBCUtil�� query���� �Ű� ���� ����

		List<CompleteMatching> arr = new ArrayList<CompleteMatching>();
		try {
			ResultSet rs = jdbcUtil.executeQuery();// query ����
			while (rs.next()) {					
				// ��Ī�� ���� �߰�
				CompleteMatching cm = new CompleteMatching();
				cm.setOwner_id(rs.getString("owner"));
				cm.setApp_id(rs.getString("apply"));
				cm.setPet_id(rs.getString("pet_id"));
				cm.setPet_name(rs.getString("pet_name"));
				arr.add(cm);
			}
			return arr;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	//��Ī ���̺��� Ư�� �Խñ��� �ĺ��� ��� �������� (Ư�� �Խñ��� �Ķ���ͷ� �ޱ�)
	public Candidate getCandidateList(String userId, String list_id)throws SQLException{
		
		String sql = "SELECT matching.user_id as apply "
	    		+ "FROM pet join matching on pet.pet_id = matching.pet_id "
	    		+ "WHERE pet.user_id=? and matching.list_id=? and matching.matching_state=0";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId, list_id});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			Candidate candidate = new Candidate(list_id, null);
			List <Member> user_list = new ArrayList <Member>();
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			while (rs.next()) {				
				Member user = new Member();
				user.setUserId(rs.getString("apply"));
				user_list.add(user);
			}
			candidate.setUser_list(user_list);
			return candidate;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
}
