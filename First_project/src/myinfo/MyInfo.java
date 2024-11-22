package myinfo;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import loginDAO.LoginDAO;
import loginmain.Login;
import mainpage.Mainpage;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.Color;

public class MyInfo extends JFrame {

    private JPanel contentPane;
    private LoginDAO ldao;
    private String userId;
    private  int cnt;//게임을 했는지 안했는지 체크 하는 카운트(0이면 게임을 안했다는 표시)
    private  int cnt3;//게임을 했는지 안했는지 체크 하는 카운트(0이면 게임을 안했다는 표시)
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MyInfo frame = new MyInfo("testId",0,0,0,0,0,0,0,0,0);
                    frame.setVisible(true);
                  //중앙 배치
                    Dimension frameSize = frame.getSize();
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MyInfo(String id,int viewCount1,double sum5,int score,int cnt2,int cnt4,int like0,int like1,int like2,int like3) {
    	setResizable(false);
    	
        this.userId = id;//로그인으로부터 아이디를 받아 옴
        try {//LoginDAO와 연결
            ldao = new LoginDAO();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 605, 872);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
      //중앙 배치
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

        JTextArea txtrAaa = new JTextArea();
        txtrAaa.setFont(new Font("굴림", Font.PLAIN, 33));
        txtrAaa.setEditable(false);
        txtrAaa.setBounds(207, 82, 354, 155);
        contentPane.add(txtrAaa);
        
        
        List<String> info=null; //회원정보 저장
        

        try {
            info = ldao.myinfo(userId);
            if (info != null && !info.isEmpty()) {
                String infos = String.join("\n", info);
                txtrAaa.append(infos);
            }
            
        } catch (SQLException e) {
            //e.printStackTrace();
        }
       
        
        JButton btnNewButton = new JButton("");
        btnNewButton.setOpaque(false);
        btnNewButton.setContentAreaFilled(false);
        btnNewButton.setBorderPainted(false);
        btnNewButton.setFocusPainted(false);
        btnNewButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {//뒤로가기
        		dispose();
                setVisible(false);
                try {
					new Mainpage(id,sum5,score,cnt,cnt3,like0,like1,like2,like3).setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
        	}
        });
        btnNewButton.setFont(new Font("굴림", Font.PLAIN, 26));
        
        btnNewButton.setBounds(12, 10, 57, 50);
        contentPane.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("\uB85C\uADF8\uC544\uC6C3");
        btnNewButton_1.setOpaque(false);
        btnNewButton_1.setContentAreaFilled(false);
        btnNewButton_1.setBorderPainted(false);
        btnNewButton_1.setFocusPainted(false);
        btnNewButton_1.setBackground(new Color(255, 255, 255));
        btnNewButton_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {//로그아웃
        		cnt=0;//게임을 했는지 안했는지 체크 하는 카운트(0이면 게임을 안했다는 표시)
        		cnt3=0;//게임을 했는지 안했는지 체크 하는 카운트(0이면 게임을 안했다는 표시)
        		try {
					Mainpage.viewCount=ldao.newsdate(id, viewCount1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					
				}
        		Login loginFrame = new Login();
                loginFrame.setLocationRelativeTo(null);
                dispose();
                setVisible(false);
                loginFrame.setVisible(true);
        	}
        });
        cnt=cnt2;//만약 게임 한번 들어갔으면 cnt2가 1이 됨
	     cnt3=cnt4;//만약 게임 한번 들어갔으면 cnt4가 1이 됨
        if(cnt==0||cnt3==0) {//퀴즈나 게임 안했을 경우
        	
        	try {
        		ldao.Gamewtd_score(id);//게임 점수 조회
				ldao.Game_person_score(id);//게임 점수 조회
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				
			}
        }
        btnNewButton_1.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 15));
        btnNewButton_1.setBounds(400, 252, 161, 43);
        contentPane.add(btnNewButton_1);

	        ldao.update_recordnews(id, viewCount1);//뉴스 조회 정보 수정
	     
	        if(cnt==1) {//cnt가 1이면
	        boolean b2=ldao.update_Gamewtd_score(id, sum5);//게임 점수 정보 수정
	        
	        }
	        if(cnt3==1) {//cnt3이 1이면
	        boolean b3=ldao.update_Game_person(id, score);//퀴즈 점수 수정
	        
	        }
        
	        // 기록 막대차트 그리기
	        // Create dataset from the reco data
	        CategoryDataset dataset = createDataset();

	        
	        JFreeChart chart = ChartFactory.createBarChart(
	            "",   // 차트 제목
	            "",           // X축 제목
	            "",              // Y축제목
	            dataset,              // 데이터
	            PlotOrientation.VERTICAL, // 그래프 방향
	            false,                 // 범례포함
	            true,                 // 툴팁
	            false                 // URLs
	        );

	        // 차트 페널 추가
	        ChartPanel chartPanel = new ChartPanel(chart);
	        chartPanel.setBounds(39, 309, 521, 486); // Set the location and size of the chart panel
	        contentPane.add(chartPanel);
	        
	        JButton btnNewButton_1_1 = new JButton("\uB0B4\uC815\uBCF4 \uD3B8\uC9D1");
	        btnNewButton_1_1.setOpaque(false);
	        btnNewButton_1_1.setContentAreaFilled(false);
	        btnNewButton_1_1.setBorderPainted(false);
	        btnNewButton_1_1.setFocusPainted(false);
	        btnNewButton_1_1.setBackground(new Color(255, 255, 255));
	        btnNewButton_1_1.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent e) {//뒤로가기
	        		dispose();
	        		setVisible(false);
	        		new Edit(id,viewCount1,sum5,score,cnt,cnt3,like0,like1,like2,like3).setVisible(true);
	        	}
	        });
	        btnNewButton_1_1.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 15));
	        btnNewButton_1_1.setBounds(207, 252, 164, 43);
	        contentPane.add(btnNewButton_1_1);
	        
	        JLabel lblNewLabel_3 = new JLabel("");
	        lblNewLabel_3.setIcon(new ImageIcon(MyInfo.class.getResource("/background/mypage123.png")));
	        lblNewLabel_3.setBounds(0, 0, 999, 841);
	        contentPane.add(lblNewLabel_3);
        
    }
    //막대 차트의 값을 지정해주는 메소드
    
    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        List<String> reco;
        String [] cat= {"Game_person","Game_wtd","News_good"};//x축제목
        
        try {
            reco = ldao.record(userId);//기록 불러오기

           
            if (reco != null && reco.size() >= 3) {
                for (int i = 0; i < 3; i++) {
                    String valueStr = reco.get(i);

                    double value;
                    try {
                        value = Double.parseDouble(valueStr.trim());//double로 변환
                    } catch (NumberFormatException e) {
                        System.err.println("숫자로 변형 시켜 주세요: " + valueStr);
                        continue; // Skip this entry if the value is not a valid number
                    }

                    // 값추가
                    dataset.addValue(value, "score", cat[i] + (i + 1));
                }
            } else {
               JOptionPane.showMessageDialog(null, "로그인을 해주세요");
            }

        } catch (SQLException e) {
            
        }

        return dataset;//값반환
    }
}
