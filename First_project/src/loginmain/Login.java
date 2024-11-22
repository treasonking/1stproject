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
					// �߾� ��ġ
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

		// ũ�� ����
		setResizable(false);
		// LoginDAO ��ü ����
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
		join.setFont(new Font("������� ExtraBold", Font.PLAIN, 15));
		join.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {// ȸ������ �������� �̵�
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
		
		
		txtId.setFont(new Font("����", Font.PLAIN, 28));
		txtId.setColumns(10);
		txtId.setBounds(382, 240, 300, 52);
		contentPane.add(txtId);

		JLabel lblNewLabel_1 = new JLabel("\uC544\uC774\uB514 : ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("���� ���", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(270, 212, 114, 124);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("\uBE44\uBC00\uBC88\uD638 : ");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("���� ���", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(270, 344, 114, 76);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setForeground(new Color(255, 0, 0));
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("����", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(381, 404, 458, 28);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_2_1.setFont(new Font("����", Font.PLAIN, 11));
		lblNewLabel_2_1.setBounds(382, 302, 300, 28);
		contentPane.add(lblNewLabel_2_1);

		JButton login = new JButton("\uB85C\uADF8\uC778");
		login.setFont(new Font("������� ExtraBold", Font.PLAIN, 15));
		login.setForeground(new Color(255, 255, 255));
		login.setBackground(new Color(0, 0, 160));
		login.setBounds(419, 442, 170, 50);
		contentPane.add(login);

		JButton join_1 = new JButton("\uC544\uC774\uB514 \uCC3E\uAE30");
		join_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {// ���̵� ã��
				dispose();
				setVisible(false);
				new IDFind().setVisible(true);
			}
		});
		join_1.setForeground(Color.BLACK);
		join_1.setFont(new Font("������� ExtraBold", Font.PLAIN, 15));
		join_1.setBackground(new Color(255, 255, 255));
		join_1.setBounds(433, 532, 126, 36);
		contentPane.add(join_1);

		JButton join_2 = new JButton("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		join_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {// ��й�ȣ ã��
				dispose();
				setVisible(false);
				new PwFind().setVisible(true);
			}
		});
		join_2.setForeground(Color.BLACK);
		join_2.setFont(new Font("������� ExtraBold", Font.PLAIN, 15));
		join_2.setBackground(new Color(255, 255, 255));
		join_2.setBounds(560, 532, 137, 36);
		contentPane.add(join_2);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("����", Font.PLAIN, 28));
		passwordField.setBounds(382, 345, 300, 52);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(Login.class.getResource("/background/login1.jpg")));
		lblNewLabel_3.setBounds(0, 0, 999, 746);
		contentPane.add(lblNewLabel_3);

		ActionListener loginAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lblNewLabel_2.setText("");// �����޼��� �ʱ�ȭ
					lblNewLabel_2_1.setText("");
					String id = txtId.getText();// ���̵�
					String pw = new String(passwordField.getPassword());// ��й�ȣ
					boolean b1 = ldao.insertLogin(id, pw);// ���̵� ��й�ȣ �´��� üũ

					if (b1) {// ������
						JOptionPane.showMessageDialog(null, "�α��� ����!!!");
						dispose();
						setVisible(false);
						new Mainpage(txtId.getText(), 0, 0, 0, 0,0,0,0,0).setVisible(true);
					} else {// Ʋ�����
						if (txtId.getText().equals("") || passwordField.getText().equals("")) {// ���̵� Ȥ�� ��й�ȣ ������� ���
							if (txtId.getText().equals("") && passwordField.getText().equals("")) {// �Ѵ� ������� ��
								lblNewLabel_2_1.setText("���̵� �Է����ּ���");
								lblNewLabel_2.setText("��й�ȣ�� �Է����ּ���");
								txtId.requestFocus();// Ŀ�� ���� �̵�
							} else if (txtId.getText().equals("")) {// ���̵� ������� ���
								lblNewLabel_2_1.setText("���̵� �Է����ּ���");
								txtId.requestFocus();
							} else if (passwordField.getText().equals("")) {// ��й�ȣ�� ������� ���
								lblNewLabel_2.setText("��й�ȣ�� �Է����ּ���");
								passwordField.requestFocus();// Ŀ�� ���� �̵�
							}
						} else {// ��й�ȣ Ȥ�� ���̵� ��ġ ���� ���� ���
							JOptionPane.showMessageDialog(null, "�α��� ����, ���̵�� ��й�ȣ�� Ȯ�����ּ���");
							lblNewLabel_2.setText("���̵� �Ǵ� ��й�ȣ�� �߸� �Է��߽��ϴ�, �ٽ� Ȯ�����ּ���.");
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
