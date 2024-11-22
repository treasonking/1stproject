package winnlose;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mainpage.Mainpage;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;

public class WinDrawLose extends JFrame {

    private JPanel contentPane;
    private int sum1 = 0; //1
    private int sum2 = 0; //2
    private int sum3 = 1; //1
    private int sum4 = 1; //1
    private float sum5 = (float) 10.0; //1
    private int i;
    
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WinDrawLose frame = new WinDrawLose("userId",0,0,0,0,0,0);
                    frame.setVisible(true);
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
    public WinDrawLose(String id,int score,int cnt1,int like0,int like1,int like2,int like3) {
       //크기고정
       setResizable(false);
       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 740, 795);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JTextPane txtpnRed = new JTextPane();
        txtpnRed.setFont(new Font("굴림", Font.PLAIN, 20));
        txtpnRed.setText("1. Red 팀은 빨간색 버튼 입니다.\r\n2. Blue 팀은 파란색 버튼 입니다.\r\n3. 무승부는 녹색 버튼 입니다.\r\n4. <- 표시는 뒤로 가기 입니다.\r\n5. 되돌이표시는 다시하기 입니다.");
        txtpnRed.setForeground(Color.WHITE);
        txtpnRed.setBackground(Color.BLACK);
        txtpnRed.setBounds(175, 448, 390, 247);
        contentPane.add(txtpnRed);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(52, 63, 639, 295);
        contentPane.add(scrollPane_1);
        
                JTextArea textArea = new JTextArea();
                scrollPane_1.setViewportView(textArea);
                textArea.setFont(new Font("굴림", Font.PLAIN, 22));
                textArea.setEditable(false);
                textArea.setWrapStyleWord(true);
                textArea.setLineWrap(true);
        //화면 중앙에 고정
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

        // Random함수
        Random random = new Random();

