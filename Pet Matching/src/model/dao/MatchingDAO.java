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
 * matching 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * MATCHING 테이블에 matching 정보를 추가, 수정, 삭제, 검색 수행 
 */
public class MatchingDAO {
	
	private JDBCUtil jdbcUtil = null;
	
	public MatchingDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	//matching 테이블에 새로운 matching 생성
	public void insertMaching(Matching mat) throws SQLException {
		
		String sql = "INSERT INTO MATCHING VALUES (matching_id_sec.NEXTVAL, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] {mat.getPet_id(), mat.getMatching_date(),
									mat.getUser_id(), mat.getMatching_state(), mat.getList_id()}; 
		jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil 에 insert문과 매개 변수 설정
		
		try {
			
			jdbcUtil.executeUpdate();   // insert 문 실행
			
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
			
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();   // resource 반환
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
	
	// 기존의 matching 정보를 수정
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
		jdbcUtil.setSqlArr(sqlarr);	// JDBCUtil에 쿼리 배열 설정
		
		try {				
			int[] result = jdbcUtil.executeBatch();	// 일괄 갱신 메소드 실행
			if(result[0]!=1 || result[2]!=1){//result[1]에 해당하는 sql2 쿼리는 실행이 안될 경우도 있기 떄문에 제외 (신청자가 1명 뿐일때는 다른 신청자가 없음으로 쿼리가 실행이 되지 않는다.)
				throw new Exception();
			}
			jdbcUtil.commit();

		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
			
		} finally {
			jdbcUtil.setAutocommit(true);//auto-commit mode 설정
			jdbcUtil.close();   // resource 반환
		}
		return;
	}
	
	//matching 테이블에서 해당하는 matching 삭제
	public int deleteMatching(String matching_id) throws SQLException {
		
		String sql = "DELETE FROM MATCHING WHERE matching_id=?";
		Object[] param = new Object[] { matching_id };
		jdbcUtil.setSqlAndParameters(sql, param);   // JDBCUtil에 delete문과 매개 변수 설정

		try {            
			int result = jdbcUtil.executeUpdate();   // delete문 실행
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

	//매칭 테이블에서 내가 신청한 목록 가져오는 쿼리
	public List<MatNotice> getMyApplicationList(String userId)throws SQLException{
		
	    String sql = "select d.list_id as list_id, d.list_title as list_title, matching.matching_state as matching_state, d.race as race "
	             + "from matching, (select pet.race as race, noticeboard.list_id as list_id, noticeboard.list_title as list_title, pet.pet_id as pet_id "
	                            + "from noticeboard join pet on noticeboard.pet_id = pet.pet_id) d "
	             + "where matching.pet_id = d.pet_id and matching.user_id = ?";
	    jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});   // JDBCUtil에 query문과 매개 변수 설정

	    List<MatNotice> arr = new ArrayList<MatNotice>();
	    try {
	         ResultSet rs = jdbcUtil.executeQuery();      // query 실행
	         while (rs.next()) {               
	            // 내가 신청한 매칭 대기 정보 발견
	            MatNotice mn = new MatNotice(      // 매칭 객체를 생성하여 매칭 대기 정보를 저장
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
	         jdbcUtil.close();      // resource 반환
	    }
	    return null;
	}

	//매칭된 테이블 리스트 확인
	public List<CompleteMatching> getMyMatchingList(String userId)throws SQLException{
		
		String sql = "select pet.user_id as owner, d.user_id as apply, pet.pet_id as pet_id, pet.pet_name as pet_name "
    			+ "from pet, (select matching.pet_id, matching.user_id, matching.matching_state "
    					  + "from matching join noticeboard on matching.list_id = noticeboard.list_id )d "
    			+ "where pet.pet_id = d.pet_id and d.matching_state = 1 and (d.user_id = ? or pet.user_id = ?)";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId, userId});	// JDBCUtil에 query문과 매개 변수 설정

		List<CompleteMatching> arr = new ArrayList<CompleteMatching>();
		try {
			ResultSet rs = jdbcUtil.executeQuery();// query 실행
			while (rs.next()) {					
				// 매칭된 정보 발견
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
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	//매칭 테이블에서 특정 게시글의 후보자 목록 가져오기 (특정 게시글은 파라미터로 받기)
	public Candidate getCandidateList(String userId, String list_id)throws SQLException{
		
		String sql = "SELECT matching.user_id as apply "
	    		+ "FROM pet join matching on pet.pet_id = matching.pet_id "
	    		+ "WHERE pet.user_id=? and matching.list_id=? and matching.matching_state=0";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId, list_id});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			Candidate candidate = new Candidate(list_id, null);
			List <Member> user_list = new ArrayList <Member>();
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
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
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
}
