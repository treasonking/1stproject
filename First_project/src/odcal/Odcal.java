package odcal;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mainpage.Mainpage;
import myinfo.MyInfo;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Odcal extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField textField_2;
	private JLabel lblNewLabel_3;
	private JTextArea textArea;
	private JButton btnNewButton_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private ActionListener calcuAction;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Odcal frame = new Odcal("userId", 0, 0, 0, 0,0,0,0,0);
					frame.setVisible(true);
					// ȭ�� �߾� ��ġ
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
	public Odcal(String id, double sum5, int score, int cnt, int cnt1,int like0,int like1,int like2,int like3) {// ����ؼ� �������� ���̵� �Ѱ��ֱ�����
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 874);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		// ȭ�� �߾� ��ġ
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

		textField = new JTextField();// ����
		textField.setFont(new Font("����", Font.PLAIN, 35));

		textField.setBounds(19, 275, 282, 72);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setBorder(null);
		textField_1 = new JTextField();// �¸���
		textField_1.setFont(new Font("����", Font.PLAIN, 35));
		textField_1.setBorder(null);
		textField_1.setColumns(10);
		textField_1.setBounds(19, 368, 282, 72);
		contentPane.add(textField_1);

		lblNewLabel = new JLabel("\uC804\uCCB4 \uACBD\uAE30\uC218");
		lblNewLabel.setFont(new Font("������� ExtraBold", Font.PLAIN, 15));
		lblNewLabel.setBounds(29, 247, 139, 32);

		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\uC2B9\uB9AC \uC218");
		lblNewLabel_1.setFont(new Font("������� ExtraBold", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(19, 339, 139, 32);
		contentPane.add(lblNewLabel_1);

		textField_2 = new JTextField();// ���
		textField_2.setEditable(false);
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("������� ExtraBold", Font.PLAIN, 44));
		textField_2.setBounds(313, 284, 258, 247);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setBorder(null);
		lblNewLabel_3 = new JLabel("\uACB0\uACFC");
		lblNewLabel_3.setFont(new Font("������� ExtraBold", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(319, 235, 139, 57);
		contentPane.add(lblNewLabel_3);
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setBounds(148, 172, 282, 78);
		contentPane.add(comboBox);
		comboBox.setBorder(null);
		textField_3 = new JTextField();// �¸���

		textField_3.setFont(new Font("����", Font.PLAIN, 35));
		textField_3.setBounds(19, 370, 282, 66);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setBorder(null);
		textField_4 = new JTextField();// ���

		textField_4.setFont(new Font("����", Font.PLAIN, 35));// �й��
		textField_4.setBounds(19, 275, 282, 72);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		textField_4.setBorder(null);
		textField_5 = new JTextField();// ���º� ��

		textField_5.setFont(new Font("����", Font.PLAIN, 35));
		textField_5.setBounds(19, 458, 282, 73);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		textField_5.setBorder(null);
		lblNewLabel_7 = new JLabel("�й� ��");
		lblNewLabel_7.setFont(new Font("������� ExtraBold", Font.BOLD, 15));
		lblNewLabel_7.setBounds(23, 329, 139, 57);
		contentPane.add(lblNewLabel_7);
		lblNewLabel_8 = new JLabel("���º� ��");
		lblNewLabel_8.setFont(new Font("������� ExtraBold", Font.PLAIN, 15));
		lblNewLabel_8.setBounds(19, 421, 139, 57);
		contentPane.add(lblNewLabel_8);
		textField_3.setVisible(false);
		textField_4.setVisible(false);
		textField_5.setVisible(false);
		lblNewLabel_7.setVisible(false);// cg�� 2�϶� ������ ��
		lblNewLabel_8.setVisible(false);// cg�� 2�϶� ������ ��
		SwingUtilities.invokeLater(new Runnable() {// ù ����� ���� Ŀ�� �̵�
			public void run() {
				textField.requestFocus();
			}
		});
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// �� ǥ��
				int cg = comboBox.getSelectedIndex();
				try{
				if (cg == 0) {// ��� �ɼ� ����:��ü���� �¸���
					lblNewLabel.setText("��ü ��� ��");
					lblNewLabel_1.setText("�¸� ��");
					// �ؽ�Ʈ �ʵ忡 �ִ� ���� �ʱ�ȭ
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setVisible(false);
					textField_3.setEnabled(false);
					textField.setVisible(true);
					textField_1.setVisible(true);
					textField_4.setVisible(false);// cg�� 2�϶� ������ �ؽ�Ʈ �ʵ�
					textField_4.setEnabled(false);
					textField_5.setVisible(false);// cg�� 2�϶� ������ �ؽ�Ʈ �ʵ�
					textField_5.setEnabled(false);
					lblNewLabel_1.setVisible(true);
					lblNewLabel_7.setVisible(false);// cg�� 2�϶� ������ ��
					lblNewLabel_8.setVisible(false);// cg�� 2�϶� ������ ��

				} else if (cg == 1) {// ��� �ɼ� ����:�¸��� �й��
					lblNewLabel.setText("�¸� ��");
					lblNewLabel_1.setText("�й� ��");
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setVisible(false);// cg�� 2�϶� ������ �ؽ�Ʈ �ʵ�
					textField_3.setEnabled(false);// cg�� 2�϶� ������ �ؽ�Ʈ �ʵ�
					textField.setVisible(true);

					textField_1.setVisible(true);

					textField_4.setVisible(false);// cg�� 3�϶� ������ �ؽ�Ʈ �ʵ�
					textField_4.setEnabled(false);
					textField_5.setVisible(false);// cg�� 2�϶� ������ �ؽ�Ʈ �ʵ�
					textField_5.setEnabled(false);
					lblNewLabel_1.setVisible(true);
					lblNewLabel_7.setVisible(false);// cg�� 2�϶� ������ ��
					lblNewLabel_8.setVisible(false);// cg�� 2�϶� ������ ��

				}
				if (cg == 2) {// ��� �ɼ� ����:�¸� �� ���º� �� �й� ��

					
					textField_2.setText("");
					lblNewLabel.setText("�¸� ��");
					textField.setVisible(false);// cg�� 1�϶� ������ �ؽ�Ʈ �ʵ�
					textField_1.setVisible(false);// cg�� 1�϶� ������ �ؽ�Ʈ �ʵ�
					lblNewLabel_1.setVisible(false);// cg�� 1�϶� ������ �ؽ�Ʈ �ʵ�
					textField_3.setVisible(true);
					textField_3.setEnabled(true);
					textField_4.setVisible(true);// cg�� 3�϶� ������ �ؽ�Ʈ �ʵ�
					textField_4.setEnabled(true);
					textField_5.setVisible(true);// cg�� 2�϶� ������ �ؽ�Ʈ �ʵ�
					textField_5.setEnabled(true);
					lblNewLabel_1.setVisible(false);
					lblNewLabel_7.setVisible(true);// cg�� 2�϶� ������ ��
					lblNewLabel_8.setVisible(true);// cg�� 2�϶� ������ ��
					textField_5.addActionListener(calcuAction);
				}
			}catch(Exception e3) {
				
			}
			}
		});

		calcuAction = new ActionListener() {

			public void actionPerformed(ActionEvent e) {// ���
				try {

					int cg = comboBox.getSelectedIndex();// �޺� �ڽ����� ������ ��°(��:���� �����ִ� ��ü���� �¸����� �����ϸ� 0��)

					if (cg == 0) {// ��� �ɼ� ����:��ü���� �¸���
						double game_num = Integer.parseInt(textField.getText());// ����
						double win_num = Integer.parseInt(textField_1.getText());// �¸���
						double odds = (win_num / game_num) * 100.0;// ��� ��
						int odd = (int) odds;// ��� �� ������ ��ȯ
						textField_2.setText("�·� : " + odd + "%");
						textArea.append("�¸� ��/��� ��*100 : " + odd + "%\n");
					} else if (cg == 1) {// ��� �ɼ� ����:�¸��� �й��
						double win_num1 = Integer.parseInt(textField.getText());// �¸���
						double dfeat_num = Integer.parseInt(textField_1.getText());// �й��
						double odds = (win_num1 / (win_num1 + dfeat_num)) * 100;// ��� ��
						int odd = (int) odds;// ��� �� ������ ��ȯ
						textField_2.setText("�·� : " + odd + "%");
						textArea.append("�¸� ��/(�¸� ��+�й��)*100 = " + odd + "%\n");
					} else if (cg == 2) {// ��� �ɼ� ����:�¸��� �й��
						double win_num2 = Integer.parseInt(textField_3.getText());// �¸���
						double defeat_num1 = Integer.parseInt(textField_4.getText());// �й��
						double tie_num1 = Integer.parseInt(textField_5.getText());// ���º� ��
						double total = win_num2 + defeat_num1 + tie_num1;// �� ����
						double odds = ((win_num2 + 0.5 * tie_num1) / total) * 100;// ��갪
						int odd = (int) odds;// ��� �� ������ ��ȯ
						textField_2.setText("�·� : " + odd + "%");
						textArea.append("((�¼� + 0.5 �� ���º�) / ����)*100 = " + odd + "%\n");

					}

				} catch (Exception e1) {// ���ڰ� �ƴ� ���ڸ� �Է��ϰų� ��� ���� �ʹ� ũ�ų�

					
					JOptionPane.showMessageDialog(null, "��� �� ���� �Է� ���ּ���.");

				}
			}
		};
		textField_1.addActionListener(calcuAction);
		textField.addActionListener(calcuAction);
		textField_3.addActionListener(calcuAction);
		textField_4.addActionListener(calcuAction);
		textField_5.addActionListener(calcuAction);
		comboBox.setFont(new Font("������� ExtraBold", Font.PLAIN, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"       \uC804\uCCB4 \uACBD\uAE30\uC218\uC640 \uC2B9\uB960", "       \uC2B9\uB9AC\uC640 \uD328\uBC30 \uD69F\uC218", "       \uD300\uBCC4 \uC2B9\uC810\uACFC \uC2B9\uB960"}));

		btnNewButton_1 = new JButton("");
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {// �ڷΰ���
				dispose();
				setVisible(false);
				new Mainpage(id, sum5, score, cnt, cnt1,like0,like1,like2,like3).setVisible(true);
			}
		});
		btnNewButton_1.setBounds(19, 768, 64, 57);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(Odcal.class.getResource("/background/CAL1.png")));
		lblNewLabel_5.setBounds(0, 0, 1451, 842);
		contentPane.add(lblNewLabel_5);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 570, 135);
		contentPane.add(scrollPane);
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setFont(new Font("����", Font.PLAIN, 20));

	}
}