        // StringBuilder 
        StringBuilder scores = new StringBuilder();
        JButton btnBack = new JButton(""); // Back   ư  ߰ 
        btnBack.setOpaque(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setBorderPainted(false);
        btnBack.setFocusPainted(false);
        btnBack.setIcon(new ImageIcon(WinDrawLose.class.getResource("/ImageIcon/back.png")));
        btnBack.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {//뒤로가기
              
              dispose();
              setVisible(false);
              new Mainpage(id,sum5,score,1,cnt1,like0,like1,like2,like3).setVisible(true);//game들어왔으면 1을반환
           }
        });
        btnBack.setBounds(312, 705, 110, 41);
        contentPane.add(btnBack);
        JButton btnTeam1 = new JButton("");
        btnTeam1.setOpaque(false);
        btnTeam1.setContentAreaFilled(false);
        btnTeam1.setBorderPainted(false);
        btnTeam1.setFocusPainted(false);
        btnTeam1.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
              if (sum5 >= 1) {//점수가 1점보다 많으면
              for(i = 1; i<10; i++) {//반복문으로 점수를 sum1과sum2에 저장
                    int score1=random.nextInt(3);
                    int score2=random.nextInt(3);
                    sum1+=score1;
                    sum2+=score2;
                    textArea.append(i+"회 : "+"Red Team vs Blue Team : "+sum1+":"+sum2+"\n");
                 
                 }
                if (sum1 > sum2) {//sum1이 크면 팀1이 이김
                    textArea.append("Red Team 승!! 성공 했습니다!\n");
                    sum5 += (float) (sum3 * 0.2); // Apply 1.2 odds
                 } else if (sum1 < sum2) {//sum2이 크면 팀1이 이김
                    textArea.append("Blue Team 승~ 실패 했습니다~\n");
                    sum5 -= sum4; 
                 } else {//무승부
                    textArea.append("무승부~ 실패 했습니다~\n");
                    sum5 -= sum4;
                 }
                //초기화
                 calculateResult();
                 sum1 = 0;
                 sum2 = 0;
           } 
           }
         
         private void calculateResult() {
            textArea.append("\nFinal Result:\n");
            float result = sum5;
               if (sum5 < 1) {//가진 점수가 0보다 작으면 점수 리셋
                  textArea.append(result+"점. 1점 이하 입니다. \n게임을 재시작 해주세요.\n");

               } else {
                   textArea.append("내 점수: " + sum5 + "\n");
               }
         }
        });
        btnTeam1.setBounds(591, 549, 45, 34);
        contentPane.add(btnTeam1);
        
        JButton btnDraw = new JButton(""); //      ư
        btnDraw.setOpaque(false);
        btnDraw.setContentAreaFilled(false);
        btnDraw.setBorderPainted(false);
        btnDraw.setFocusPainted(false);
        btnDraw.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
              if (sum5 >= 1) {//가진 점수가 1보다 클때
              for(i = 1; i<10; i++) {
                    int score1=random.nextInt(3);
                    int score2=random.nextInt(3);
                    sum1+=score1;
                    sum2+=score2;
                    textArea.append(i+"회 : "+"Red Team vs Blue Team : "+sum1+":"+sum2+"\n");
              
                 }
                  if (sum1 > sum2) {//팀1이 팀2보다 점수가 클때
                       textArea.append("Red Team 승~ 실패 했습니다~\n");
                       sum5 -= sum4; 
                    } else if (sum1 < sum2) {//팀2이 팀1보다 점수가 클때
                       textArea.append("Blue Team 승~ 실패 했습니다~\n");
                       sum5 -= sum4; 
                    } else {//무승부 일때
                       textArea.append("무승부! 성공!\n");
                       sum5 += (float) (sum3 * 0.5); // Apply 1.5 odds for draw
                    }
                    calculateResult();
                    sum1 = 0;
                    sum2 = 0;
              } 
           }
            private void calculateResult() {
               textArea.append("\nFinal Result:\n");
               float result = sum5;
               if (sum5 < 1) {//가진 점수가 0보다 작으면 점수 리셋
                   textArea.append(result+"점. 1점 이하 입니다. \n게임을 재시작 해주세요.\n");
                      
                  } else {
                      textArea.append("내 점수: " + sum5 + "\n");
                  }
            }
        });
        btnDraw.setBounds(651, 549, 50, 34);
        contentPane.add(btnDraw);
        
        JButton btnTeam2 = new JButton(""); //      ư
        btnTeam2.setOpaque(false);
        btnTeam2.setContentAreaFilled(false);
        btnTeam2.setBorderPainted(false);
        btnTeam2.setFocusPainted(false);
        btnTeam2.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
              if (sum5 >= 1) {//점수가 1보다 많을때
              for(i = 1; i<10; i++) {
                    int score1=random.nextInt(3);
                    int score2=random.nextInt(3);
                    sum1+=score1;
                    sum2+=score2;
                    textArea.append(i+"회 : "+"Red Team vs Blue Team : "+sum1+":"+sum2+"\n"); 
                 }
               if (sum1 > sum2) {
                    textArea.append("Red Team 승~ 실패 했습니다~\n");
                    sum5 -= sum4; 
                 } else if (sum1 < sum2) {
                    textArea.append("Blue Team 승! 성공했습니다!\n");
                    sum5 += (float) (sum3 * 0.3); // Apply 1.3 odds
                 } else {
                    textArea.append("무승부~ 실패 했습니다~\n");
                    sum5 -= sum4;
                 }
                 calculateResult();
                 sum1 = 0;
                 sum2 = 0;
           } 
           }
         private void calculateResult() {
            textArea.append("\nFinal Result:\n");
            float result = sum5;
            if (sum5 < 1) {//가진 점수가 0보다 작으면 점수 리셋
               textArea.append(result+"점. 1점 이하 입니다. \n게임을 재시작 해주세요.\n");

               } else {
                   textArea.append("내 점수: " + sum5 + "\n");
               }
         }
        });
        btnTeam2.setBounds(618, 516,45, 34);
        contentPane.add(btnTeam2);        
        
        JButton btnDelete = new JButton("");
        btnDelete.setOpaque(false);
        btnDelete.setContentAreaFilled(false);
        btnDelete.setBorderPainted(false);
        btnDelete.setFocusPainted(false);
        btnDelete.setIcon(new ImageIcon(WinDrawLose.class.getResource("/ImageIcon/iconmonstr-reload-thin-24.png")));
        btnDelete.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {//다시하기(초기화)
              textArea.setText("");
              sum5 = 10; // Reset Team 1's score
           }
        });
        btnDelete.setBounds(73, 516, 45, 41);
        contentPane.add(btnDelete);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(WinDrawLose.class.getResource("/background/KakaoTalk_20240709_161057345.jpg")));
        lblNewLabel.setBounds(0, 0, 738, 770);
        contentPane.add(lblNewLabel);
      
        
        
   }
}

