package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.Cashbook;
import vo.Member;

public class MemberDao {
	
	public void updateMemberPw(Member member, String memberPw) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet re = null;
		int row = 0;
		
		// 회원수정
		/* 쿼리
		 UPDATE member SET member_pw=PASSWORD(?), member_name=?, member_address=?, 
		member_gender=?, member_phone=?, member_email=?, update_date=NOW() 
		WHERE member_id=? AND member_pw=PASSWORD(?)		
		 */
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
				
			String sql = "UPDATE member SET member_pw=PASSWORD(?), member_name=?, member_address=?,"
								+ " member_gender=?, member_phone=?, member_email=?, update_date=NOW()"
								+ " WHERE member_id=? AND member_pw=PASSWORD(?)";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getChangePw());
			stmt.setString(2, member.getMemberName());
			stmt.setString(3, member.getMemberAddress());
			stmt.setString(4, member.getMemberGender());
			stmt.setString(5, member.getMemberPhone());
			stmt.setString(6, member.getMemberEmail());
			stmt.setString(7, member.getMemberId());
			stmt.setString(8, member.getMemberPw());
			
			row = stmt.executeUpdate();
			if(row == 1) { // 디버깅, row 값이 1일 경우 입력 성공, 아닐경우 실패
				System.out.println("☆★☆★☆★☆★ 회원정보수정 성공 ☆★☆★☆★☆★");
			} else {
				System.out.println("※※※※※※※ 회원정보수정 실패 ※※※※※※※");
			}	

		} catch (SQLException e) {
				e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				// -데이터베이스 자원 반환
				
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

		
	
	public int deleteMember(String memberId, String memberPw) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		// 회원탈퇴
		/* 쿼리
	 	DELETE FROM member WHERE member_id=? AND member_pw=PASSWORD(?);
		 */
		
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			
			String sql = "DELETE FROM member WHERE member_id=? AND member_pw=PASSWORD(?)";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setString(2, memberPw);
			
			System.out.println("[deleteMember] SQL : " + stmt);	
			
			row = stmt.executeUpdate();
			
			
			if(row == 1) { // 디버깅, row 값이 1일 경우 입력 성공, 아닐경우 실패
				System.out.println("☆★☆★☆★☆★ 회원탈퇴 성공 ☆★☆★☆★☆★");
			} else {
				System.out.println("※※※※※※※ 회원탈퇴 실패 ※※※※※※※");
			}
			
				
				
		}catch (SQLException e) {
				e.printStackTrace();
			}
		 catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				// -데이터베이스 자원 반환
				
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	
	
	
	public Member selectMemberOne(String memberId) {
		Member member = new Member();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		// 회원정보상세보기(select one)
		/* 쿼리
		 SELECT member_id memberId, member_name memberName, member_address memberAddress, 
		member_gender memberGender, member_phone memberPhone, member_email memberEmail 
		FROM member WHERE member_id=?;
		 */
		
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
					String sql = "SELECT member_id memberId, member_name memberName, member_address memberAddress,"
							+ " member_gender memberGender, member_phone memberPhone, member_email memberEmail"
							+ " FROM member WHERE member_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			System.out.println("[selectMemberOne] SQL : " + stmt);			
			rs = stmt.executeQuery();
				
			while(rs.next()) {
				member = new Member();
				member.setMemberId(rs.getString("memberId"));
				member.setMemberName(rs.getString("memberName"));
				member.setMemberAddress(rs.getString("memberAddress"));
				member.setMemberGender(rs.getString("memberGender"));
				member.setMemberPhone(rs.getString("memberPhone"));
				member.setMemberEmail(rs.getString("memberEmail"));
				System.out.println("[selectMemberOne] member.toString() : " + member.toString());
			}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
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
		return member;
	}

	
	
	// 회원가입
	public void insertMember(Member member) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			
			String sql = "INSERT INTO member"
				+ 		" (member_id, member_pw, member_name,"
				+ 		" member_address, member_gender, member_phone,"
				+ 		" member_email, create_date, Update_date)"
				+ 		" VALUES"
				+ 		" (?, PASSWORD(?), ?, ?, ?, ?, ?, NOW(), NOW())";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			stmt.setString(3, member.getMemberName());
			stmt.setString(4, member.getMemberAddress());
			stmt.setString(5, member.getMemberGender());
			stmt.setString(6, member.getMemberPhone());
			stmt.setString(7, member.getMemberEmail());

			System.out.println("[MemberDao.insertMember] insert value : " + member.toString());
			System.out.println("[MemberDao.insertMember] stmt : " + stmt);
			int row = stmt.executeUpdate();
			
			if(row == 1) { // 디버깅, row 값이 1일 경우 입력 성공, 아닐경우 실패
				System.out.println("☆★☆★☆★☆★ 회원가입 성공 ☆★☆★☆★☆★");
			} else {
				System.out.println("※※※※※※※ 회원가입 실패 ※※※※※※※");
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
		  }
	
	
	
	// 로그인
	public String selectMemberByIdPw(Member member) {
		String memberId = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT member_id memberId FROM member WHERE member_id=? AND member_pw=PASSWORD(?)";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cashbook","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			System.out.println("[MemberDao.selectMemberByIdPw] insert value : " + stmt);
			rs = stmt.executeQuery();
			
			  if(rs.next()) {
		            memberId = rs.getString("memberId");
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
		      return memberId;
		  }
	
}
