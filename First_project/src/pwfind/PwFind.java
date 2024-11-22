package pwfind;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import loginDAO.LoginDAO;
import loginVO.LoginVO;
import loginmain.Login;
import winnlose.WinDrawLose;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class PwFind extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private LoginDAO ldao;
	private JTextArea textArea;
	private int cnt = 0;
	private ActionListener pwfindAction;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PwFind frame = new PwFind();
					frame.setVisible(true);
					// 화면 중앙에 배치
					Dimension frameSize = frame.getSize();
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					frame.setLocation((screenSize.width - frameSize.width) / 2,
							(screenSize.height - frameSize.height) / 2);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PwFind() {
		// 크기 고정
		setResizable(false);

		try {// LoginDAO연결
			ldao = new LoginDAO();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1015, 795);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		// 화면 중앙에 배치
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

		textField = new JTextField();
		textField.setFont(new Font("굴림", Font.PLAIN, 30));
		textField.setBounds(375, 258, 316, 39);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("굴림", Font.PLAIN, 30));
		textField_1.setColumns(10);
		textField_1.setBounds(375, 336, 317, 39);
		contentPane.add(textField_1);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setForeground(new Color(255, 0, 0));
		lblNewLabel_4.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(409, 299, 433, 27);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_4_1 = new JLabel("");
		lblNewLabel_4_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_4_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_4_1.setBounds(409, 385, 433, 27);
		contentPane.add(lblNewLabel_4_1);
		JButton btnNewButton = new JButton("\uB2E4\uC74C");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 0, 160));
		SwingUtilities.invokeLater(new Runnable() {// 첫 실행시 강제 커서 이동
			public void run() {
				textField.requestFocus();
			}
		});
		JLabel lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(605, 93, 150, 50);
		contentPane.add(lblNewLabel_3);
		lblNewLabel_3.setText("비밀번호 : ");
		lblNewLabel_3.setVisible(false);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(773, 93, 214, 308);
		scrollPane.setVisible(false);
		contentPane.add(scrollPane);
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("굴림", Font.PLAIN, 26));
		textArea.setRows(0);
		textArea.setEditable(false);
		textArea.setVisible(false);
		ActionListener pwfindAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				lblNewLabel_4.setText("");// 오류메세지 초기화
				lblNewLabel_4_1.setText("");// 오류메세지 초기화
				String id = textField.getText();// 아이디
				String name = textField_1.getText();// 이름
				if(id.equals("")&&name.equals("")) {
					lblNewLabel_4.setText("아이디를 입력 하세요.");
					lblNewLabel_4_1.setText("이름을 입력 하세요.");
					textField.requestFocus();
					if(cnt==1) {//한번 텍스트 에어리어가 떴을경우 없앰
						
						textArea.setVisible(false);
						cnt=0;
					}
				}
				else if(id.equals("")) {//아이디 입력칸이 비어있을 때
					lblNewLabel_4.setText("아이디를 입력 하세요.");
					textField.requestFocus();
					if(cnt==1) {//한번 텍스트 에어리어가 떴을경우 없앰
						
						textArea.setVisible(false);
						cnt=0;
					}
				}
				else if(name.equals("")) {//이름 입력칸이 비어있을 때
					textField_1.requestFocus();
					lblNewLabel_4_1.setText("이름을 입력 하세요.");
					if(cnt==1) {//한번 텍스트 에어리어가 떴을경우 없앰
						textArea.setVisible(false);
						cnt=0;
					}
				}
				if (!id.equals("") && !name.equals("")) {// 아이디,이름 입력칸이 채워져있을 때
					cnt = 1;// 텍스트 에어리어가 한번뜬걸로 간주
					scrollPane.setVisible(true);
					textArea.setVisible(true);
					lblNewLabel_3.setVisible(true);
					try {
						String pw = ldao.find_pw(id, name);// 비밀번호를 조회해봄

						textArea.append(pw);// 비밀번호를 텍스트 에어리어를 띄움
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
					}
				}

				// }
			}
		};

		btnNewButton.addActionListener(pwfindAction);
		textField_1.addActionListener(pwfindAction);

		btnNewButton.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 15));
		btnNewButton.setBounds(430, 431, 170, 50);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setFocusPainted(false);

		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {// 뒤로가기(로그인 화면을 중앙에 띄우기 위해서login.setLocationRelativeTo(null);를 씀)
				Login login = new Login();
				login.setLocationRelativeTo(null);
				dispose();
				setVisible(false);
				login.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 20));
		btnNewButton_1.setIcon(new ImageIcon("./src/ImageIcon/back.png"));
		btnNewButton_1.setBounds(274, 119, 67, 37);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(PwFind.class.getResource("/background/pwfind3.jpg")));
		lblNewLabel_5.setBounds(1, 0, 998, 756);
		contentPane.add(lblNewLabel_5);
		
	}
}
