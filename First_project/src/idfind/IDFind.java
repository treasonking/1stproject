package idfind;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import loginDAO.LoginDAO;
import loginVO.LoginVO;
import loginmain.Login;
import pwfind.PwFind;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;

public class IDFind extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private LoginDAO ldao;
	private JTextArea textArea;
	private int cnt = 0;
	private ActionListener idfindAction;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IDFind frame = new IDFind();
					frame.setVisible(true);
					// 화면을 중앙에 띄움
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
	public IDFind() {
		// 크기 고정
		setResizable(false);
		// LoginDAO객체 생성
		try {
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
		// 화면을 중앙에 띄움
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

		textField = new JTextField();
		textField.setFont(new Font("굴림", Font.PLAIN, 28));
		textField.setBounds(353, 256, 335, 40);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("굴림", Font.PLAIN, 28));
		textField_1.setColumns(10);
		textField_1.setBounds(398, 336, 290, 40);
		contentPane.add(textField_1);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setForeground(new Color(255, 0, 0));
		lblNewLabel_4.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(367, 306, 433, 20);
		contentPane.add(lblNewLabel_4);
		SwingUtilities.invokeLater(new Runnable() {// 첫 실행시 강제 커서 이동
			public void run() {
				textField.requestFocus();
			}
		});
		JLabel lblNewLabel_4_1 = new JLabel("");
		lblNewLabel_4_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_4_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_4_1.setBounds(367, 386, 433, 32);
		contentPane.add(lblNewLabel_4_1);
		JLabel lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(634, 97, 123, 40);
		contentPane.add(lblNewLabel_3);
		lblNewLabel_3.setText("아이디 : ");
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(769, 98, 218, 299);
		contentPane.add(scrollPane);
		// 아이디 표시하는 공간
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("굴림", Font.PLAIN, 26));
		textArea.setRows(0);
		textArea.setEditable(false);
		lblNewLabel_3.setVisible(false);
		scrollPane.setVisible(false);
		textArea.setVisible(false);
		JButton btnNewButton = new JButton("\uB2E4\uC74C");
		btnNewButton.setBackground(new Color(0, 0, 160));
		btnNewButton.setForeground(new Color(255, 255, 255));
		idfindAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 오류 메세지 초기화
				lblNewLabel_4.setText("");
				lblNewLabel_4_1.setText("");
				textArea.setText("");
				String name = textField.getText();// 이름
				String birthday = textField_1.getText();// 생년월일
				if(birthday.equals("")&&name.equals("")) {
					lblNewLabel_4.setText("이름을 입력 하세요.");
					lblNewLabel_4_1.setText("생년월일을 입력 하세요.");
					textField.requestFocus();
					if(cnt==1) {//텍스트 에어리어가 띄워져있으면
						textArea.setVisible(false);
						cnt=0;
					}
				}
				else if(name.equals("")) {//이름이 공백일때
					textField.requestFocus();
					lblNewLabel_4.setText("이름을 입력 하세요.");
					if(cnt==1) {//텍스트 에어리어가 띄워져있으면
						textArea.setVisible(false);
						cnt=0;
					}
				}
				else if(birthday.equals("")) {//생년월일이 공백일때
					textField_1.requestFocus();
					lblNewLabel_4_1.setText("생년월일을 입력 하세요.");
					if(cnt==1) {//텍스트 에어리어가 띄워져있으면
						textArea.setVisible(false);
						cnt=0;
					}
				}
				if (!birthday.equals("") && !name.equals("")) {// 둘다 비어있을때
					cnt = 1;// 둘중 하나가 공백이 되면 아이디 표시하는 텍스트 창을 지워버림
					// 아이디 표시 라벨
					lblNewLabel_3.setVisible(true);
					scrollPane.setVisible(true);
					textArea.setVisible(true);
					try {
						List<String> id = ldao.find_id(name, birthday);// 아이디 조회
						String ids = String.join("\n", id);// 리스트로 받아와서 문자열로 변환후 ids에 저장
						textArea.append(ids);// textArea에 조회한 아이디 추가
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "올바른 값을 입력해주세요");
					}
				}

				// }
			}
		};

		btnNewButton.addActionListener(idfindAction);
		textField_1.addActionListener(idfindAction);

		btnNewButton.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 15));
		btnNewButton.setBounds(428, 446, 170, 50);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {// 뒤로가기(로그인 화면 가운데 띄우기)
				Login loginFrame = new Login();
				loginFrame.setLocationRelativeTo(null);
				dispose();
				setVisible(false);
				loginFrame.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 24));
		btnNewButton_1.setIcon(new ImageIcon("./src/ImageIcon/back.png"));
		btnNewButton_1.setBounds(253, 106, 89, 55);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(IDFind.class.getResource("/background/loginfind1.jpg")));
		lblNewLabel_5.setBounds(0, 0, 999, 756);
		contentPane.add(lblNewLabel_5);

	}
}
