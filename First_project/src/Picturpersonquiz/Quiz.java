package Picturpersonquiz;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import jaco.mp3.player.*;
import mainpage.Mainpage;
import newpaper.SoundThread;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.Color;

public class Quiz extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel_1;
	private SoundThread soundThread;
	private int cnt = 0;
	private int score = 0;
	private boolean isPlaying = false;
	private JLabel lblNewLabel_2;

	// ������ ���� �迭 (����ڰ� ������ �����ؾ� ��)
	private String[] answers = { "�̴�ȣ", "�����", "�迬��", "�ڼ���", "������" };

	// ������ �̹��� ��� �迭 (����ڰ� �̹��� ��θ� �����ؾ� ��)
	private String[] imagePaths = { "./src/img/soccer2.jpg", "./src/img/vb.jpg", "./src/img/gol.jpg", "./src/img/sk.jpg",
			// ���� �̹����� ������ ���������� ������Ʈ���� ����
			"D:\\default_image.jpg" };

	private String[] soundPaths = { "./src/img/�̴�ȣ.mp3", "./src/img/�����.mp3", "./src/img/�迬��.mp3", "./src/img/�ڼ���.mp3",
			"./src/img/������.mp3"//������ ��Ʈ tts

	};

	// Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Quiz frame = new Quiz("userId", 0,0,0,0,0,0);
					frame.setVisible(true);
					//ȭ�� �߾� ǥ��
					Dimension frameSize = frame.getSize();
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the frame.
	public Quiz(String id, double sum5,int cnt1,int like0,int like1,int like2,int like3) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 601, 870);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//ȭ�� �߾� ǥ��
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				playMusic(cnt); // ���� ��ȣ�� ���� ���� ���
			}
		});
		btnNewButton.setFont(new Font("������� ExtraBold", Font.BOLD, 25));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(455, 558, 73, 69);
		contentPane.add(btnNewButton);

		textField = new JTextField();
		textField.setBackground(new Color(192, 192, 192));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("������� ExtraBold", Font.BOLD, 19));
		textField.setBounds(152, 542, 292, 57);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setBorder(null);
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(Quiz.class.getResource("/img/baseball1.png"))); // �ʱ� �̹��� ����
		lblNewLabel_1.setBounds(81, 102, 445, 394);
		contentPane.add(lblNewLabel_1);
		
		SwingUtilities.invokeLater(new Runnable() {//ù ����� ���� Ŀ�� �̵�
	        public void run() {
	        	textField.requestFocus();
	        }
	    });
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFocusPainted(false);
		ActionListener answerAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {// ���� ��
				String answer;
				answer = textField.getText();
				textField.requestFocus();
				if (!answer.trim().isEmpty()) // ������� ������ ������ ����(isEmpty()����ִ��� Ȯ��)(trim()�� �����)
					checkAnswer();
				else// ������� ���
					JOptionPane.showMessageDialog(null, "���� �Է��ϼ���.");
			}

		};
		textField.addActionListener(answerAction);

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {//�ڷΰ���
				dispose();
				setVisible(false);
				new Mainpage(id, sum5, score,cnt1,1,like0,like1,like2,like3).setVisible(true);//quiz�������� 1����ȯ
				if (soundThread != null)
					soundThread.stopRunning();
			}
		});
		btnNewButton_2.setBounds(74, 557, 66, 70);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.setOpaque(false);
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setFocusPainted(false);
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Quiz.class.getResource("/background/quiz4.png")));
		lblNewLabel_2.setBounds(0, 0, 585, 836);
		contentPane.add(lblNewLabel_2);

	}

	// ���� Ȯ�� �� ���� ó�� �޼���
	private void checkAnswer() {
		String answer = textField.getText();
		String correctAnswer = answers[cnt]; // ���� ������ ���� ��������
		if (answer.trim().equalsIgnoreCase(correctAnswer)) {
			score++; // ������ ��� ���� ����
			JOptionPane.showMessageDialog(null, "�����Դϴ�.");
			updateImage(cnt); // �̹��� ������Ʈ
			cnt++;
			if (soundThread != null)//Ȯ�ι�ư �������� ��Ʈ ��� ����
				soundThread.stopRunning();
		} else {
			JOptionPane.showMessageDialog(null, "�����Դϴ�.");
			updateImage(cnt); // �̹��� ������Ʈ
			cnt++;
			if (soundThread != null)//Ȯ�ι�ư �������� ��Ʈ ��� ����
				soundThread.stopRunning();
		}
		textField.setText(""); // �Է� �ʵ� �ʱ�ȭ

		// ���� ������ �Ѿ�� ���� cnt ����

		// ��� ������ Ǭ ��� ������ ���
		if (cnt >= answers.length) {
			JOptionPane.showMessageDialog(null, "��� �������ϴ�.\n����� ������ " + score + "���Դϴ�.");
		}
	}

	// �ش� ���� ��ȣ�� ���� ���� ��� �޼���
	private void playMusic(int index) {

		toggleMusic(soundPaths[index]);

	}

	// �ش� ���� ��ȣ�� ���� �̹��� ������Ʈ �޼���
	private void updateImage(int index) {
		if (index < imagePaths.length) {//���� ���� ��ȣ�� �̹��� ��� �迭���� ���̰� ���� ��� ���� ��ȣ�� �̹� ��� �迭 ������ ����
			lblNewLabel_1.setIcon(new ImageIcon(imagePaths[index]));//�׷� �̹��� ��� �迭�� ������ȣ ��°�� ã�ư�
		}
	}

	private void toggleMusic(String musicPath) {//������� ���� �غ��� ���� ��
		if (isPlaying) {
			if (soundThread != null) {
				soundThread.stopRunning();
				soundThread = null;
			}
			isPlaying = false;
		} else {
			soundThread = new SoundThread(musicPath);
			soundThread.start();
			isPlaying = true;
		}
	}
}
