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
    //db���� 
    public LoginDAO() throws ClassNotFoundException, SQLException {
        conn = new DBconnection().getconnection();
    }
    //�α���(���̵�� ��й�ȣ�� �´��� Ȯ��)
    public boolean insertLogin(String id, String pw) throws SQLException {
        //��ȣȭ
        String encryptedPw = null;
        try {
        	//��ȣȭ�� ��й�ȣ ����
			encryptedPw = AESUtil.encrypt(pw);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
        String sql = "SELECT id, pw FROM LoginInfo WHERE id = ? AND pw = ?";//���̵� ��� ��ȸ
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);//���̵� ù��° ?�� ����
        pstmt.setString(2, encryptedPw);//��й�ȣ �ι�° ?�� ����
        rs = pstmt.executeQuery();
        
        if (rs.next()) {//��ȸ�� ������ ������
        	
            return true;
        } else {//������
            return false;
        }
    }
    //ȸ������
    public boolean insert_join(String name,String id, String pw, String birth) {
    	String sql="INSERT INTO LoginInfo(name,id,pw,birth) VALUES(?,?,?,to_date(?))";
    	try {
    	pstmt=conn.prepareStatement(sql);
	    pstmt.setString(1, name);//ù��°?
	    pstmt.setString(2, id);//�ι�°?
	    pstmt.setString(3, pw);//����°?
	    pstmt.setString(4, birth);//�׹�°?
	    rs1=pstmt.executeUpdate();
    	}catch(SQLException e) {//���� ó��
    		JOptionPane.showMessageDialog(null, "ȸ������ ����..");
    		
    		if(e.getErrorCode()==1841) {//���� ���� ������ �ƴϸ�
    			JOptionPane.showMessageDialog(null, "��������� ����� �Է����ּ���.");
    			return bd;
    		}
    		else {//�� �����ڵ尡 �ƴϸ� true�� ��ȯ
    			bd=true;
    		}
//    		e.printStackTrace();
    		if (e.getErrorCode() == 1) {//���̵� �ߺ��� ���
                // Primary key �Ǵ� Unique constraint ���� ó��
                JOptionPane.showMessageDialog(null, "�̹� �����ϴ� ID�Դϴ�.");
                return er;
    		}else {
    			er=true;
    		}
    		return false;
    	}
    	return true;
    	
    	
    }
    //���̵� ã��
    public List<String> find_id(String name,String birth) throws SQLException {
    	 List<String> idList = new ArrayList<>();

         try {
             // ���� ������ ��ȯ
             String formattedBirth = convertBirthFormat(birth);

             // SQL ���� �ۼ�
             String sql = "select id from LoginInfo where name=? and birth=to_date(?,'YYYYMMDD')";
             pstmt = conn.prepareStatement(sql);
             pstmt.setString(1, name);//ù��° ?
             pstmt.setString(2, formattedBirth);//�ι�° ?

             // ���� ���� �� ��� ó��
             rs = pstmt.executeQuery();
             while (rs.next()) {//rs�� ������ 
                 idList.add(rs.getString("id"));//����Ʈ�� �߰�
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
         if (birth.length() != 6) {//6�ڸ� �ƴϸ�
             throw new ParseException("Invalid date format", 0);
         }

         // ��������� �� �� �ڸ��� �������� ������ ��ȯ
         String yearPrefix = (Integer.parseInt(birth.substring(0, 2)) <= 50) ? "20" : "19";
         String fullBirth = yearPrefix + birth;//�յڸ� ��ħ

         // "YYMMDD" ������ "YYYYMMDD" �������� ��ȯ
         SimpleDateFormat originalFormat = new SimpleDateFormat("yyMMdd");
         SimpleDateFormat targetFormat = new SimpleDateFormat("yyyyMMdd");
         Date date = originalFormat.parse(birth);

         return targetFormat.format(date);
    }
    //��й�ȣ ã��
    public String find_pw(String id,String name) throws Exception {
    	String sql="select pw from LoginInfo where id=? and name=?";
    	pstmt=conn.prepareStatement(sql);
    	pstmt.setString(1, id);//ù��° ?
    	pstmt.setString(2, name);//�ι�° ?
    	rs=pstmt.executeQuery();
    	String encryptedPw = null;//��ȣ�� �� ��й�ȣ ����
    	if (rs.next()) {
            encryptedPw = rs.getString("pw");      
    	}
    	return AESUtil.decrypt(encryptedPw);//��ȣȭ(��ȣȭ�� ��й�ȣ�� �ٽ� ������� �ǵ���)
    }
    //������ ǥ��
    public List<String> myinfo(String id) throws SQLException {
    	
    	String sql="select id,name,birth from LoginInfo where id=?";
    	pstmt=conn.prepareStatement(sql);
    	pstmt.setString(1, id);//ù��° ?
    	rs=pstmt.executeQuery();
    	List<String> info = new ArrayList<>();//����Ʈ
    	
    	while(rs.next()) {//��ȸ�� �� ������
    	info.add(rs.getString("id"));//id �����ͼ� ����Ʈ �߰���
    	info.add(rs.getString("name"));//name �����ͼ� ����Ʈ �߰���
    	Date birth = rs.getDate("birth");//birth �����ͼ� 
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//���� ���� ��ȯ�ؼ� ǥ��
        String fBirth = (birth != null) ? sdf.format(birth) : "N/A";//��������� �⵵�� ���ڸ��� ǥ��
        info.add(fBirth);//birth �����ͼ� ����Ʈ �߰���
        
    	//LoginVO lv=new LoginVO(id);//(3)��ü���� 
		//tiarray.add(tv);//(4) �÷��ǿ� �ֱ� 
    	}
    	
    	return info;//����Ʈ ��ȯ		
    }
    //��� ���� �� �������� ��� �Ѱ���
    public List<String>  record(String id) throws SQLException {
    	//ArrayList<LoginVO> larray = new ArrayList<LoginVO>();
    	String sql="SELECT p.score AS per, w.score AS wtd, n.good AS ng " +
                "FROM Game_person p " +
                "JOIN Game_wtd w ON w.id = p.id " +
                "JOIN newsdate n ON n.id = p.id " +
                "JOIN LoginInfo l ON l.id = p.id " +
                "WHERE l.id = ?";
    	pstmt=conn.prepareStatement(sql);
    	pstmt.setString(1, id);//ù��° ?
    	rs=pstmt.executeQuery();
    	List<String> info = new ArrayList<>();
    	
    	if (rs.next()) {
            String ps = Integer.toString(rs.getInt("per"));//���� ������
            String ws = Double.toString(rs.getDouble("wtd"));//���� ������
            String ng = Integer.toString(rs.getInt("ng"));//��ȸ�� ������
            info.add(ps);
            info.add(ws);
            info.add(ng);  
        }
    	return info;		
    } 
    //��ȸ�� ����
    public boolean insert_recordnews(String id,int viewCount) {
    	String sql="INSERT INTO NEWSDATE VALUES(?,?)";
    	
    	try {
	    	pstmt=conn.prepareStatement(sql); 
		    pstmt.setString(1, id);//ù��° ?
		    pstmt.setInt(2, viewCount);//�ι�° ?
		    
		    rs1=pstmt.executeUpdate();
	    
    	}catch(SQLException e) {
    		return false;
    	}
        	return true;
	
    }
    //�¹��� ���� ���� ����
    public boolean insert_Gamewtd_score(String id,double score) {
    	
    	String sql="INSERT INTO Game_wtd VALUES(?,?)";
    	
    	try {
    	
	    	pstmt=conn.prepareStatement(sql);
		    
		    pstmt.setString(1, id);//ù��° ?
		    pstmt.setDouble(2, score);//�ι�° ?
		    
		    rs1=pstmt.executeUpdate();
		   
	   
    	}catch(SQLException e) {
    		return false;
    	}
        	return true;
	
    }
    //���� ���� ����
    public boolean insert_Gameperson_score(String id,int score) {
    	
    	String sql="INSERT INTO Game_person VALUES(?,?)";
    	
    	try {
    	
	    	pstmt=conn.prepareStatement(sql);
		    
		    pstmt.setString(1, id);//ù��° ?
		    pstmt.setInt(2, score);//�ι�° ?
		    
		    rs1=pstmt.executeUpdate();
		   
	    
    	}catch(SQLException e) {
    		return false;
    	}
        	return true;
	
    }
    //�¹��� ���� ���� ����
    public boolean update_Gamewtd_score(String id, double sum5){
		String sql = "update Game_wtd set score=? where id=?";
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setDouble(1, sum5);//ù��° ?
			pstmt.setString(2, id);//�ι�° ?
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
			pstmt.setInt(1, viewCount);//ù��° ?
			pstmt.setString(2, id);//�ι�° ?
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}//update-end
    //���� ���� ����
    public boolean update_Game_person(String id, int score){
		String sql = "update Game_person set score=? where id=?";
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, score);//ù��° ?
			pstmt.setString(2, id);//�ι�° ?
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}//update-end
    //�¹��� ���� ���� ��ȸ�ؼ� ���ڷ� ��ȯ
    public double Gamewtd_score(String id) throws SQLException {
    	//ArrayList<LoginVO> larray = new ArrayList<LoginVO>();
    	String sql="select score from Game_wtd where id=?";
    	pstmt=conn.prepareStatement(sql);
    	pstmt.setString(1, id);//ù��° ?
    	rs=pstmt.executeQuery();
    	
    	 if (rs.next()) {//rs������
    	        double result = rs.getDouble("score");//��ȸ�� ����
    	        
    	        return result;
    	    } else {
    	        
    	        return 0.0;
    	    }
    }
  //���� ���� ��ȸ�ؼ� ���ڷ� ��ȯ
    public int Game_person_score(String id) throws SQLException {
    	//ArrayList<LoginVO> larray = new ArrayList<LoginVO>();
    	String sql="select score from Game_person where id=?";
    	pstmt=conn.prepareStatement(sql);
    	pstmt.setString(1, id);//ù��° ?
    	rs=pstmt.executeQuery();
    	
    	 if (rs.next()) {//rs������
    	        int result = rs.getInt("score");//��ȸ�� ����
    	        
    	        return result;
    	    } else {
    	        
    	        return 0;
    	    }
    }
  //���� ��ȸ�� ��ȸ�ؼ� ���ڷ� ��ȯ
    public int newsdate(String id,int cnt1) throws SQLException {
    	//ArrayList<LoginVO> larray = new ArrayList<LoginVO>();
    	String sql="select good from newsdate where id=?";
    	pstmt=conn.prepareStatement(sql);
    	pstmt.setString(1, id);//ù��° ?
    	rs=pstmt.executeQuery();
    	
    	 if (rs.next()) {//rs������
    	        int result = rs.getInt("good");//��ȸ�� ����
    	        
    	        return result;
    	    } else {
    	       
    	        return cnt1;
    	    }
    }
 public String passwordck(String id,String pw) throws Exception {
        //��ȣȭ
	 String encryptedInputPw = AESUtil.encrypt(pw);   
	 String sql = "SELECT pw FROM LoginInfo WHERE id = ? and pw=?";  // ��й�ȣ�� ã�� ���� ���� ����
	    pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, id);//ù��° ?
	    pstmt.setString(2, encryptedInputPw);//�ι�° ?
	    rs = pstmt.executeQuery();
	    
	    // �����ͺ��̽����� ��й�ȣ�� �����´�
	    String encryptedPw = null;
	    if (rs.next()) {
	        encryptedPw = rs.getString("pw");//��й�ȣ �����ͼ�
	    }
	    
	    // ��й�ȣ�� null�� ��� ó��
	    if (encryptedPw == null) {//���������
	        throw new SQLException("Password from database is null.");
	    }
	    
	    // ��й�ȣ�� ��ȣȭ�Ͽ� ��ȯ
	    return AESUtil.decrypt(encryptedPw);
    
}
 //��й�ȣ ����
 public boolean edit(String id,String pw_up){
		String sql = "update LoginInfo set pw=? where id=?";
		String encryptedPw = null;
		//��ȣȭ
        try {
			encryptedPw = AESUtil.encrypt(pw_up);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
        
		try{
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, encryptedPw);//ù��° ?
			
			pstmt.setString(2, id);//�ι�° ?
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
		    pstmt.setString(1, id);//ù��° ?
		    pstmt.setInt(2, like0);//�ι�° ?
		    pstmt.setInt(3, like1);//�ι�° ?
		    pstmt.setInt(4, like2);//�ι�° ?
		    pstmt.setInt(5, like3);//�ι�° ?
		    
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
			pstmt.setInt(1, like0);//ù��° ?
			pstmt.setInt(2, like1);//ù��° ?
			pstmt.setInt(3, like2);//ù��° ?
			pstmt.setInt(4, like3);//ù��° ?
			pstmt.setString(5, id);//ù��° ?
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
 	pstmt.setString(1, id);//ù��° ?
 	rs=pstmt.executeQuery();
 	
 	 if (rs.next()) {//rs������
 	        int result = rs.getInt("chu0");//��ȸ�� ����
 	        
 	        return result;
 	    } else {
 	       
 	        return 0;
 	    }
 }
 public int chuchen1(String id) throws SQLException {
	 	//ArrayList<LoginVO> larray = new ArrayList<LoginVO>();
	 	String sql="select chu1 from chuchen where id=?";
	 	pstmt=conn.prepareStatement(sql);
	 	pstmt.setString(1, id);//ù��° ?
	 	rs=pstmt.executeQuery();
	 	
	 	 if (rs.next()) {//rs������
	 	        int result = rs.getInt("chu1");//��ȸ�� ����
	 	        
	 	        return result;
	 	    } else {
	 	       
	 	        return 0;
	 	    }
	 }
 public int chuchen2(String id) throws SQLException {
	 	//ArrayList<LoginVO> larray = new ArrayList<LoginVO>();
	 	String sql="select chu2 from chuchen where id=?";
	 	pstmt=conn.prepareStatement(sql);
	 	pstmt.setString(1, id);//ù��° ?
	 	rs=pstmt.executeQuery();
	 	
	 	 if (rs.next()) {//rs������
	 	        int result = rs.getInt("chu2");//��ȸ�� ����
	 	        
	 	        return result;
	 	    } else {
	 	       
	 	        return 0;
	 	    }
	 }
 public int chuchen3(String id) throws SQLException {
	 	//ArrayList<LoginVO> larray = new ArrayList<LoginVO>();
	 	String sql="select chu3 from chuchen where id=?";
	 	pstmt=conn.prepareStatement(sql);
	 	pstmt.setString(1, id);//ù��° ?
	 	rs=pstmt.executeQuery();
	 	
	 	 if (rs.next()) {//rs������
	 	        int result = rs.getInt("chu3");//��ȸ�� ����
	 	        
	 	        return result;
	 	    } else {
	 	       
	 	        return 0;
	 	    }
	 }
 
	 public boolean update_chuchen_cnt(int like0,int like1,int like2,int like3){
			String sql = "update chuchen_cnt set chu0_cnt=?,chu1_cnt=?,chu2_cnt=?,chu3_cnt=?";
			try{
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, like0);//ù��° ?
				pstmt.setInt(2, like1);//ù��° ?
				pstmt.setInt(3, like2);//ù��° ?
				pstmt.setInt(4, like3);//ù��° ?
				
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
	 	
	 	 if (rs.next()) {//rs������
	 	        int result = rs.getInt("chu0_cnt");//��ȸ�� ����
	 	        
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
		 	
		 	 if (rs.next()) {//rs������
		 	        int result = rs.getInt("chu1_cnt");//��ȸ�� ����
		 	        
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
		 	
		 	 if (rs.next()) {//rs������
		 	        int result = rs.getInt("chu2_cnt");//��ȸ�� ����
		 	        
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
		 	
		 	 if (rs.next()) {//rs������
		 	        int result = rs.getInt("chu3_cnt");//��ȸ�� ����
		 	        
		 	        return result;
		 	    } else {
		 	       
		 	        return 0;
		 	    }
		 }
}
