package display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import mainpage.Mainpage;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class ElecDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField txtFdsfds;
	private String message = "";
	private Timer timer;
	private JButton btnNewButton_1;
	private JTextField textField;
	private ActionListener enterAction;
	private JButton btnNewButton;
	private JButton btnNewButton_3;
	private int fontcolor = 1;
	private int displayspeed = 900;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ElecDisplay frame = new ElecDisplay("userId", 0, 0, 0, 0, 0, 0, 0, 0);
					frame.setVisible(true);
					// 화면 중앙에 띄우기
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
	public ElecDisplay(String id, double sum5, int score, int cnt, int cnt1, int like0, int like1, int like2,
			int like3) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ElecDisplay.class.getResource("/image/TV.png")));
		setTitle("\uC751\uC6D0\uC804\uAD11\uD310\r\n");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 762);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		// 화면 중앙에 띄우기
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

		JButton btnNewButton_4 = new JButton(""); // color 버튼
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (timer != null) {
					switch (fontcolor) {
					case 1:
						txtFdsfds.setForeground(Color.red);
						break;
					case 2:
						txtFdsfds.setForeground(Color.yellow);
						break;
					case 3:
						txtFdsfds.setForeground(Color.green);
						break;
					case 4:
						txtFdsfds.setForeground(Color.orange);
						break;
					case 5:
						txtFdsfds.setForeground(Color.white);
						break;
					case 6:
						txtFdsfds.setForeground(Color.magenta);
						break;
					case 7:
						fontcolor = 0;
					case 0:
						txtFdsfds.setForeground(Color.blue);
						break;
					}
					fontcolor++;
				}
			}
		});
		btnNewButton_4.setOpaque(false);
		btnNewButton_4.setContentAreaFilled(false);
		btnNewButton_4.setBorderPainted(false);
		btnNewButton_4.setFocusPainted(false);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JButton btnNewButton_5 = new JButton(""); // blink 버튼
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (timer != null) {
					timer.cancel();
					txtFdsfds.setText("");
					txtFdsfds.setForeground(Color.blue);
					fontcolor = 1;
				}

			}
		});

		JButton btnNewButton_6_1 = new JButton(""); // + 버튼
		btnNewButton_6_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (timer != null) {
					if (displayspeed > 100) {
						displayspeed -= 200;
						timer.cancel();
						startDisplayingMessage(displayspeed);
					}
				}
			}
		});
		btnNewButton_6_1.setBounds(391, 460, 53, 54);
		btnNewButton_6_1.setOpaque(false);
		btnNewButton_6_1.setContentAreaFilled(false);
		btnNewButton_6_1.setBorderPainted(false);
		btnNewButton_6_1.setFocusPainted(false);
		contentPane.add(btnNewButton_6_1);

		JButton btnNewButton_6 = new JButton(""); // - 버튼
		btnNewButton_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (timer != null) {
					if (displayspeed < 2000) {
						displayspeed += 200;
						timer.cancel();
						startDisplayingMessage(displayspeed);
					}
				}

			}
		});
		btnNewButton_6.setBounds(312, 460, 53, 54);
		btnNewButton_6.setOpaque(false);
		btnNewButton_6.setContentAreaFilled(false);
		btnNewButton_6.setBorderPainted(false);
		btnNewButton_6.setFocusPainted(false);
		contentPane.add(btnNewButton_6);

		btnNewButton_5.setOpaque(false);
		btnNewButton_5.setContentAreaFilled(false);
		btnNewButton_5.setBorderPainted(false);
		btnNewButton_5.setFocusPainted(false);
		btnNewButton_5.setBounds(153, 521, 53, 54);
		contentPane.add(btnNewButton_5);
		btnNewButton_4.setBounds(75, 521, 53, 54);
		contentPane.add(btnNewButton_4);

		JButton btnNewButton_3 = new JButton(""); // stop 버튼
		btnNewButton_3.setOpaque(false);
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.setFocusPainted(false);
		btnNewButton_3.setBounds(231, 460, 53, 54);
		contentPane.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (timer != null) {
					timer.cancel();

				}

			}
		});

		txtFdsfds = new JTextField();
		txtFdsfds.setEditable(false);
		txtFdsfds.setForeground(Color.BLUE);
		txtFdsfds.setFont(new Font("궁서체", Font.PLAIN, 95));
		txtFdsfds.setBackground(Color.BLACK);
		txtFdsfds.setHorizontalAlignment(SwingConstants.CENTER);
		txtFdsfds.setBounds(44, 132, 425, 248);
		contentPane.add(txtFdsfds);
		txtFdsfds.setColumns(10);

		btnNewButton_1 = new JButton("");
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setIcon(null);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {// 뒤로가기
				dispose();
				setVisible(false);
				new Mainpage(id, sum5, score, cnt, cnt1, like0, like1, like2, like3).setVisible(true);

			}
		});
		btnNewButton_1.setBounds(52, 607, 53, 54);
		contentPane.add(btnNewButton_1);

		SwingUtilities.invokeLater(new Runnable() {// 첫 실행시 강제 커서 이동
			public void run() {
				textField.requestFocus();
			}
		});

		textField = new JTextField(); // 메시지 입력 부분
		enterAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String input = textField.getText();
				if (input != null && !input.isEmpty()) {
					message = input;
					startDisplayingMessage(displayspeed);
				}

			}
		};

		textField.addActionListener(enterAction);

		textField.setFont(new Font("굴림", Font.BOLD, 17));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(54, 399, 408, 38);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setBorder(null);
		JButton btnNewButton_1_1 = new JButton(""); // play 버튼
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String input = textField.getText();
				if (input != null && !input.isEmpty()) {
					message = input;
					startDisplayingMessage(displayspeed);
				}

			}
		});
		btnNewButton_1_1.setOpaque(false);
		btnNewButton_1_1.setFocusPainted(false);
		btnNewButton_1_1.setContentAreaFilled(false);
		btnNewButton_1_1.setBorderPainted(false);
		btnNewButton_1_1.setBounds(408, 607, 53, 54);
		contentPane.add(btnNewButton_1_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ElecDisplay.class.getResource("/background/display2.png")));
		lblNewLabel.setBounds(0, 0, 725, 723);
		contentPane.add(lblNewLabel);

	}

	private void startDisplayingMessage(int speed) {// 전광판 속도 조정

		if (timer != null) // 중복 방지
			timer.cancel();

		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			public void run() {

				message = message.substring(1) + message.charAt(0);
				txtFdsfds.setText(message);

			}
		}, 0, speed);

	}
}
