package loginDAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;



import dbconn.DBconnection;
import loginVO.LoginVO;
import join.AESUtil;

public class LoginDAO {
	
    private Connection conn;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private int rs1=0;
    public boolean er=false;
    public boolean bd=false;
    private static int vc=0;
    //db연결 
    public LoginDAO() throws ClassNotFoundException, SQLException {
        conn = new DBconnection().getconnection();
    }
    //로그인(아이디와 비밀번호가 맞는지 확인)
    public boolean insertLogin(String id, String pw) throws SQLException {
        //암호화
        String encryptedPw = null;
        try {
        	//암호화한 비밀번호 저장
			encryptedPw = AESUtil.encrypt(pw);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
        String sql = "SELECT id, pw FROM LoginInfo WHERE id = ? AND pw = ?";//아이디 비번 조회
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);//아이디 첫번째 ?에 넣음
        pstmt.setString(2, encryptedPw);//비밀번호 두번째 ?에 넣음
        rs = pstmt.executeQuery();
        
        if (rs.next()) {//조회된 내용이 있으면
        	
            return true;
        } else {//없으면
            return false;
        }
    }
    //회원가입
    public boolean insert_join(String name,String id, String pw, String birth) {
    	String sql="INSERT INTO LoginInfo(name,id,pw,birth) VALUES(?,?,?,to_date(?))";
    	try {
    	pstmt=conn.prepareStatement(sql);
	    pstmt.setString(1, name);//첫번째?
	    pstmt.setString(2, id);//두번째?
	    pstmt.setString(3, pw);//세번째?
	    pstmt.setString(4, birth);//네번째?
	    rs1=pstmt.executeUpdate();
    	}catch(SQLException e) {//오류 처리
    		JOptionPane.showMessageDialog(null, "회원가입 실패..");
    		
    		if(e.getErrorCode()==1841) {//생년 월일 형식이 아니면
    			JOptionPane.showMessageDialog(null, "생년월일을 제대로 입력해주세요.");
    			return bd;
    		}
    		else {//저 오류코드가 아니면 true를 반환
    			bd=true;
    		}
//    		e.printStackTrace();
    		if (e.getErrorCode() == 1) {//아이디가 중복될 경우
                // Primary key 또는 Unique constraint 위반 처리
                JOptionPane.showMessageDialog(null, "이미 존재하는 ID입니다.");
                return er;
    		}else {
    			er=true;
    		}
    		return false;
    	}
    	return true;
    	
    	
    }
    //아이디 찾기
    public List<String> find_id(String name,String birth) throws SQLException {
    	 List<String> idList = new ArrayList<>();

         try {
             // 생일 형식을 변환
             String formattedBirth = convertBirthFormat(birth);

             // SQL 쿼리 작성
             String sql = "select id from LoginInfo where name=? and birth=to_date(?,'YYYYMMDD')";
             pstmt = conn.prepareStatement(sql);
             pstmt.setString(1, name);//첫번째 ?
             pstmt.setString(2, formattedBirth);//두번째 ?

             // 쿼리 실행 및 결과 처리
             rs = pstmt.executeQuery();
             while (rs.next()) {//rs가 있으면 
                 idList.add(rs.getString("id"));//리스트에 추가
             }
         } catch (ParseException e) {
             
             throw new SQLException("Date format error");
         } finally {
             if (rs != null) rs.close();
             if (pstmt != null) pstmt.close();
         }

         return idList;
     }

