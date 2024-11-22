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

	// 문제별 정답 배열 (사용자가 정답을 설정해야 함)
	private String[] answers = { "이대호", "손흥민", "김연경", "박세리", "윤성빈" };

	// 문제별 이미지 경로 배열 (사용자가 이미지 경로를 설정해야 함)
	private String[] imagePaths = { "./src/img/soccer2.jpg", "./src/img/vb.jpg", "./src/img/gol.jpg", "./src/img/sk.jpg",
			// 다음 이미지는 마지막 문제에서는 업데이트하지 않음
			"D:\\default_image.jpg" };

	private String[] soundPaths = { "./src/img/이대호.mp3", "./src/img/손흥민.mp3", "./src/img/김연경.mp3", "./src/img/박세리.mp3",
			"./src/img/윤성빈.mp3"//문제별 힌트 tts

	};

	// Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Quiz frame = new Quiz("userId", 0,0,0,0,0,0);
					frame.setVisible(true);
					//화면 중앙 표시
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
		//화면 중앙 표시
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				playMusic(cnt); // 문제 번호에 따라 음악 재생
			}
		});
		btnNewButton.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 25));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(455, 558, 73, 69);
		contentPane.add(btnNewButton);

		textField = new JTextField();
		textField.setBackground(new Color(192, 192, 192));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD, 19));
		textField.setBounds(152, 542, 292, 57);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setBorder(null);
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(Quiz.class.getResource("/img/baseball1.png"))); // 초기 이미지 설정
		lblNewLabel_1.setBounds(81, 102, 445, 394);
		contentPane.add(lblNewLabel_1);
		
		SwingUtilities.invokeLater(new Runnable() {//첫 실행시 강제 커서 이동
	        public void run() {
	        	textField.requestFocus();
	        }
	    });
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFocusPainted(false);
		ActionListener answerAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {// 정답 비교
				String answer;
				answer = textField.getText();
				textField.requestFocus();
				if (!answer.trim().isEmpty()) // 비어있지 않으면 정답을 비교함(isEmpty()비어있는지 확인)(trim()는 공백비교)
					checkAnswer();
				else// 비어있을 경우
					JOptionPane.showMessageDialog(null, "정답 입력하세요.");
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
			public void mouseClicked(MouseEvent e) {//뒤로가기
				dispose();
				setVisible(false);
				new Mainpage(id, sum5, score,cnt1,1,like0,like1,like2,like3).setVisible(true);//quiz들어왔으면 1을반환
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

	// 정답 확인 및 점수 처리 메서드
	private void checkAnswer() {
		String answer = textField.getText();
		String correctAnswer = answers[cnt]; // 현재 문제의 정답 가져오기
		if (answer.trim().equalsIgnoreCase(correctAnswer)) {
			score++; // 정답일 경우 점수 증가
			JOptionPane.showMessageDialog(null, "정답입니다.");
			updateImage(cnt); // 이미지 업데이트
			cnt++;
			if (soundThread != null)//확인버튼 눌렀을때 힌트 재생 정지
				soundThread.stopRunning();
		} else {
			JOptionPane.showMessageDialog(null, "오답입니다.");
			updateImage(cnt); // 이미지 업데이트
			cnt++;
			if (soundThread != null)//확인버튼 눌렀을때 힌트 재생 정지
				soundThread.stopRunning();
		}
		textField.setText(""); // 입력 필드 초기화

		// 다음 문제로 넘어가기 위해 cnt 증가

		// 모든 문제를 푼 경우 점수를 출력
		if (cnt >= answers.length) {
			JOptionPane.showMessageDialog(null, "퀴즈가 끝났습니다.\n당신의 점수는 " + score + "점입니다.");
		}
	}

	// 해당 문제 번호에 따라 음악 재생 메서드
	private void playMusic(int index) {

		toggleMusic(soundPaths[index]);

	}

	// 해당 문제 번호에 따라 이미지 업데이트 메서드
	private void updateImage(int index) {
		if (index < imagePaths.length) {//현재 문제 번호가 이미지 경로 배열보다 길이가 작을 경우 문제 번호를 이미 경로 배열 순서에 넣음
			lblNewLabel_1.setIcon(new ImageIcon(imagePaths[index]));//그럼 이미지 경로 배열에 문제번호 번째를 찾아감
		}
	}

	private void toggleMusic(String musicPath) {//쓰레드는 공부 해보고 설명 씀
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
