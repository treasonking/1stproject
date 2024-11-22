package loginmain;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import idfind.IDFind;
import join.Join;
import loginDAO.LoginDAO;
import mainpage.Mainpage;
import myinfo.MyInfo;
import pwfind.PwFind;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

public class Login extends JFrame {

	private JPanel contentPane;
	private static JTextField txtId;
	private LoginDAO ldao;
	private JPasswordField passwordField;
	private List<String> a;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					// 중앙 배치
					Dimension frameSize = frame.getSize();
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					frame.setLocation((screenSize.width - frameSize.width) / 2,
							(screenSize.height - frameSize.height) / 2);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {

		// 크기 고정
		setResizable(false);
		// LoginDAO 객체 생성
		try {
			ldao = new LoginDAO();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1015, 779);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton join = new JButton("\uD68C\uC6D0\uAC00\uC785");
		join.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 15));
		join.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {// 회원가입 페이지로 이동
				dispose();
				setVisible(false);
				new Join().setVisible(true);
			}
		});
		join.setForeground(Color.BLACK);
		join.setBackground(new Color(255, 255, 255));
		join.setBounds(317, 532, 114, 36);
		contentPane.add(join);

		txtId = new JTextField();
		
		
		txtId.setFont(new Font("굴림", Font.PLAIN, 28));
		txtId.setColumns(10);
		txtId.setBounds(382, 240, 300, 52);
		contentPane.add(txtId);

		JLabel lblNewLabel_1 = new JLabel("\uC544\uC774\uB514 : ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(270, 212, 114, 124);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("\uBE44\uBC00\uBC88\uD638 : ");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(270, 344, 114, 76);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setForeground(new Color(255, 0, 0));
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(381, 404, 458, 28);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_2_1.setFont(new Font("굴림", Font.PLAIN, 11));
		lblNewLabel_2_1.setBounds(382, 302, 300, 28);
		contentPane.add(lblNewLabel_2_1);

		JButton login = new JButton("\uB85C\uADF8\uC778");
		login.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 15));
		login.setForeground(new Color(255, 255, 255));
		login.setBackground(new Color(0, 0, 160));
		login.setBounds(419, 442, 170, 50);
		contentPane.add(login);

		JButton join_1 = new JButton("\uC544\uC774\uB514 \uCC3E\uAE30");
		join_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {// 아이디 찾기
				dispose();
				setVisible(false);
				new IDFind().setVisible(true);
			}
		});
		join_1.setForeground(Color.BLACK);
		join_1.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 15));
		join_1.setBackground(new Color(255, 255, 255));
		join_1.setBounds(433, 532, 126, 36);
		contentPane.add(join_1);

		JButton join_2 = new JButton("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		join_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {// 비밀번호 찾기
				dispose();
				setVisible(false);
				new PwFind().setVisible(true);
			}
		});
		join_2.setForeground(Color.BLACK);
		join_2.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 15));
		join_2.setBackground(new Color(255, 255, 255));
		join_2.setBounds(560, 532, 137, 36);
		contentPane.add(join_2);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("굴림", Font.PLAIN, 28));
		passwordField.setBounds(382, 345, 300, 52);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(Login.class.getResource("/background/login1.jpg")));
		lblNewLabel_3.setBounds(0, 0, 999, 746);
		contentPane.add(lblNewLabel_3);

		ActionListener loginAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lblNewLabel_2.setText("");// 오류메세지 초기화
					lblNewLabel_2_1.setText("");
					String id = txtId.getText();// 아이디
					String pw = new String(passwordField.getPassword());// 비밀번호
					boolean b1 = ldao.insertLogin(id, pw);// 아이디 비밀번호 맞는지 체크

					if (b1) {// 맞으면
						JOptionPane.showMessageDialog(null, "로그인 성공!!!");
						dispose();
						setVisible(false);
						new Mainpage(txtId.getText(), 0, 0, 0, 0,0,0,0,0).setVisible(true);
					} else {// 틀릴경우
						if (txtId.getText().equals("") || passwordField.getText().equals("")) {// 아이디 혹은 비밀번호 비어있을 경우
							if (txtId.getText().equals("") && passwordField.getText().equals("")) {// 둘다 비어있을 때
								lblNewLabel_2_1.setText("아이디를 입력해주세요");
								lblNewLabel_2.setText("비밀번호를 입력해주세요");
								txtId.requestFocus();// 커서 강제 이동
							} else if (txtId.getText().equals("")) {// 아이디만 비어있을 경우
								lblNewLabel_2_1.setText("아이디를 입력해주세요");
								txtId.requestFocus();
							} else if (passwordField.getText().equals("")) {// 비밀번호만 비어있을 경우
								lblNewLabel_2.setText("비밀번호를 입력해주세요");
								passwordField.requestFocus();// 커서 강제 이동
							}
						} else {// 비밀번호 혹은 아이디가 일치 하지 않을 경우
							JOptionPane.showMessageDialog(null, "로그인 실패, 아이디와 비밀번호를 확인해주세요");
							lblNewLabel_2.setText("아이디 또는 비밀번호를 잘못 입력했습니다, 다시 확인해주세요.");
							passwordField.setText("");
							passwordField.requestFocus();
						}
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		};
		
		login.addActionListener(loginAction);
		passwordField.addActionListener(loginAction);
	}
}