     private String convertBirthFormat(String birth) throws ParseException {
         if (birth.length() != 6) {//6자리 아니면
             throw new ParseException("Invalid date format", 0);
         }

         // 생년월일의 앞 두 자리를 기준으로 연도를 변환
         String yearPrefix = (Integer.parseInt(birth.substring(0, 2)) <= 50) ? "20" : "19";
         String fullBirth = yearPrefix + birth;//앞뒤를 합침

         // "YYMMDD" 형식을 "YYYYMMDD" 형식으로 변환
         SimpleDateFormat originalFormat = new SimpleDateFormat("yyMMdd");
         SimpleDateFormat targetFormat = new SimpleDateFormat("yyyyMMdd");
         Date date = originalFormat.parse(birth);

         return targetFormat.format(date);
    }
    //비밀번호 찾기
    public String find_pw(String id,String name) throws Exception {
    	String sql="select pw from LoginInfo where id=? and name=?";
    	pstmt=conn.prepareStatement(sql);
    	pstmt.setString(1, id);//첫번째 ?
    	pstmt.setString(2, name);//두번째 ?
    	rs=pstmt.executeQuery();
    	String encryptedPw = null;//암호된 한 비밀번호 저장
    	if (rs.next()) {
            encryptedPw = rs.getString("pw");      
    	}
    	return AESUtil.decrypt(encryptedPw);//복호화(암호화한 비밀번호를 다시 원래대로 되돌림)
    }
    //내정보 표시
    public List<String> myinfo(String id) throws SQLException {
    	
    	String sql="select id,name,birth from LoginInfo where id=?";
    	pstmt=conn.prepareStatement(sql);
    	pstmt.setString(1, id);//첫번째 ?
    	rs=pstmt.executeQuery();
    	List<String> info = new ArrayList<>();//리스트
    	
    	while(rs.next()) {//조회한 게 있으면
    	info.add(rs.getString("id"));//id 가져와서 리스트 추가함
    	info.add(rs.getString("name"));//name 가져와서 리스트 추가함
    	Date birth = rs.getDate("birth");//birth 가져와서 
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//생년 월일 변환해서 표시
        String fBirth = (birth != null) ? sdf.format(birth) : "N/A";//생년월일을 년도를 네자리로 표시
        info.add(fBirth);//birth 가져와서 리스트 추가함
        
    	//LoginVO lv=new LoginVO(id);//(3)객체생성 
		//tiarray.add(tv);//(4) 컬렉션에 넣기 
    	}
    	
    	return info;//리스트 반환		
    }
    //기록 저장 및 내정보에 기록 넘겨줌
    public List<String>  record(String id) throws SQLException {
    	//ArrayList<LoginVO> larray = new ArrayList<LoginVO>();
    	String sql="SELECT p.score AS per, w.score AS wtd, n.good AS ng " +
                "FROM Game_person p " +
                "JOIN Game_wtd w ON w.id = p.id " +
                "JOIN newsdate n ON n.id = p.id " +
                "JOIN LoginInfo l ON l.id = p.id " +
                "WHERE l.id = ?";
    	pstmt=conn.prepareStatement(sql);
    	pstmt.setString(1, id);//첫번째 ?
    	rs=pstmt.executeQuery();
    	List<String> info = new ArrayList<>();
    	
    	if (rs.next()) {
            String ps = Integer.toString(rs.getInt("per"));//점수 가져옴
            String ws = Double.toString(rs.getDouble("wtd"));//점수 가져옴
            String ng = Integer.toString(rs.getInt("ng"));//조회수 가져옴
            info.add(ps);
            info.add(ws);
            info.add(ng);  
        }
    	return info;		
    } 
    //조회수 삽입
    public boolean insert_recordnews(String id,int viewCount) {
    	String sql="INSERT INTO NEWSDATE VALUES(?,?)";
    	
    	try {
	    	pstmt=conn.prepareStatement(sql); 
		    pstmt.setString(1, id);//첫번째 ?
		    pstmt.setInt(2, viewCount);//두번째 ?
		    
		    rs1=pstmt.executeUpdate();
	    
    	}catch(SQLException e) {
    		return false;
    	}
        	return true;
	
    }
    //승무패 게임 점수 삽입
    public boolean insert_Gamewtd_score(String id,double score) {
    	
    	String sql="INSERT INTO Game_wtd VALUES(?,?)";
    	
    	try {
    	
	    	pstmt=conn.prepareStatement(sql);
		    
		    pstmt.setString(1, id);//첫번째 ?
		    pstmt.setDouble(2, score);//두번째 ?
		    
		    rs1=pstmt.executeUpdate();
		   
	   
    	}catch(SQLException e) {
    		return false;
    	}
        	return true;
	
    }
    //퀴즈 점수 삽입
    public boolean insert_Gameperson_score(String id,int score) {
    	
    	String sql="INSERT INTO Game_person VALUES(?,?)";
    	
    	try {
    	
	    	pstmt=conn.prepareStatement(sql);
		    
		    pstmt.setString(1, id);//첫번째 ?
		    pstmt.setInt(2, score);//두번째 ?
		    
		    rs1=pstmt.executeUpdate();
		   
	    
    	}catch(SQLException e) {
    		return false;
    	}
        	return true;
	
    }
    //승무패 게임 점수 수정
    public boolean update_Gamewtd_score(String id, double sum5){
		String sql = "update Game_wtd set score=? where id=?";
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setDouble(1, sum5);//첫번째 ?
			pstmt.setString(2, id);//두번째 ?
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}//update-end
    
