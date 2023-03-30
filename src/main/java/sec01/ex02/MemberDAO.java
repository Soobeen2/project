package sec01.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.Date;

public class MemberDAO {

	private PreparedStatement pstmt;
	private Connection con;

	
	public List<MemberVO> listMembers(){
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
		 connDB();
		 String query = "select * from t_member ";
		 System.out.println("preparedStatement: " + query);
		 pstmt = con.prepareStatement(query);
		 ResultSet rs = pstmt.executeQuery();
		 
		 while (rs.next()){
			String id = rs.getString("id");
			String pwd = rs.getString("pwd");
			String name = rs.getString("name");
			String email = rs.getString("email");
			Date joinDate = rs.getDate("joinDate");
			
			MemberVO vo = new MemberVO();
			vo.setId(id);
			vo.setPwd(pwd);
			vo.setName(name);
			vo.setEmail(email);
			vo.setJoinDate(joinDate);
			list.add(vo);
		 }
		 rs.close();
		 pstmt.close();
		 con.close();
		} catch (Exception e) {
		  e.printStackTrace();
		}
		return list;
	}

	private void connDB() {
		try {
			String driver = "oralce.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "";
			String pwd = "";		
					
			Class.forName("oralce.jdbc.driver.OracleDriver");
			System.out.println("Oracle 드라이버 로딩 성공");
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println("Connection 생성 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
