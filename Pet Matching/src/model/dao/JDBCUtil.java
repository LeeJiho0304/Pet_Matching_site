// Java Project �� JDBCUtil
// DBCP2 ���� jar ������ ������Ʈ�� �����ؾ� ������
// commons-dbcp2-X.X.X.jar, commons-pool2-X.X.X.jar, commons-logging-X.X.jar
package model.dao;

import java.sql.*;

public class JDBCUtil {
	
	private static ConnectionManager connMan = new ConnectionManager();
	
	private String sql = null; 				// ������ query
	private String[] sqlarr = null;			// ������ query �迭
	private Object[] parameters = null;; 	// PreparedStatement �� �Ű����� ���� �����ϴ� �迭
	private static Connection conn = null;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private int resultSetType = ResultSet.TYPE_FORWARD_ONLY,
				resultSetConcurrency = ResultSet.CONCUR_READ_ONLY;

	// �⺻ ������
	public JDBCUtil() {	}

	
	// �Ű����� ���� query�� ���޹޾� query�� �����ϴ� ������
	public JDBCUtil(String sql) {
		this.setSql(sql);		
	}

	// �Ű������� �迭�� �Բ� query�� ���޹޾� ������ �����ϴ� ������
	public JDBCUtil(String sql, Object[] parameters) {
		this.setSql(sql);
		this.setParameters(parameters);
	}
	
	// sql ���� setter
	public void setSql(String sql) {
		this.sql = sql;
	}

	// Object[] ���� setter
	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}
	
	// sql ���� getter
	public String getSql() {
		return this.sql;
	}

	// �Ű����� �迭���� Ư����ġ�� �Ű������� ��ȯ�ϴ� �޼ҵ�
	private Object getParameter(int index) throws Exception {
		if (index >= getParameterSize())
			throw new Exception("INDEX ���� �Ķ������ �������� �����ϴ�.");
		return parameters[index];
	}

	// �Ű������� ������ ��ȯ�ϴ� �޼ҵ�
	private int getParameterSize() {
		return parameters == null ? 0 : parameters.length;
	}

	// sql �� Object[] ���� setter
	public void setSqlAndParameters(String sql, Object[] parameters) {
		this.sql = sql;
		this.parameters = parameters;
		this.resultSetType = ResultSet.TYPE_FORWARD_ONLY;
		this.resultSetConcurrency = ResultSet.CONCUR_READ_ONLY;
	}
	
	// sql �� Object[], resultSetType, resultSetConcurrency ���� setter
	public void setSqlAndParameters(String sql, Object[] parameters, int resultSetType, int resultSetConcurrency) {
		this.sql = sql;
		this.parameters = parameters;
		this.resultSetType = resultSetType;
		this.resultSetConcurrency = resultSetConcurrency;
	}
	

	// ������  PreparedStatement�� ��ȯ
	private PreparedStatement getPreparedStatement() throws SQLException {
		if (conn == null) {
			conn = connMan.getConnection();
			conn.setAutoCommit(false);
		}
		if (pstmt != null) pstmt.close();
		pstmt = conn.prepareStatement(sql, resultSetType, resultSetConcurrency);
		// JDBCUtil.printDataSourceStats(ds);
		return pstmt;
	}

	// JDBCUtil�� ������ �Ű������� �̿���  executeQuery�� �����ϴ� �޼ҵ�
	public ResultSet executeQuery() {
		try {
			pstmt = getPreparedStatement();
			for (int i = 0; i < getParameterSize(); i++) {
				pstmt.setObject(i + 1, getParameter(i));
			}
			rs = pstmt.executeQuery();
			return rs;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	// JDBCUtil�� ������ �Ű������� �̿��� executeUpdate�� �����ϴ� �޼ҵ�
	public int executeUpdate() throws SQLException, Exception {
		pstmt = getPreparedStatement();
		int parameterSize = getParameterSize();
		for (int i = 0; i < parameterSize; i++) {
			if (getParameter(i) == null) { // �Ű����� ���� ���� �κ��� ���� ���
				pstmt.setString(i + 1, null);
			} else {
				pstmt.setObject(i + 1, getParameter(i));
			}
		}
		return pstmt.executeUpdate();
	}
	
	// sql �迭 ���� setter
		public void setSqlArr(String[] sqlarr){
			this.sqlarr = sqlarr;
		}
	
	// ������  Statement�� ��ȯ
		private Statement getStatement() throws SQLException {
			if (conn == null) {
				conn = connMan.getConnection();
				conn.setAutoCommit(false);
			}
			if (stmt != null) stmt.close();
			stmt = conn.createStatement();
			// JDBCUtil.printDataSourceStats(ds);
			return stmt;
		}
		
	//�������� ������ �ϰ� �����ϴ� �޼ҵ� 
	public int[] executeBatch() throws SQLException, Exception {
		stmt = getStatement();
		for(String sql : sqlarr)
		{
			stmt.addBatch(sql);
		}
		
		return stmt.executeBatch();
	}
	
	public void setAutocommit(boolean b){
		try{
			conn.setAutoCommit(b);
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	// �ڿ� ��ȯ
	public void close() {
		
		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
				pstmt = null;
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		if (stmt != null) {
			try {
				stmt.close();
				stmt = null;
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	
	
	public void commit() {
		try {
			conn.commit();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void rollback() {
		try {
			conn.rollback();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	// DataSource �� ����
	public void shutdownPool() {
		this.close();
		connMan.close();
	}

	// ���� Ȱ��ȭ ������ Connection �� ������ ��Ȱ��ȭ ������ Connection ���� ���
	public void printDataSourceStats() {
		connMan.printDataSourceStats();
	}
}
