package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Member;

/**
 * ����� ������ ���� �����ͺ��̽� �۾��� �����ϴ� DAO Ŭ����
 * USERINFO ���̺� ����� ������ �߰�, ����, ����, �˻� ���� 
 */
public class MemberDAO {
	
	private JDBCUtil jdbcUtil = null;
	
	public MemberDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil ��ü ����
	}
		
	/**
	 * ����� ���� ���̺� ���ο� ����� ����.
	 */
	public int create(Member member) throws SQLException {
      String sql = "INSERT INTO MEMBER VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";      
      Object[] param = new Object[] {member.getUserId(), member.getName(), member.getPassword(), 
            member.getRes_id(), member.getAge(), member.getGender(), member.getFamily_mem(), 
            member.getPet_cnt(), member.getAddress(), member.getPhone(), member.getJob(), member.getProfile()};
      jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil �� insert���� �Ű� ���� ����
                  
      try {            
         int result = jdbcUtil.executeUpdate();   // insert �� ����
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

	
	/**
	 * ������ ����� ������ ����.*/
	public int update(Member member, String userId) throws SQLException {
      String sql = "UPDATE MEMBER "
               + "SET user_id = ?, user_name = ?, pwd=?, res_id=?, user_age=?, user_gender=?, family_member =?, pet_cnt = ?, address=?, phone=?, job=?, profile=? "
               + "WHERE user_id=?";
      Object[] param = new Object[] {member.getUserId(), member.getName(), member.getPassword(), 
            member.getRes_id(), member.getAge(), member.getGender(), member.getFamily_mem(), 
            member.getPet_cnt(), member.getAddress(), member.getPhone(), member.getJob(), member.getProfile(), userId};
      jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil�� update���� �Ű� ���� ����
	         
      try {            
         int result = jdbcUtil.executeUpdate();   // update �� ����
         if(result == 0)System.out.println("������Ʈ ����");
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


	/**
	 * �־��� ����� ID�� �ش��ϴ� ����� ������ �����ͺ��̽����� ã�� User ������ Ŭ������ 
	 * �����Ͽ� ��ȯ.
	 */
	public Member findUser(String userId) throws SQLException {
		  
      String sql = "SELECT user_id, user_name, pwd, res_id, user_age, user_gender, family_member, pet_cnt, address, phone, job, profile "
                 + "FROM MEMBER WHERE user_id=? ";
      jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});   // JDBCUtil�� query���� �Ű� ���� ����

      try {
         ResultSet rs = jdbcUtil.executeQuery();      // query ����
         if (rs.next()) {                  // �л� ���� �߰�
            Member member = new Member(      // User ��ü�� �����Ͽ� �л� ������ ����
               rs.getString("user_id"),
               rs.getString("user_name"),
               rs.getString("pwd"),
               rs.getString("res_id"),
               rs.getInt("user_age"),
               rs.getString("user_gender"),
               rs.getString("family_member"),
               rs.getInt("pet_cnt"),
               rs.getString("address"),
               rs.getString("phone"),
               rs.getString("job"),
               rs.getString("profile"));
            return member;
         }
         
      } catch (Exception ex) {
         ex.printStackTrace();
         
      } finally {
         jdbcUtil.close();      // resource ��ȯ
      }
      return null;
      
    }
	
	  
	/**
	 * ��ü ����� ������ �˻��Ͽ� List�� ���� �� ��ȯ
	 */
	public List<Member> findUserList() throws SQLException {
		
		String sql = "SELECT user_id, user_name, pwd, res_id, user_age, user_gender, family_member, pet_cnt, address, phone, job, profile"
			+ "FROM MEMBER ";
		List<Member> userList = new ArrayList<Member>();	// User���� ����Ʈ ����
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			while (rs.next()) {						// �л� ���� �߰�
				Member user = new Member(		// User ��ü�� �����Ͽ� �л� ������ ����
					  rs.getString("user_id"),
					  rs.getString("user_name"),
					  rs.getString("pwd"),
					  rs.getString("res_id"),
					  rs.getInt("user_age"),
					  rs.getString("user_gender"),
					  rs.getString("family_member"),
					  rs.getInt("pet_cnt"),
					  rs.getString("address"),
					  rs.getString("phone"),
					  rs.getString("job"),
					  rs.getString("profile"));
				userList.add(user);
				return userList;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	/**
	 * �ش��ϴ� ����� �������� List�� �����Ͽ� ��ȯ.
	 */
	public List<Member> findUserList(int currentPage, int countPerPage) throws SQLException {
		
        String sql = "SELECT userId, password, name, email, phone " 
        		   + "FROM USERINFO ORDER BY userId";
		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil�� query�� ����
				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll ����
				ResultSet.CONCUR_READ_ONLY);						
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query ����			
			int start = ((currentPage-1) * countPerPage) + 1;	// ����� ������ �� ��ȣ ���
			if ((start >= 0) && rs.absolute(start)) {			// Ŀ���� ���� ������ �̵�
				List<Member> userList = new ArrayList<Member>();	// User���� ����Ʈ ����
				do {
					Member user = new Member(		// User ��ü�� �����Ͽ� ���� ���� ������ ����
							rs.getString("user_id"),
							rs.getString("user_name"),
							rs.getString("pwd"),
							rs.getString("res_id"),
							rs.getInt("user_age"),
							rs.getString("user_gender"),
							rs.getString("family_member"),
							rs.getInt("pet_cnt"),
							rs.getString("address"),
							rs.getString("phone"),
							rs.getString("job"),
							rs.getString("profile"));	
					userList.add(user);							// ����Ʈ�� User ��ü ����
				} while ((rs.next()) && (--countPerPage > 0));		
				return userList;							
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}

	/**
	 * �־��� ����� ID�� �ش��ϴ� ����ڰ� �����ϴ��� �˻� 
	 */
	public boolean existingUser(String userId) throws SQLException {
		
		String sql = "SELECT count(*) FROM USERINFO WHERE userid=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query ����
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return false;
	}
}
