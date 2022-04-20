package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vo.Cashbook;

public class CashbookDao {
	public int deleteCashbook(int cashbookNo) {
		int row = 0;

		
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;

		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			conn.setAutoCommit(false); 
			
			
			String cashbookSql = "DELETE FROM hashtag WHERE cashbook_no=?";
			
			stmt = conn.prepareStatement(cashbookSql);
			stmt.setInt(1, cashbookNo);

			
			String hashtagSql = "DELETE FROM cashbook WHERE cashbook_no=?;";
			
			stmt2 = conn.prepareStatement(hashtagSql);
			stmt2.setInt(1, cashbookNo);
			stmt2.executeQuery();
	
			
			if(row == 1) {
				System.out.println("delete hashtag 성공");
			} else {
				System.out.println("delete hashtag 실패");		
			}
			
			conn.commit();
				
			
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}

		
	
	
	
	
	public Cashbook selectCashbookOne(int cashbookNo) {
		Cashbook cashbook = new Cashbook();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			
			String sql = "SELECT cashbook_no cashbookNo, cash_date cashDate, kind, cash, memo FROM cashbook WHERE cashbook_no = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cashbookNo);
			System.out.println(stmt + "◀ SQL selectCashbookOne");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				cashbook = new Cashbook();
				cashbook.setCashbookNo(rs.getInt("cashbookNo"));
				cashbook.setCashDate(rs.getString("cashDate"));
				cashbook.setKind(rs.getString("kind"));
				cashbook.setCash(rs.getInt("cash"));
				cashbook.setMemo(rs.getString("memo"));
			}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					// -데이터베이스 자원 반환
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return cashbook;
		}


		public void insertCashbook(Cashbook cashbook, List<String> hashtag) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				Class.forName("org.mariadb.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
				conn.setAutoCommit(false); // 자동커밋을 해제
				
				String sql = "INSERT INTO cashbook(cash_date,kind,cash,memo,update_date,create_date)"
						+ " VALUES(?,?,?,?,NOW(),NOW())";
				
				// insert + select 방금생성된 행의 키값 ex) select 방금입력한 cashbook_no from cashbook;
				stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); 
				stmt.setString(1, cashbook.getCashDate());
				stmt.setString(2, cashbook.getKind());
				stmt.setInt(3, cashbook.getCash());
				stmt.setString(4, cashbook.getMemo());
				stmt.executeUpdate(); // insert
				rs = stmt.getGeneratedKeys(); // select 방금입력한 cashbook_no from cashbook
				int cashbookNo = 0;
				if(rs.next()) {
					cashbookNo = rs.getInt(1);
				}
				
				// hashtag를 저장하는 코드
				PreparedStatement stmt2 = null;
				for(String h : hashtag) {
					String sql2 = "INSERT INTO hashtag(cashbook_no, tag, create_date) VALUES(?, ?, NOW())";
					stmt2 = conn.prepareStatement(sql2);
					stmt2.setInt(1, cashbookNo);
					stmt2.setString(2, h);
					stmt2.executeUpdate();
				}
				
				conn.commit();
			} catch(Exception e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	

	
	
	public List<Map<String, Object>> selectCashbookListByMonth(int y, int m) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		/*
		 SELECT 
		 	cashbook_no cashbookNo
		 	,DAY(cash_date) day
		 	,kind
		 	,cash
		 	,LEFT(memo, 5) memo
		 FROM cashbook
		 WHERE YEAR(cash_date) = ? AND MONTH(cash_date) = ?
		 ORDER BY DAY(cash_date) ASC
		 */
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT"
				+ "		 	cashbook_no cashbookNo"
				+ "		 	,DAY(cash_date) day"
				+ "		 	,kind"
				+ "		 	,cash"
				+ "		 	,LEFT(memo, 5) memo"
				+ "		 FROM cashbook"
				+ "		 WHERE YEAR(cash_date) = ? AND MONTH(cash_date) = ?"
				+ "		 ORDER BY DAY(cash_date) ASC, kind ASC";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, y);
			stmt.setInt(2, m);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cashbookNo", rs.getInt("cashbookNo"));
				map.put("day", rs.getInt("day"));
				map.put("kind", rs.getString("kind"));
				map.put("cash", rs.getInt("cash"));
				map.put("memo", rs.getString("memo"));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
