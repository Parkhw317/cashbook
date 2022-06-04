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

public class HashtagDao {
	
	public List<Map<String, Object>> selectDateTagList(String startDate, String endDate) { // 항목별 검색(상세항목)
		List<Map<String, Object>> dateTagList = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;


		try {
			/*
				SELECT
				c.cash_date cashDate
				,c.cashbook_no cashbookNo
				,c.kind
				,h.tag
				,c.cash
				  FROM hashtag h
				  INNER JOIN cashbook c
				  ON c.cashbook_no = h.cashbook_no
				  WHERE c.cash_date BETWEEN '2022-04-01' AND '2022-04-02'
				  ORDER BY c.cash_date ASC;
			 */
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","mariadb1234");
			
			String sql = "SELECT"
							+ " c.cash_date cashDate"
							+ " ,c.cashbook_no cashbookNo"
							+ " ,c.kind"
							+ " ,h.tag"
							+ " ,c.cash"
							+ " FROM hashtag h"
							+ " INNER JOIN cashbook c"
							+ " ON c.cashbook_no = h.cashbook_no"
							+ " WHERE c.cash_date BETWEEN ? AND ?"
							+ " ORDER BY c.cash_date ASC";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, startDate);
			stmt.setString(2, endDate);
			System.out.println(stmt + "◀ SQL selectDateTagList");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("cashDate", rs.getString("cashDate"));
				map.put("cashbookNo", rs.getInt("cashbookNo"));
				map.put("kind", rs.getString("kind"));
				map.put("tag", rs.getString("tag"));
				map.put("cash", rs.getInt("cash"));
				dateTagList.add(map);
				
			}
		}catch (Exception e) {
		         e.printStackTrace();
		      } finally {
		         try {
		            conn.close();
		         } catch (SQLException e) {
		            e.printStackTrace();
		         }
		      }
		     
				return dateTagList;
		   }
	
	
	
	public List<Map<String, Object>> selectTagOneList(String tag) { // 항목별 검색(상세항목)
		List<Map<String, Object>> tagOneList = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;


		try {
			/*
				SELECT h.cashbook_no cashbookNo, cash_date cashDate, kind, cash, memo, tag
				FROM cashbook c, hashtag h
				WHERE c.cashbook_no = h.cashbook_no
				AND tag = '?';
			 */
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","mariadb1234");
			
			String sql = "SELECT h.cashbook_no cashbookNo, cash_date cashDate, kind, cash, memo, tag"
								+ " FROM cashbook c, hashtag h"
								+ " WHERE c.cashbook_no = h.cashbook_no"
								+ " AND tag = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tag);
			System.out.println(stmt + "◀ SQL selectTagOneList");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("tag", rs.getString("tag"));
				map.put("cashDate", rs.getString("cashDate"));
				map.put("kind", rs.getString("kind"));
				map.put("cash", rs.getInt("cash"));
				map.put("memo", rs.getString("memo"));
				map.put("cashbookNo", rs.getInt("cashbookNo"));
				tagOneList.add(map);
				
			}
		}catch (Exception e) {
		         e.printStackTrace();
		      } finally {
		         try {
		            conn.close();
		         } catch (SQLException e) {
		            e.printStackTrace();
		         }
		      }
		     
				return tagOneList;
		   }
		
	
	public List<Map<String, Object>> selectKindRankList(String kind) { // 수입,지출별 검색
		List<Map<String, Object>> kindList = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			/*
				SELECT t.kind, t.tag, t.cnt, RANK() over(ORDER BY t.cnt DESC) 'rank'
				FROM 
				(SELECT c.kind, h.tag, COUNT(*) cnt
				FROM hashtag h
				INNER JOIN cashbook c
				ON h.cashbook_no = c.cashbook_no
				WHERE c.kind = '?'
				GROUP BY tag) t;
			 */
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","mariadb1234");
			String sql = "SELECT t.kind, t.tag, t.cnt, RANK() over(ORDER BY t.cnt DESC) 'rank'"
					+ " FROM"
					+ " (SELECT c.kind, h.tag, COUNT(*) cnt"
					+ " FROM hashtag h"
					+ " INNER JOIN cashbook c"
					+ " ON h.cashbook_no = c.cashbook_no"
					+ " WHERE c.kind = ?"
					+ " GROUP BY tag) t";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, kind);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("tag", rs.getString("tag"));
				map.put("count", rs.getInt("cnt"));
				map.put("rank", rs.getInt("rank"));
				map.put("kind", rs.getString("kind"));
				kindList.add(map);
			}
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
     
		return kindList;
   }
		
	
	
	public List<Map<String,Object>> selectTagRankList() { // 항목별 전체 랭킹
		List<Map<String, Object>> tagList = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
			
			try {
					/*
					SELECT t.tag, t.cnt, RANK() over(ORDER BY t.cnt DESC) ranking
		            FROM 
		            (SELECT tag, COUNT(*) cnt
		            FROM hashtag
		            GROUP BY tag) t
					 */
					Class.forName("org.mariadb.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","mariadb1234");
					String sql = "SELECT t.tag, t.cnt, RANK() over(ORDER BY t.cnt DESC) ranking"
							+ " FROM"
							+ " (SELECT tag, COUNT(*) cnt"
							+ " FROM hashtag"
							+ " GROUP BY tag) t";

					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery();
					while(rs.next()) {
						Map<String, Object> map = new HashMap<>();
						map.put("tag", rs.getString("tag"));
						map.put("count", rs.getInt("cnt"));
						map.put("rank", rs.getInt("ranking"));
						tagList.add(map);
					}
		      } catch (Exception e) {
		         e.printStackTrace();
		      } finally {
		         try {
		            conn.close();
		         } catch (SQLException e) {
		            e.printStackTrace();
		         }
		      }
		      return tagList;
		   }
}