    public boolean update_recordnews(String id, int viewCount){
		String sql = "update newsdate set good=? where id=?";
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, viewCount);//첫번째 ?
			pstmt.setString(2, id);//두번째 ?
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}//update-end
    //퀴즈 점수 수정
    public boolean update_Game_person(String id, int score){
		String sql = "update Game_person set score=? where id=?";
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, score);//첫번째 ?
			pstmt.setString(2, id);//두번째 ?
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}//update-end
    //승무패 게임 점수 조회해서 숫자로 반환
    public double Gamewtd_score(String id) throws SQLException {
    	//ArrayList<LoginVO> larray = new ArrayList<LoginVO>();
    	String sql="select score from Game_wtd where id=?";
    	pstmt=conn.prepareStatement(sql);
    	pstmt.setString(1, id);//첫번째 ?
    	rs=pstmt.executeQuery();
    	
    	 if (rs.next()) {//rs있으면
    	        double result = rs.getDouble("score");//조회수 넣음
    	        
    	        return result;
    	    } else {
    	        
    	        return 0.0;
    	    }
    }
  //퀴즈 점수 조회해서 숫자로 반환
    public int Game_person_score(String id) throws SQLException {
    	//ArrayList<LoginVO> larray = new ArrayList<LoginVO>();
    	String sql="select score from Game_person where id=?";
    	pstmt=conn.prepareStatement(sql);
    	pstmt.setString(1, id);//첫번째 ?
    	rs=pstmt.executeQuery();
    	
    	 if (rs.next()) {//rs있으면
    	        int result = rs.getInt("score");//조회수 넣음
    	        
    	        return result;
    	    } else {
    	        
    	        return 0;
    	    }
    }
  //뉴스 조회수 조회해서 숫자로 반환
    public int newsdate(String id,int cnt1) throws SQLException {
    	//ArrayList<LoginVO> larray = new ArrayList<LoginVO>();
    	String sql="select good from newsdate where id=?";
    	pstmt=conn.prepareStatement(sql);
    	pstmt.setString(1, id);//첫번째 ?
    	rs=pstmt.executeQuery();
    	
    	 if (rs.next()) {//rs있으면
    	        int result = rs.getInt("good");//조회수 넣음
    	        
    	        return result;
    	    } else {
    	       
    	        return cnt1;
    	    }
    }
 public String passwordck(String id,String pw) throws Exception {
        //암호화
	 String encryptedInputPw = AESUtil.encrypt(pw);   
	 String sql = "SELECT pw FROM LoginInfo WHERE id = ? and pw=?";  // 비밀번호를 찾기 위한 쿼리 수정
	    pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, id);//첫번째 ?
	    pstmt.setString(2, encryptedInputPw);//두번째 ?
	    rs = pstmt.executeQuery();
	    
	    // 데이터베이스에서 비밀번호를 가져온다
	    String encryptedPw = null;
	    if (rs.next()) {
	        encryptedPw = rs.getString("pw");//비밀번호 가져와서
	    }
	    
	    // 비밀번호가 null인 경우 처리
	    if (encryptedPw == null) {//비어있으면
	        throw new SQLException("Password from database is null.");
	    }
	    
	    // 비밀번호를 복호화하여 반환
	    return AESUtil.decrypt(encryptedPw);
    
}
 //비밀번호 수정
 public boolean edit(String id,String pw_up){
		String sql = "update LoginInfo set pw=? where id=?";
		String encryptedPw = null;
		//암호화
        try {
			encryptedPw = AESUtil.encrypt(pw_up);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
        
		try{
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, encryptedPw);//첫번째 ?
			
			pstmt.setString(2, id);//두번째 ?
			pstmt.executeUpdate();
		}catch(SQLException e){
			
			return false;
		}
		return true;
	}//update-end
 public boolean insert_chuchen(String id,int like0,int like1,int like2,int like3) {
 	String sql="INSERT INTO chuchen VALUES(?,?,?,?,?)";
 	
 	try {
	    	pstmt=conn.prepareStatement(sql); 
		    pstmt.setString(1, id);//첫번째 ?
		    pstmt.setInt(2, like0);//두번째 ?
		    pstmt.setInt(3, like1);//두번째 ?
		    pstmt.setInt(4, like2);//두번째 ?
		    pstmt.setInt(5, like3);//두번째 ?
		    
		    rs1=pstmt.executeUpdate();
	    
 	}catch(SQLException e) {
 		return false;
 	}
     	return true;
	
 }
 public boolean update_chuchen(String id, int like0,int like1,int like2,int like3){
		String sql = "update chuchen set chu0=?,chu1=?,chu2=?,chu3=? where id=?";
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, like0);//첫번째 ?
			pstmt.setInt(2, like1);//첫번째 ?
			pstmt.setInt(3, like2);//첫번째 ?
			pstmt.setInt(4, like3);//첫번째 ?
			pstmt.setString(5, id);//첫번째 ?
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}//update-end
 public int chuchen0(String id) throws SQLException {
 	//ArrayList<LoginVO> larray = new ArrayList<LoginVO>();
 	String sql="select chu0 from chuchen where id=?";
 	pstmt=conn.prepareStatement(sql);
 	pstmt.setString(1, id);//첫번째 ?
 	rs=pstmt.executeQuery();
 	
 	 if (rs.next()) {//rs있으면
 	        int result = rs.getInt("chu0");//조회수 넣음
 	        
 	        return result;
 	    } else {
 	       
 	        return 0;
 	    }
 }
 public int chuchen1(String id) throws SQLException {
	 	//ArrayList<LoginVO> larray = new ArrayList<LoginVO>();
	 	String sql="select chu1 from chuchen where id=?";
	 	pstmt=conn.prepareStatement(sql);
	 	pstmt.setString(1, id);//첫번째 ?
	 	rs=pstmt.executeQuery();
	 	
	 	 if (rs.next()) {//rs있으면
	 	        int result = rs.getInt("chu1");//조회수 넣음
	 	        
	 	        return result;
	 	    } else {
	 	       
	 	        return 0;
	 	    }
	 }
 public int chuchen2(String id) throws SQLException {
	 	//ArrayList<LoginVO> larray = new ArrayList<LoginVO>();
	 	String sql="select chu2 from chuchen where id=?";
	 	pstmt=conn.prepareStatement(sql);
	 	pstmt.setString(1, id);//첫번째 ?
	 	rs=pstmt.executeQuery();
	 	
	 	 if (rs.next()) {//rs있으면
	 	        int result = rs.getInt("chu2");//조회수 넣음
	 	        
	 	        return result;
	 	    } else {
	 	       
	 	        return 0;
	 	    }
	 }
 public int chuchen3(String id) throws SQLException {
	 	//ArrayList<LoginVO> larray = new ArrayList<LoginVO>();
	 	String sql="select chu3 from chuchen where id=?";
	 	pstmt=conn.prepareStatement(sql);
	 	pstmt.setString(1, id);//첫번째 ?
	 	rs=pstmt.executeQuery();
	 	
	 	 if (rs.next()) {//rs있으면
	 	        int result = rs.getInt("chu3");//조회수 넣음
	 	        
	 	        return result;
	 	    } else {
	 	       
	 	        return 0;
	 	    }
	 }
 
	 public boolean update_chuchen_cnt(int like0,int like1,int like2,int like3){
			String sql = "update chuchen_cnt set chu0_cnt=?,chu1_cnt=?,chu2_cnt=?,chu3_cnt=?";
			try{
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, like0);//첫번째 ?
				pstmt.setInt(2, like1);//첫번째 ?
				pstmt.setInt(3, like2);//첫번째 ?
				pstmt.setInt(4, like3);//첫번째 ?
				
				pstmt.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
				return false;
			}
			return true;
		}//update-end
	 public int chuchen0_cnt() throws SQLException {
	 	//ArrayList<LoginVO> larray = new ArrayList<LoginVO>();
	 	String sql="select chu0_cnt from chuchen_cnt";
	 	pstmt=conn.prepareStatement(sql);
	 	
	 	rs=pstmt.executeQuery();
	 	
	 	 if (rs.next()) {//rs있으면
	 	        int result = rs.getInt("chu0_cnt");//조회수 넣음
	 	        
	 	        return result;
	 	    } else {
	 	       
	 	        return 0;
	 	    }
	 }
	 public int chuchen1_cnt() throws SQLException {
		 	//ArrayList<LoginVO> larray = new ArrayList<LoginVO>();
		 	String sql="select chu1_cnt from chuchen_cnt";
		 	pstmt=conn.prepareStatement(sql);
		 	
		 	rs=pstmt.executeQuery();
		 	
		 	 if (rs.next()) {//rs있으면
		 	        int result = rs.getInt("chu1_cnt");//조회수 넣음
		 	        
		 	        return result;
		 	    } else {
		 	       
		 	        return 0;
		 	    }
		 }
	 public int chuchen2_cnt() throws SQLException {
		 	//ArrayList<LoginVO> larray = new ArrayList<LoginVO>();
		 	String sql="select chu2_cnt from chuchen_cnt";
		 	pstmt=conn.prepareStatement(sql);
		 	
		 	rs=pstmt.executeQuery();
		 	
		 	 if (rs.next()) {//rs있으면
		 	        int result = rs.getInt("chu2_cnt");//조회수 넣음
		 	        
		 	        return result;
		 	    } else {
		 	       
		 	        return 0;
		 	    }
		 }
	 public int chuchen3_cnt() throws SQLException {
		 	//ArrayList<LoginVO> larray = new ArrayList<LoginVO>();
		 	String sql="select chu3_cnt from chuchen_cnt";
		 	pstmt=conn.prepareStatement(sql);
		 	
		 	rs=pstmt.executeQuery();
		 	
		 	 if (rs.next()) {//rs있으면
		 	        int result = rs.getInt("chu3_cnt");//조회수 넣음
		 	        
		 	        return result;
		 	    } else {
		 	       
		 	        return 0;
		 	    }
		 }
}
