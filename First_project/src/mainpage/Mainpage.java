package mainpage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Calendar.Diary;
import Picturpersonquiz.Quiz;
import display.ElecDisplay;
import loginDAO.LoginDAO;
import loginmain.Login;
import myinfo.MyInfo;
import newpaper.NewPaper;
import odcal.Odcal;
import winnlose.WinDrawLose;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Mainpage extends JFrame {

	private JPanel contentPane;
	private Login login;
	private LoginDAO ldao;
	private String userId;
	
	public static int viewCount;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainpage frame = new Mainpage("Guest",0,0,0,0,0,0,0,0);
					frame.setVisible(true);
					
					//중앙에 화면 띄우기
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
	public Mainpage(String id,double sum5,int score,int cnt,int cnt1,int like0,int like1,int like2,int like3) {
		setFont(new Font("Microsoft PhagsPa", Font.PLAIN, 17));
		setTitle("Sports Feed");
		
		//크기 고정
		setResizable(false);
		
		this.userId = id;//로그인으로부터 아이디를 받아 옴
        try {//LoginDAO와 연결
            ldao = new LoginDAO();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
			viewCount=ldao.newsdate(id, viewCount);//초기값을 데이터베이스에 조회해서 넣음
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 990, 795);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		 setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
		JButton btnMyPage = new JButton("MyPage"); 
		btnMyPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		 btnMyPage.setOpaque(false);
	        btnMyPage.setContentAreaFilled(false);
	        btnMyPage.setBorderPainted(false);
	        btnMyPage.setFocusPainted(false);

		btnMyPage.setBackground(new Color(255, 255, 255));
		btnMyPage.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		btnMyPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {//내정보로 이동

				dispose();
                setVisible(false);
                new MyInfo(id,viewCount,sum5,score,cnt,cnt1,like0,like1,like2,like3).setVisible(true);
			}
		});
		btnMyPage.setBounds(802, 10, 146, 37);
		contentPane.add(btnMyPage);
		
		JButton btnQuiz = new JButton("QUIZ"); // 인물맞추기 페이지로 이동
		btnQuiz.setOpaque(false);
        btnQuiz.setContentAreaFilled(false);
        btnQuiz.setBorderPainted(false);
        btnQuiz.setFocusPainted(false);

		btnQuiz.setBackground(new Color(220, 220, 220));
		
		btnQuiz.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		btnQuiz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				setVisible(false);
				new Quiz(id,sum5,cnt,like0,like1,like2,like3).setVisible(true);
			}
		});
		btnQuiz.setBounds(94, 560, 81, 37);
		contentPane.add(btnQuiz);
		
		JButton btnGame = new JButton("GAME"); // 승무패게임 페이지로 이동
		 btnGame.setOpaque(false);
		 btnGame.setContentAreaFilled(false);
		 btnGame.setBorderPainted(false);
	     btnGame.setFocusPainted(false);

		btnGame.setBackground(new Color(220, 220, 220));
		btnGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGame.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		btnGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
                setVisible(false);
                new WinDrawLose(id,score,cnt1,like0,like1,like2,like3).setVisible(true);
			}
		});
		btnGame.setBounds(174, 560, 101, 37);
		contentPane.add(btnGame);
		
		JButton btnScore = new JButton("CALCULATOR"); // 계산기 페이지로 이동
		 btnScore.setOpaque(false);
	        btnScore.setContentAreaFilled(false);
	        btnScore.setBorderPainted(false);
	        btnScore.setFocusPainted(false);

		btnScore.setBackground(new Color(220, 220, 220));
		btnScore.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		btnScore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
                setVisible(false);
                new Odcal(id,sum5,score,cnt,cnt1,like0,like1,like2,like3).setVisible(true);
			}
		});
		btnScore.setBounds(603, 630, 177, 37);
		contentPane.add(btnScore);
		
		JButton btnNews = new JButton("NEWS"); // 
		 btnNews.setOpaque(false);
	        btnNews.setContentAreaFilled(false);
	        btnNews.setBorderPainted(false);
	        btnNews.setFocusPainted(false);
	        btnNews.setBackground(Color.LIGHT_GRAY);

		btnNews.setBackground(new Color(220, 220, 220));
		btnNews.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		btnNews.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {//뉴스 페이지로 이동,이동할때마다 조회수 증가
				
				viewCount++;//조회수 1씩증가
				
				try {
					ldao.update_recordnews(id,viewCount);//조회수를 업데이트 함
					viewCount=ldao.newsdate(id,viewCount);//그리고 다시 조회수를 조회해서 집어넣음
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
                setVisible(false);
                new NewPaper(id,viewCount,sum5,score,cnt,cnt1,like0,like1,like2,like3).setVisible(true);
				
			}
		});
		btnNews.setBounds(810, 630, 101, 37);
		contentPane.add(btnNews);
		
		JButton btnCa = new JButton("CALENDAR");
		btnCa.setOpaque(false);
		btnCa.setContentAreaFilled(false);
		btnCa.setBorderPainted(false);
		btnCa.setFocusPainted(false);
		btnCa.setBackground(Color.LIGHT_GRAY);
		btnCa.setBackground(new Color(220, 220, 220));
		btnCa.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		btnCa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { // 경기일정 페이지
				dispose();
				setVisible(false);
				new Diary(id,sum5,score,cnt,cnt1,like0,like1,like2,like3).setVisible(true);;
			}
		});
		btnCa.setBounds(603, 560, 146, 37);
		contentPane.add(btnCa);
		
		JButton btnPanel = new JButton("DISPLAY BOARD"); 
		btnPanel.setOpaque(false);
		btnPanel.setContentAreaFilled(false);
		btnPanel.setBorderPainted(false);
		btnPanel.setFocusPainted(false);

		btnPanel.setBackground(new Color(220, 220, 220));
		btnPanel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		btnPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {// 전광판 페이지
				dispose();
                setVisible(false);
                new ElecDisplay(id,sum5,score,cnt,cnt1,like0,like1,like2,like3).setVisible(true);
			}
		});
		btnPanel.setBounds(763, 560, 199, 37);
		contentPane.add(btnPanel);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		lblNewLabel.setBounds(401, 10, 360, 37);
		contentPane.add(lblNewLabel);
		lblNewLabel.setText("ID : "+id);
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Mainpage.class.getResource("/background/main3.jpg")));
		lblNewLabel_1.setBounds(0, 0, 974, 756);
		contentPane.add(lblNewLabel_1);
	}
}
