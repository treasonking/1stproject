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
					// ȭ���� �߾ӿ� ���
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
		// ũ�� ����
		setResizable(false);
		// LoginDAO��ü ����
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
		// ȭ���� �߾ӿ� ���
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

		textField = new JTextField();
		textField.setFont(new Font("����", Font.PLAIN, 28));
		textField.setBounds(353, 256, 335, 40);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("����", Font.PLAIN, 28));
		textField_1.setColumns(10);
		textField_1.setBounds(398, 336, 290, 40);
		contentPane.add(textField_1);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setForeground(new Color(255, 0, 0));
		lblNewLabel_4.setFont(new Font("����", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(367, 306, 433, 20);
		contentPane.add(lblNewLabel_4);
		SwingUtilities.invokeLater(new Runnable() {// ù ����� ���� Ŀ�� �̵�
			public void run() {
				textField.requestFocus();
			}
		});
		JLabel lblNewLabel_4_1 = new JLabel("");
		lblNewLabel_4_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_4_1.setFont(new Font("����", Font.PLAIN, 15));
		lblNewLabel_4_1.setBounds(367, 386, 433, 32);
		contentPane.add(lblNewLabel_4_1);
		JLabel lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setFont(new Font("���� ���", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(634, 97, 123, 40);
		contentPane.add(lblNewLabel_3);
		lblNewLabel_3.setText("���̵� : ");
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(769, 98, 218, 299);
		contentPane.add(scrollPane);
		// ���̵� ǥ���ϴ� ����
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("����", Font.PLAIN, 26));
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
				// ���� �޼��� �ʱ�ȭ
				lblNewLabel_4.setText("");
				lblNewLabel_4_1.setText("");
				textArea.setText("");
				String name = textField.getText();// �̸�
				String birthday = textField_1.getText();// �������
				if(birthday.equals("")&&name.equals("")) {
					lblNewLabel_4.setText("�̸��� �Է� �ϼ���.");
					lblNewLabel_4_1.setText("��������� �Է� �ϼ���.");
					textField.requestFocus();
					if(cnt==1) {//�ؽ�Ʈ ���� �����������
						textArea.setVisible(false);
						cnt=0;
					}
				}
				else if(name.equals("")) {//�̸��� �����϶�
					textField.requestFocus();
					lblNewLabel_4.setText("�̸��� �Է� �ϼ���.");
					if(cnt==1) {//�ؽ�Ʈ ���� �����������
						textArea.setVisible(false);
						cnt=0;
					}
				}
				else if(birthday.equals("")) {//��������� �����϶�
					textField_1.requestFocus();
					lblNewLabel_4_1.setText("��������� �Է� �ϼ���.");
					if(cnt==1) {//�ؽ�Ʈ ���� �����������
						textArea.setVisible(false);
						cnt=0;
					}
				}
				if (!birthday.equals("") && !name.equals("")) {// �Ѵ� ���������
					cnt = 1;// ���� �ϳ��� ������ �Ǹ� ���̵� ǥ���ϴ� �ؽ�Ʈ â�� ��������
					// ���̵� ǥ�� ��
					lblNewLabel_3.setVisible(true);
					scrollPane.setVisible(true);
					textArea.setVisible(true);
					try {
						List<String> id = ldao.find_id(name, birthday);// ���̵� ��ȸ
						String ids = String.join("\n", id);// ����Ʈ�� �޾ƿͼ� ���ڿ��� ��ȯ�� ids�� ����
						textArea.append(ids);// textArea�� ��ȸ�� ���̵� �߰�
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "�ùٸ� ���� �Է����ּ���");
					}
				}

				// }
			}
		};

		btnNewButton.addActionListener(idfindAction);
		textField_1.addActionListener(idfindAction);

		btnNewButton.setFont(new Font("������� ExtraBold", Font.PLAIN, 15));
		btnNewButton.setBounds(428, 446, 170, 50);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {// �ڷΰ���(�α��� ȭ�� ��� ����)
				Login loginFrame = new Login();
				loginFrame.setLocationRelativeTo(null);
				dispose();
				setVisible(false);
				loginFrame.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("����", Font.PLAIN, 24));
		btnNewButton_1.setIcon(new ImageIcon("./src/ImageIcon/back.png"));
		btnNewButton_1.setBounds(253, 106, 89, 55);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(IDFind.class.getResource("/background/loginfind1.jpg")));
		lblNewLabel_5.setBounds(0, 0, 999, 756);
		contentPane.add(lblNewLabel_5);

	}
}
