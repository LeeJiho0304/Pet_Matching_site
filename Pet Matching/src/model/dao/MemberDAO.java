package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Member;

/**
 * 사용자 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * USERINFO 테이블에 사용자 정보를 추가, 수정, 삭제, 검색 수행 
 */
public class MemberDAO {
	
	private JDBCUtil jdbcUtil = null;
	
	public MemberDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
		
	/**
	 * 사용자 관리 테이블에 새로운 사용자 생성.
	 */
	public int create(Member member) throws SQLException {
      String sql = "INSERT INTO MEMBER VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";      
      Object[] param = new Object[] {member.getUserId(), member.getName(), member.getPassword(), 
            member.getRes_id(), member.getAge(), member.getGender(), member.getFamily_mem(), 
            member.getPet_cnt(), member.getAddress(), member.getPhone(), member.getJob(), member.getProfile()};
      jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil 에 insert문과 매개 변수 설정
                  
      try {            
         int result = jdbcUtil.executeUpdate();   // insert 문 실행
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

	
	/**
	 * 기존의 사용자 정보를 수정.*/
	public int update(Member member, String userId) throws SQLException {
      String sql = "UPDATE MEMBER "
               + "SET user_id = ?, user_name = ?, pwd=?, res_id=?, user_age=?, user_gender=?, family_member =?, pet_cnt = ?, address=?, phone=?, job=?, profile=? "
               + "WHERE user_id=?";
      Object[] param = new Object[] {member.getUserId(), member.getName(), member.getPassword(), 
            member.getRes_id(), member.getAge(), member.getGender(), member.getFamily_mem(), 
            member.getPet_cnt(), member.getAddress(), member.getPhone(), member.getJob(), member.getProfile(), userId};
      jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil에 update문과 매개 변수 설정
	         
      try {            
         int result = jdbcUtil.executeUpdate();   // update 문 실행
         if(result == 0)System.out.println("업데이트 없음");
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


	/**
	 * 주어진 사용자 ID에 해당하는 사용자 정보를 데이터베이스에서 찾아 User 도메인 클래스에 
	 * 저장하여 반환.
	 */
	public Member findUser(String userId) throws SQLException {
		  
      String sql = "SELECT user_id, user_name, pwd, res_id, user_age, user_gender, family_member, pet_cnt, address, phone, job, profile "
                 + "FROM MEMBER WHERE user_id=? ";
      jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});   // JDBCUtil에 query문과 매개 변수 설정

      try {
         ResultSet rs = jdbcUtil.executeQuery();      // query 실행
         if (rs.next()) {                  // 학생 정보 발견
            Member member = new Member(      // User 객체를 생성하여 학생 정보를 저장
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
         jdbcUtil.close();      // resource 반환
      }
      return null;
      
    }
	
	  
	/**
	 * 전체 사용자 정보를 검색하여 List에 저장 및 반환
	 */
	public List<Member> findUserList() throws SQLException {
		
		String sql = "SELECT user_id, user_name, pwd, res_id, user_age, user_gender, family_member, pet_cnt, address, phone, job, profile"
			+ "FROM MEMBER ";
		List<Member> userList = new ArrayList<Member>();	// User들의 리스트 생성
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			while (rs.next()) {						// 학생 정보 발견
				Member user = new Member(		// User 객체를 생성하여 학생 정보를 저장
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
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	/**
	 * 해당하는 사용자 정보만을 List에 저장하여 반환.
	 */
	public List<Member> findUserList(int currentPage, int countPerPage) throws SQLException {
		
        String sql = "SELECT userId, password, name, email, phone " 
        		   + "FROM USERINFO ORDER BY userId";
		jdbcUtil.setSqlAndParameters(sql, null,					// JDBCUtil에 query문 설정
				ResultSet.TYPE_SCROLL_INSENSITIVE,				// cursor scroll 가능
				ResultSet.CONCUR_READ_ONLY);						
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();				// query 실행			
			int start = ((currentPage-1) * countPerPage) + 1;	// 출력을 시작할 행 번호 계산
			if ((start >= 0) && rs.absolute(start)) {			// 커서를 시작 행으로 이동
				List<Member> userList = new ArrayList<Member>();	// User들의 리스트 생성
				do {
					Member user = new Member(		// User 객체를 생성하여 현재 행의 정보를 저장
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
					userList.add(user);							// 리스트에 User 객체 저장
				} while ((rs.next()) && (--countPerPage > 0));		
				return userList;							
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}

	/**
	 * 주어진 사용자 ID에 해당하는 사용자가 존재하는지 검사 
	 */
	public boolean existingUser(String userId) throws SQLException {
		
		String sql = "SELECT count(*) FROM USERINFO WHERE userid=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return false;
	}
}
