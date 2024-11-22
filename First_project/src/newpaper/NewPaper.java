package newpaper;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import loginDAO.LoginDAO;
import mainpage.Mainpage;

import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import java.awt.Toolkit;

public class NewPaper extends JFrame {

   private JPanel contentPane;
   private JTextArea textArea;
   private JLabel lblNewLabel;
   private JLabel lblNewLabel_1;
   private JButton btnNewButton_1_1_2; // 음성 재생 버튼
   private JButton btnNewButton_1_1_1_1;
   private JButton btnNewButton_2;
   private JLabel lblNewLabel_2_1; // 뉴스 출처 url
   private boolean likeButtonClicked = false; // 좋아요 버튼 초기값
   private boolean isFontLarge = false; // 글씨 크기 초기값
   private SoundThread soundThread; // SoundThread 객체
   private boolean isPlaying = false; // 음성 재생 상태를 저장할 변수
   private int currentIndex = 0; // 각 기사별 고유번호
   private int[] pageLikes = new int[4]; // 각 기사의 좋아요 상태 배열
   private JButton btnNewButton_1;
   private String[] url = { "<html><a href=''>https://www.nabo.com/sportsnews/201\r\n</a></html>",
         "<html><a href=''>https://www.nabo.com/sportsnews/154\r\n</a></html>",
         "<html><a href=''>https://www.nabo.com/sportsnews/210</a></html>",
         "<html><a href=''>https://www.nabo.com/sportsnews/756</a></html>" };
   private JScrollPane scrollPane;
   private JLabel lblNewLabel_4;
   private LoginDAO ldao;
   private int[] pageLikes_cnt = new int[4];
   private JLabel lblNewLabel_3;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               NewPaper frame = new NewPaper("UserId", 0, 0, 0,0,0,0,0,0,0);
               //화면 중앙에 배치
               Dimension frameSize = frame.getSize();
               Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
               frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
               
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the frame.
    */
   public NewPaper(String id, int viewCount, double sum5, int score,int cnt,int cnt1,int like0,int like1,int like2,int like3) {
	   try {//LoginDAO와 연결
           ldao = new LoginDAO();
       } catch (SQLException | ClassNotFoundException e) {
           e.printStackTrace();
       }
	   try {
		pageLikes[0]=ldao.chuchen0(id);
		pageLikes[1]=ldao.chuchen1(id);
		pageLikes[2]=ldao.chuchen2(id);
		pageLikes[3]=ldao.chuchen3(id);
		pageLikes_cnt[0]=ldao.chuchen0_cnt();
		pageLikes_cnt[1]=ldao.chuchen1_cnt();
		pageLikes_cnt[2]=ldao.chuchen2_cnt();
		pageLikes_cnt[3]=ldao.chuchen3_cnt();
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	   
      setIconImage(Toolkit.getDefaultToolkit().getImage(NewPaper.class.getResource("/image/newspaper.png")));
      setTitle("Sports News\r\n");
      setResizable(false);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 755, 795);
      contentPane = new JPanel();
      contentPane.setBackground(new Color(240, 248, 255));
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

      setContentPane(contentPane);
      contentPane.setLayout(null);
      //화면중앙에 표시
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
      JButton btnNewButton = new JButton(""); // Right 버튼
      btnNewButton.setOpaque(false);
      btnNewButton.setContentAreaFilled(false);
      btnNewButton.setBorderPainted(false);
      btnNewButton.setFocusPainted(false);
      
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         }
      });

      btnNewButton.addMouseListener(new MouseAdapter() {
    	  
         @Override
         public void mouseClicked(MouseEvent e) {
        	 if(currentIndex == 2)
           	  btnNewButton.setVisible(false);
        	 btnNewButton_1.setVisible(true);
        	 if (isPlaying & currentIndex == 0) //현재 페이지 0일때
                 soundThread.stopRunning();//다음 페이지 누르면 멈춤
              
              if (isPlaying & currentIndex == 1) //현재 페이지 1일때
                 soundThread.stopRunning();//다음 페이지 누르면 멈춤

              if (isPlaying & currentIndex == 2) //현재 페이지 2일때
                 soundThread.stopRunning();//다음 페이지 누르면 멈춤

            if (currentIndex < 3)//3보다 적을때만 페이지 증가
               currentIndex++;

            if (currentIndex == 0) {//페이지 1일때
            	
            	
               textArea.setText(
                     "토티넘 핫스퍼의 공격수 손응민(30)이 프레미어리그에서 31번째 골을 넣으며 또 다른 위대한 기록을 세웠다. 이번 경기는 토티넘과 맨체스터 유니티드의 경기로, 손응민은 후반 75분에 결정적인 골을 넣으며 팀을 승리로 이끌었다.\r\n"
                           + "손응민은 이번 시즌 동안 꾸준한 활약을 펼치며 팀의 핵심 선수로 자리잡았다. 그의 탁월한 속도와 날카로운 슈팅 능력은 많은 축구 팬들로부터 찬사를 받고 있다. 이번 골로 손응민은 아시아 선수로서 프레미어리그에서 가장 많은 골을 넣은 기록을 경신하게 되었다.\r\n"
                           + "손응민은 경기 후 인터뷰에서 \"팀 동료들과 팬들 덕분에 이런 기록을 세울 수 있었다. 항상 최선을 다해 플레이할 것이며, 더 많은 골로 팀에 기여하고 싶다\"고 소감을 밝혔다.\r\n"
                           + "토티넘의 감독은 \"손응민은 우리 팀의 진정한 에이스다. 그의 헌신과 노력은 우리 모두에게 큰 영감을 준다\"며 손응민을 칭찬했다.\r\n"
                           + "손응민의 이번 기록은 단순한 골 수치를 넘어, 그의 끊임없는 노력과 열정을 상징하는 것이다. 앞으로도 그의 활약이 계속될지 전 세계 축구 팬들의 이목이 집중되고 있다.");
               lblNewLabel.setText("손응민, 프레미어리그 31번째 골");
               lblNewLabel_1.setIcon(new ImageIcon("./src/image/son.jpg"));
               lblNewLabel_2_1.setText(url[0]);
               

               if (pageLikes[0] == 1) {//추천 한번 눌렀을 경우
                  btnNewButton_1_1_1_1.setEnabled(false);
                  likeButtonClicked = true;
               } else {//추천 안 눌렀을 경우
                  btnNewButton_1_1_1_1.setEnabled(true);
                  likeButtonClicked = false;
               }
            }
            if (currentIndex == 1) {//2페이지 일때
            	lblNewLabel_3.setText("추천수: "+pageLikes_cnt[1]);
               textArea.setText(
                     "NAB의 샌프란시스코 오리스는 스테판 카이의 환상적인 슈팅 능력으로 승리했습니다. 이날 경기에서 그는 3점 슛 10개를 성공시키며 팀을 이끌었습니다. 스테판 카이는 그의 존재 자체가 경기의 흐름을 바꾸는 힘을 가지고 있습니다. 그의 슈팅 감각은 단순히 기록을 세우는 것을 넘어서, 팬들에게는 진정한 예술이 됩니다.\r\n"
                           + "스테판 카이는 어린 시절부터 뛰어난 물리적 재능과 뛰어난 기술을 지녔습니다. 그의 아버지인 델 카이 역시 NAB에서 활약한 전설적인 선수였으며, 그의 성공적인 경력은 스테판에게 큰 영향을 미쳤습니다. 스테판은 어린 시절부터 골프에서 배운 집중력과 정밀성을 농구에 접목시켜, 특유의 슈팅 스타일을 개발했습니다. 그의 빠른 손목과 정확한 시야는 그를 NAB 역사상 최고의 슈터로 만들었습니다.\r\n"
                           + "그의 진가는 특히 큰 무대에서 드러납니다. NAB 플레이오프에서의 스테판 카이는 높이 뛰어난 압박감과 상황 판단력을 보여줍니다. 그는 시합이 치열할수록 더욱 빛을 발하며, 결정적인 순간에 팀을 구원하는 슈팅을 성공적으로 만들어냅니다. 그의 팀원들은 그의 존재가 경기장의 분위기를 바꾸는 마력을 가졌다고 말합니다.\r\n"
                           + "스테판 카이는 뛰어난 선수로서의 능력을 넘어서, 커뮤니티와 사회에도 큰 영향을 미칩니다. 그는 자선 행사와 기부 활동을 통해 사회적 책임을 다하고 있습니다. 그의 영향력은 농구 코트를 넘어서, 다양한 분야에서 사람들에게 영감을 주고 있습니다.\r\n"
                           + "스테판 카이는 그의 뛰어난 성과와 인격적인 면모로 농구 팬들의 사랑을 받고 있습니다. 그의 미래에 대한 기대는 더욱 밝아질 것으로 보입니다. NAB 역사상 최고의 슈팅 감각을 자랑하는 스테판 카이가 향후 어떤 역사적인 기록을 세울지, 팬들은 기대하며 기다립니다.");
               lblNewLabel.setText("스테판 카이, 그는 최고의 선수");
               lblNewLabel_1.setIcon(new ImageIcon("./src/image/basketball-14861_640.jpg"));
               lblNewLabel_2_1.setText(url[1]);

               if (pageLikes[1] == 1) {//추천 한번 눌렀을 경우
                  btnNewButton_1_1_1_1.setEnabled(false);
                  likeButtonClicked = true;
               } else {//추천 안 눌렀을 경우
                  btnNewButton_1_1_1_1.setEnabled(true);
                  likeButtonClicked = false;
               }
            }
            if (currentIndex == 2) {//3페이지
            	lblNewLabel_3.setText("추천수: "+pageLikes_cnt[2]);
               textArea.setText(
                     "SSS 팀의 김경현 선수가 도산 베이스와의 홈 경기에서 투수로서 뛰어난 모습을 선보였습니다. 이날 경기는 야구 팬들 사이에서 기대와 긴장 속에서 진행되었으며, 김 선수는 팀의 주축으로서 투구에서 무리 없이 상대를 제압하며 경기를 주도했습니다.\r\n"
                           + "김선수는 선발 투수로서 그라운드에 올라가 첫 투구부터 정확하고 강력한 공을 던졌습니다. 그는 7이닝 동안 도산 선수들에게 1안타 무실점으로 막강한 퍼포먼스를 펼쳤으며, 상대 타자들을 완벽히 잠재웠습니다. 특히 김 선수의 변화구와 슬라이더는 도산 타자들을 앞세우게 하며 동료들과 팬들에게 자신감을 심어주었습니다.\r\n"
                           + "또한, 김 선수는 투구 능력 외에도 수비와 주루에도 민첩하게 참여하여 팀의 전반적인 방어력을 높였습니다. 그의 리드와 통솔력은 경기의 흐름을 좌우하는 중요한 요소였으며, 경기 막판에도 불펜 투수들과 원활하게 협업하여 승리를 이끌어냈습니다.\r\n"
                           + "이날 경기는 김경현 선수의 뛰어난 투구와 리더십이 돋보이는 경기로, SSS 팀은 역전 승을 차지하며 팬들에게 큰 기쁨을 선사했습니다. 김 선수는 팀과 팬들에게 자신감과 희망을 심어주는 중요한 역할을 했습니다.");
               lblNewLabel.setText("\"김경현, 도산을 제압하며 승리\"");
               lblNewLabel_1.setIcon(new ImageIcon("./src/image/baseball.jpg"));
               lblNewLabel_2_1.setText(url[2]);

               if (pageLikes[2] == 1) {//추천 한번 눌렀을경우
                  btnNewButton_1_1_1_1.setEnabled(false);
                  likeButtonClicked = true;
               } else {//추천 안 눌렀을경우
                  btnNewButton_1_1_1_1.setEnabled(true);
                  likeButtonClicked = false;
               }
            }
            if (currentIndex == 3) {//4페이지
            	lblNewLabel_3.setText("추천수: "+pageLikes_cnt[3]);
               textArea.setText(
                     "이번 경기는 PGS와 라옹 사이에서 펼쳐졌다. 양 팀은 초반부터 치열한 공방전을 펼치며 팽팽한 경기를 이어갔다. 그러나 전반 30분, 김강인은 첫 번째 골을 기록하며 경기의 흐름을 바꾸었다. 그의 첫 번째 골은 박스 밖에서 화려한 드리블로 두 명의 수비수를 제친 후, 강력한 오른발 슛으로 골망을 흔드는 장면이었다.\r\n"
                           + "이후 김강인은 전반 40분에 다시 한 번 골을 기록했다. 이번에는 중원에서 공을 잡은 후, 라옹의 수비진을 무너뜨리며 박스 안으로 돌파해 침착하게 마무리했다. 그의 뛰어난 판단력과 속도는 라옹 수비수들이 손 쓸 틈을 주지 않았다.\r\n"
                           + "마지막으로 후반 60분, 김강인은 해트트릭을 완성하는 골을 터뜨렸다. 상대팀의 코너킥 상황에서 빠르게 역습을 전개한 PGS는 김강인에게 공을 연결했고, 그는 상대 골키퍼와의 1대1 상황에서 침착하게 공을 골대 안으로 밀어 넣었다. 이로써 김강인은 자신의 첫 PGS 해트트릭을 기록하며 팀의 3-1 승리를 확정지었다.");
               lblNewLabel.setText("김강인, 화려한 헤트트릭 달성");
               lblNewLabel_1.setIcon(new ImageIcon("./src/image/Lee.jpg"));
               lblNewLabel_2_1.setText(url[3]);
               if (pageLikes[3] == 1) {//추천 한번 눌렀을경우
                  btnNewButton_1_1_1_1.setEnabled(false);
                  likeButtonClicked = true;
               } else {//추천 안 눌렀을경우
                  btnNewButton_1_1_1_1.setEnabled(true);
                  likeButtonClicked = false;
               }
            }

         }
      });
      btnNewButton.setBackground(Color.WHITE);
      btnNewButton.setFont(new Font("굴림체", Font.PLAIN, 17));
      btnNewButton.setIcon(new ImageIcon("./src/image/right.png"));
      btnNewButton.setBounds(681, 705, 46, 41);
      contentPane.add(btnNewButton);

      lblNewLabel = new JLabel("\uC190\uC751\uBBFC, \uD504\uB808\uBBF8\uC5B4\uB9AC\uADF8 31\uBC88\uC9F8 \uACE8");
      lblNewLabel.setFont(new Font("나눔고딕 ExtraBold", Font.BOLD | Font.ITALIC, 20));
      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel.setBounds(199, 33, 319, 62);
      contentPane.add(lblNewLabel);

      btnNewButton_1 = new JButton(""); // Left 버튼
      btnNewButton_1.setOpaque(false);
      btnNewButton_1.setContentAreaFilled(false);
      btnNewButton_1.setBorderPainted(false);
      btnNewButton_1.setFocusPainted(false);
      btnNewButton_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         }
      });

      JButton btnNewButton_1_1_1 = new JButton(""); // font 크기 조절 버튼
      btnNewButton_1_1_1.setOpaque(false);
      btnNewButton_1_1_1.setContentAreaFilled(false);
      btnNewButton_1_1_1.setBorderPainted(false);
      btnNewButton_1_1_1.setFocusPainted(false);
      btnNewButton_1_1_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         }
      });
      btnNewButton_1_1_1.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {//글씨 크기 변경
            if (isFontLarge) {
               textArea.setFont(new Font("굴림체", Font.PLAIN, 18)); // 원래 글씨 크기로 변경
            } else {
               textArea.setFont(new Font("굴림체", Font.BOLD, 30)); // 글씨 크게 변경
            }
            isFontLarge = !isFontLarge; // 상태 변경
         }

      });
      btnNewButton_1_1_1.setIcon(new ImageIcon("./src/image/iconmonstr-text-3-16.png"));
      btnNewButton_1_1_1.setBackground(new Color(255, 255, 255));
      btnNewButton_1_1_1.setBounds(557, 61, 46, 34);
      contentPane.add(btnNewButton_1_1_1);
      
      btnNewButton_1.setVisible(false);
      
      btnNewButton_1.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {//이전버튼
        	 if(currentIndex == 1) {
        		 btnNewButton_1.setVisible(false);
        	 }
        	 btnNewButton.setVisible(true);
        	 if (isPlaying & currentIndex == 1) //현제 페이지 1일때 버튼 누르면 멈춤
                 soundThread.stopRunning();
              
              if (isPlaying & currentIndex == 2) //현제 페이지 2일때 버튼 누르면 멈춤
                 soundThread.stopRunning();

              if (isPlaying & currentIndex == 3) //현제 페이지 3일때 버튼 누르면 멈춤
                 soundThread.stopRunning();

            if (currentIndex >= 1)//1이상 일때 현제 페이지 감소함
               currentIndex--;

            if (currentIndex == 0) {//1페이지
            	lblNewLabel_3.setText("추천수: "+pageLikes_cnt[0]);
               textArea.setText(
                     "토티넘 핫스퍼의 공격수 손응민(30)이 프레미어리그에서 31번째 골을 넣으며 또 다른 위대한 기록을 세웠다. 이번 경기는 토티넘과 맨체스터 유니티드의 경기로, 손응민은 후반 75분에 결정적인 골을 넣으며 팀을 승리로 이끌었다.\r\n"
                           + "손응민은 이번 시즌 동안 꾸준한 활약을 펼치며 팀의 핵심 선수로 자리잡았다. 그의 탁월한 속도와 날카로운 슈팅 능력은 많은 축구 팬들로부터 찬사를 받고 있다. 이번 골로 손응민은 아시아 선수로서 프레미어리그에서 가장 많은 골을 넣은 기록을 경신하게 되었다.\r\n"
                           + "손응민은 경기 후 인터뷰에서 \"팀 동료들과 팬들 덕분에 이런 기록을 세울 수 있었다. 항상 최선을 다해 플레이할 것이며, 더 많은 골로 팀에 기여하고 싶다\"고 소감을 밝혔다.\r\n"
                           + "토티넘의 감독은 \"손응민은 우리 팀의 진정한 에이스다. 그의 헌신과 노력은 우리 모두에게 큰 영감을 준다\"며 손응민을 칭찬했다.\r\n"
                           + "손응민의 이번 기록은 단순한 골 수치를 넘어, 그의 끊임없는 노력과 열정을 상징하는 것이다. 앞으로도 그의 활약이 계속될지 전 세계 축구 팬들의 이목이 집중되고 있다.");
               lblNewLabel.setText("손응민, 프레미어리그 31번째 골");
               lblNewLabel_1.setIcon(new ImageIcon("./src/image/son.jpg"));
               lblNewLabel_2_1.setText(url[0]);
               
               if (pageLikes[0] == 1) {//추천 한번 눌렀을경우
                  btnNewButton_1_1_1_1.setEnabled(false);
                  likeButtonClicked = true;
               } else {//추천 안 눌렀을경우
                  btnNewButton_1_1_1_1.setEnabled(true);
                  likeButtonClicked = false;
               }
            }
            if (currentIndex == 1) {//2페이지
            	lblNewLabel_3.setText("추천수: "+pageLikes_cnt[1]);
               textArea.setText(
                     "NAB의 샌프란시스코 오리스는 스테판 카이의 환상적인 슈팅 능력으로 승리했습니다. 이날 경기에서 그는 3점 슛 10개를 성공시키며 팀을 이끌었습니다. 스테판 카이는 그의 존재 자체가 경기의 흐름을 바꾸는 힘을 가지고 있습니다. 그의 슈팅 감각은 단순히 기록을 세우는 것을 넘어서, 팬들에게는 진정한 예술이 됩니다.\r\n"
                           + "스테판 카이는 어린 시절부터 뛰어난 물리적 재능과 뛰어난 기술을 지녔습니다. 그의 아버지인 델 카이 역시 NAB에서 활약한 전설적인 선수였으며, 그의 성공적인 경력은 스테판에게 큰 영향을 미쳤습니다. 스테판은 어린 시절부터 골프에서 배운 집중력과 정밀성을 농구에 접목시켜, 특유의 슈팅 스타일을 개발했습니다. 그의 빠른 손목과 정확한 시야는 그를 NAB 역사상 최고의 슈터로 만들었습니다.\r\n"
                           + "그의 진가는 특히 큰 무대에서 드러납니다. NAB 플레이오프에서의 스테판 카이는 높이 뛰어난 압박감과 상황 판단력을 보여줍니다. 그는 시합이 치열할수록 더욱 빛을 발하며, 결정적인 순간에 팀을 구원하는 슈팅을 성공적으로 만들어냅니다. 그의 팀원들은 그의 존재가 경기장의 분위기를 바꾸는 마력을 가졌다고 말합니다.\r\n"
                           + "스테판 카이는 뛰어난 선수로서의 능력을 넘어서, 커뮤니티와 사회에도 큰 영향을 미칩니다. 그는 자선 행사와 기부 활동을 통해 사회적 책임을 다하고 있습니다. 그의 영향력은 농구 코트를 넘어서, 다양한 분야에서 사람들에게 영감을 주고 있습니다.\r\n"
                           + "스테판 카이는 그의 뛰어난 성과와 인격적인 면모로 농구 팬들의 사랑을 받고 있습니다. 그의 미래에 대한 기대는 더욱 밝아질 것으로 보입니다. NAB 역사상 최고의 슈팅 감각을 자랑하는 스테판 카이가 향후 어떤 역사적인 기록을 세울지, 팬들은 기대하며 기다립니다.");
               lblNewLabel.setText("스테판 카이, 그는 최고의 선수");
               lblNewLabel_1.setIcon(new ImageIcon("./src/image/basketball-14861_640.jpg"));
               lblNewLabel_2_1.setText(url[1]);
               btnNewButton_1.setVisible(true);
               if (pageLikes[1] == 1) {//추천 한번 눌렀을경우
                  btnNewButton_1_1_1_1.setEnabled(false);
                  likeButtonClicked = true;
               } else {//추천 안 눌렀을경우
                  btnNewButton_1_1_1_1.setEnabled(true);
                  likeButtonClicked = false;
               }
            }
            if (currentIndex == 2) {//3페이지
            	lblNewLabel_3.setText("추천수: "+pageLikes_cnt[2]);
               textArea.setText(
                     "SSS 팀의 김경현 선수가 도산 베이스와의 홈 경기에서 투수로서 뛰어난 모습을 선보였습니다. 이날 경기는 야구 팬들 사이에서 기대와 긴장 속에서 진행되었으며, 김 선수는 팀의 주축으로서 투구에서 무리 없이 상대를 제압하며 경기를 주도했습니다.\r\n"
                           + "김선수는 선발 투수로서 그라운드에 올라가 첫 투구부터 정확하고 강력한 공을 던졌습니다. 그는 7이닝 동안 도산 선수들에게 1안타 무실점으로 막강한 퍼포먼스를 펼쳤으며, 상대 타자들을 완벽히 잠재웠습니다. 특히 김 선수의 변화구와 슬라이더는 도산 타자들을 앞세우게 하며 동료들과 팬들에게 자신감을 심어주었습니다.\r\n"
                           + "또한, 김 선수는 투구 능력 외에도 수비와 주루에도 민첩하게 참여하여 팀의 전반적인 방어력을 높였습니다. 그의 리드와 통솔력은 경기의 흐름을 좌우하는 중요한 요소였으며, 경기 막판에도 불펜 투수들과 원활하게 협업하여 승리를 이끌어냈습니다.\r\n"
                           + "이날 경기는 김경현 선수의 뛰어난 투구와 리더십이 돋보이는 경기로, SSS 팀은 역전 승을 차지하며 팬들에게 큰 기쁨을 선사했습니다. 김 선수는 팀과 팬들에게 자신감과 희망을 심어주는 중요한 역할을 했습니다.");
               lblNewLabel.setText("\"김경현, 도산을 제압하며 승리\"");
               lblNewLabel_1.setIcon(new ImageIcon("./src/image/baseball.jpg"));
               lblNewLabel_2_1.setText(url[2]);
               btnNewButton_1.setVisible(true);
               if (pageLikes[2] == 1) {//추천 한번 눌렀을경우
                  btnNewButton_1_1_1_1.setEnabled(false);
                  likeButtonClicked = true;
               } else {//추천 안 눌렀을경우
                  btnNewButton_1_1_1_1.setEnabled(true);
                  likeButtonClicked = false;
               }
            }
            if (currentIndex == 3) {//4페이지
            	lblNewLabel_3.setText("추천수: "+pageLikes_cnt[3]);
               textArea.setText(
                     "이번 경기는 PGS와 라옹 사이에서 펼쳐졌다. 양 팀은 초반부터 치열한 공방전을 펼치며 팽팽한 경기를 이어갔다. 그러나 전반 30분, 김강인은 첫 번째 골을 기록하며 경기의 흐름을 바꾸었다. 그의 첫 번째 골은 박스 밖에서 화려한 드리블로 두 명의 수비수를 제친 후, 강력한 오른발 슛으로 골망을 흔드는 장면이었다.\r\n"
                           + "이후 김강인은 전반 40분에 다시 한 번 골을 기록했다. 이번에는 중원에서 공을 잡은 후, 라옹의 수비진을 무너뜨리며 박스 안으로 돌파해 침착하게 마무리했다. 그의 뛰어난 판단력과 속도는 라옹 수비수들이 손 쓸 틈을 주지 않았다.\r\n"
                           + "마지막으로 후반 60분, 김강인은 해트트릭을 완성하는 골을 터뜨렸다. 상대팀의 코너킥 상황에서 빠르게 역습을 전개한 PGS는 김강인에게 공을 연결했고, 그는 상대 골키퍼와의 1대1 상황에서 침착하게 공을 골대 안으로 밀어 넣었다. 이로써 김강인은 자신의 첫 PGS 해트트릭을 기록하며 팀의 3-1 승리를 확정지었다.");
               lblNewLabel.setText("김강인, 화려한 헤트트릭 달성");
               lblNewLabel_1.setIcon(new ImageIcon("./src/image/Lee.jpg"));
               lblNewLabel_2_1.setText(url[3]);
               btnNewButton_1.setVisible(true);
               if (pageLikes[3] == 1) {//추천 한번 눌렀을경우
                  btnNewButton_1_1_1_1.setEnabled(false);
                  likeButtonClicked = true;
               } else {//추천 안 눌렀을경우
                  btnNewButton_1_1_1_1.setEnabled(true);
                  likeButtonClicked = false;
               }

            }

         }
      });
      btnNewButton_1.setBackground(Color.WHITE);
      btnNewButton_1.setIcon(new ImageIcon("./src/image/left.png"));
      btnNewButton_1.setBounds(22, 705, 46, 41);
      contentPane.add(btnNewButton_1);

      lblNewLabel_1 = new JLabel("");
      lblNewLabel_1.setIcon(new ImageIcon("./src/image/son.jpg")); // 이미지 넣는 경로
      lblNewLabel_1.setBounds(88, 105, 563, 227);
      contentPane.add(lblNewLabel_1);

      btnNewButton_1_1_2 = new JButton(""); // 음성 출력 버튼
      btnNewButton_1_1_2.setOpaque(false);
      btnNewButton_1_1_2.setContentAreaFilled(false);
      btnNewButton_1_1_2.setBorderPainted(false);
      btnNewButton_1_1_2.setFocusPainted(false);
      btnNewButton_1_1_2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         }
      });
      btnNewButton_1_1_2.addMouseListener(new MouseAdapter() {

         @Override
         public void mouseClicked(MouseEvent e) {

            if (currentIndex == 0) {//페이지 1일때 재생되는 소리
               if (isPlaying) {
                  if (soundThread != null) {
                     soundThread.stopRunning();
                     soundThread = null;
                  }
                  isPlaying = false;
               } else {
                  soundThread = new SoundThread("./src/sound/son2.mp3");
                  soundThread.start();
                  isPlaying = true;
               }
            }

            if (currentIndex == 1) {//페이지 2일때 재생되는 소리
               if (isPlaying) {
                  if (soundThread != null) {
                     soundThread.stopRunning();
                     soundThread = null;
                  }
                  isPlaying = false;
               } else {
                  soundThread = new SoundThread("./src/sound/bask.mp3");
                  soundThread.start();
                  isPlaying = true;
               }
            }

            if (currentIndex == 2) {//페이지 3일때 재생되는 소리
               if (isPlaying) {
                  if (soundThread != null) {
                     soundThread.stopRunning();
                     soundThread = null;
                  }
                  isPlaying = false;
               } else {
                  soundThread = new SoundThread("./src/sound/base.mp3");
                  soundThread.start();
                  isPlaying = true;
               }
            }

            if (currentIndex == 3) {//페이지 4일때 재생되는 소리
               if (isPlaying) {
                  if (soundThread != null) {
                     soundThread.stopRunning();
                     soundThread = null;
                  }
                  isPlaying = false;
               } else {
                  soundThread = new SoundThread("./src/sound/foot.mp3");
                  soundThread.start();
                  isPlaying = true;
               }
            }

         }

      });
      btnNewButton_1_1_2.setBackground(new Color(255, 255, 255));
      btnNewButton_1_1_2.setIcon(new ImageIcon("./src/image/iconmonstr-audio-21-16.png"));
      btnNewButton_1_1_2.setBounds(506, 61, 46, 34);
      contentPane.add(btnNewButton_1_1_2);

      btnNewButton_1_1_1_1 = new JButton("");
      btnNewButton_1_1_1_1.setOpaque(false);
      btnNewButton_1_1_1_1.setContentAreaFilled(false);
      btnNewButton_1_1_1_1.setBorderPainted(false);
      btnNewButton_1_1_1_1.setFocusPainted(false);
      btnNewButton_1_1_1_1.setIcon(new ImageIcon("./src/image/iconmonstr-thumb-10-16.png"));
      lblNewLabel_3 = new JLabel("");
      lblNewLabel_3.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 15));
      lblNewLabel_3.setBounds(546, 40, 78, 22);
      contentPane.add(lblNewLabel_3);
      lblNewLabel_3.setText("추천수: "+pageLikes_cnt[0]);
      btnNewButton_1_1_1_1.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {

            if (!likeButtonClicked) { // 좋아요 버튼이 클릭되지 않았을 때만 동작
               likeButtonClicked = true;
               JOptionPane.showMessageDialog(null, "추천 완료");
               btnNewButton_1_1_1_1.setEnabled(false); // 좋아요 버튼 비활성화`
               if (currentIndex == 0) {//1페이지 에서 누르면 비활성화
                  pageLikes[0] = 1;
                  pageLikes_cnt[0] += 1;
                  ldao.update_chuchen(id, pageLikes[0], pageLikes[1], pageLikes[2], pageLikes[3]);
                  ldao.update_chuchen_cnt(pageLikes_cnt[0], pageLikes_cnt[1], pageLikes_cnt[2], pageLikes_cnt[3]);
                  lblNewLabel_3.setText("추천수: "+pageLikes_cnt[0]);
               }
               if (currentIndex == 1) {//2페이지 에서 누르면 비활성화
                  pageLikes[1] = 1;
                  pageLikes_cnt[1] += 1;
                  ldao.update_chuchen(id, pageLikes[0], pageLikes[1], pageLikes[2], pageLikes[3]);
                  ldao.update_chuchen_cnt(pageLikes_cnt[0], pageLikes_cnt[1], pageLikes_cnt[2], pageLikes_cnt[3]);
                  lblNewLabel_3.setText("추천수: "+pageLikes_cnt[1]);
               }
               if (currentIndex == 2) {//3페이지 에서 누르면 비활성화
                  pageLikes[2] = 1;
                  pageLikes_cnt[2] += 1;
                  ldao.update_chuchen(id, pageLikes[0], pageLikes[1], pageLikes[2], pageLikes[3]);
                  ldao.update_chuchen_cnt(pageLikes_cnt[0], pageLikes_cnt[1], pageLikes_cnt[2], pageLikes_cnt[3]);
                  lblNewLabel_3.setText("추천수: "+pageLikes_cnt[2]);
               }
               if (currentIndex == 3) {//4페이지 에서 누르면 비활성화
                  pageLikes[3] = 1;
                  pageLikes_cnt[3] += 1;
                  ldao.update_chuchen(id, pageLikes[0], pageLikes[1], pageLikes[2], pageLikes[3]);
                  ldao.update_chuchen_cnt(pageLikes_cnt[0], pageLikes_cnt[1], pageLikes_cnt[2], pageLikes_cnt[3]);
                  lblNewLabel_3.setText("추천수: "+pageLikes_cnt[3]);
               }
            }
         }
      });
      btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         }
      });
      btnNewButton_1_1_1_1.setBackground(new Color(255, 255, 255));
      btnNewButton_1_1_1_1.setBounds(608, 61, 46, 34);
      contentPane.add(btnNewButton_1_1_1_1);
      if (pageLikes[0] == 1) {//추천 한번 눌렀을 경우
          btnNewButton_1_1_1_1.setEnabled(false);
          likeButtonClicked = true;
       } else {//추천 안 눌렀을 경우
          btnNewButton_1_1_1_1.setEnabled(true);
          likeButtonClicked = false;
       }
      btnNewButton_2 = new JButton("\r\n");
      btnNewButton_2.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      	}
      });
      btnNewButton_2.setOpaque(false);
      btnNewButton_2.setContentAreaFilled(false);
      btnNewButton_2.setBorderPainted(false);
      btnNewButton_2.setFocusPainted(false);
      
      btnNewButton_2.setIcon(new ImageIcon(NewPaper.class.getResource("/ImageIcon/back.png")));
      btnNewButton_2.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            if (soundThread != null)
               soundThread.stopRunning();
            dispose();
            setVisible(false);
            new Mainpage(id, sum5, score,cnt,cnt1,pageLikes[0],pageLikes[1],pageLikes[2],pageLikes[3]).setVisible(true);
         }
      });
      btnNewButton_2.setBounds(88, 50, 67, 34);
      contentPane.add(btnNewButton_2);

      JLabel lblNewLabel_2 = new JLabel("\uCD9C\uCC98");
      lblNewLabel_2.setFont(new Font("나눔고딕", Font.BOLD, 14));
      lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
      lblNewLabel_2.setBounds(170, 724, 78, 22);
      contentPane.add(lblNewLabel_2);

      lblNewLabel_2_1 = new JLabel("<html><a href=''>https://www.nabo.com/sportsnews/201\r\n</a></html>");
      lblNewLabel_2_1.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            openURL("https://www.nabo.com/spotsnews/201");
         }

         private void openURL(String url) {
            try {
               Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
               e.printStackTrace();
            }
         }
      });
      lblNewLabel_2_1.setFont(new Font("굴림", Font.BOLD, 13));
      lblNewLabel_2_1.setBounds(260, 723, 250, 22);
      contentPane.add(lblNewLabel_2_1);
      
      
            
            scrollPane = new JScrollPane();
            scrollPane.setBounds(91, 344, 563, 358);
            contentPane.add(scrollPane);
      
            textArea = new JTextArea();
            textArea.setBackground(new Color(255, 255, 255));
            scrollPane.setViewportView(textArea);
            textArea.setEditable(false);
            textArea.setFont(new Font("굴림체", Font.PLAIN, 18));
            textArea.setText(
                  "\uD1A0\uD2F0\uB118 \uD56B\uC2A4\uD37C\uC758 \uACF5\uACA9\uC218 \uC190\uC751\uBBFC(31)\uC774 \uD504\uB808\uBBF8\uC5B4\uB9AC\uADF8\uC5D0\uC11C 31\uBC88\uC9F8 \uACE8\uC744 \uB123\uC73C\uBA70 \uB610 \uB2E4\uB978 \uC704\uB300\uD55C \uAE30\uB85D\uC744 \uC138\uC6E0\uB2E4. \uC774\uBC88 \uACBD\uAE30\uB294 \uD1A0\uD2F0\uB118\uACFC \uB9E8\uCCB4\uC2A4\uD130 \uC720\uB2C8\uD2F0\uB4DC\uC758 \uACBD\uAE30\uB85C, \uC190\uC751\uBBFC\uC740 \uD6C4\uBC18 75\uBD84\uC5D0 \uACB0\uC815\uC801\uC778 \uACE8\uC744 \uB123\uC73C\uBA70 \uD300\uC744 \uC2B9\uB9AC\uB85C \uC774\uB04C\uC5C8\uB2E4.\r\n\uC190\uC751\uBBFC\uC740 \uC774\uBC88 \uC2DC\uC98C \uB3D9\uC548 \uAFB8\uC900\uD55C \uD65C\uC57D\uC744 \uD3BC\uCE58\uBA70 \uD300\uC758 \uD575\uC2EC \uC120\uC218\uB85C \uC790\uB9AC\uC7A1\uC558\uB2E4. \uADF8\uC758 \uD0C1\uC6D4\uD55C \uC18D\uB3C4\uC640 \uB0A0\uCE74\uB85C\uC6B4 \uC288\uD305 \uB2A5\uB825\uC740 \uB9CE\uC740 \uCD95\uAD6C \uD32C\uB4E4\uB85C\uBD80\uD130 \uCC2C\uC0AC\uB97C \uBC1B\uACE0 \uC788\uB2E4. \uC774\uBC88 \uACE8\uB85C \uC190\uC751\uBBFC\uC740 \uC544\uC2DC\uC544 \uC120\uC218\uB85C\uC11C \uD504\uB808\uBBF8\uC5B4\uB9AC\uADF8\uC5D0\uC11C \uAC00\uC7A5 \uB9CE\uC740 \uACE8\uC744 \uB123\uC740 \uAE30\uB85D\uC744 \uACBD\uC2E0\uD558\uAC8C \uB418\uC5C8\uB2E4.\r\n\uC190\uC751\uBBFC\uC740 \uACBD\uAE30 \uD6C4 \uC778\uD130\uBDF0\uC5D0\uC11C \"\uD300 \uB3D9\uB8CC\uB4E4\uACFC \uD32C\uB4E4 \uB355\uBD84\uC5D0 \uC774\uB7F0 \uAE30\uB85D\uC744 \uC138\uC6B8 \uC218 \uC788\uC5C8\uB2E4. \uD56D\uC0C1 \uCD5C\uC120\uC744 \uB2E4\uD574 \uD50C\uB808\uC774\uD560 \uAC83\uC774\uBA70, \uB354 \uB9CE\uC740 \uACE8\uB85C \uD300\uC5D0 \uAE30\uC5EC\uD558\uACE0 \uC2F6\uB2E4\"\uACE0 \uC18C\uAC10\uC744 \uBC1D\uD614\uB2E4.\r\n\uD1A0\uD2F0\uB118\uC758 \uAC10\uB3C5\uC740 \"\uC190\uC751\uBBFC\uC740 \uC6B0\uB9AC \uD300\uC758 \uC9C4\uC815\uD55C \uC5D0\uC774\uC2A4\uB2E4. \uADF8\uC758 \uD5CC\uC2E0\uACFC \uB178\uB825\uC740 \uC6B0\uB9AC \uBAA8\uB450\uC5D0\uAC8C \uD070 \uC601\uAC10\uC744 \uC900\uB2E4\"\uBA70 \uC190\uC751\uBBFC\uC744 \uCE6D\uCC2C\uD588\uB2E4.\r\n\uC190\uC751\uBBFC\uC758 \uC774\uBC88 \uAE30\uB85D\uC740 \uB2E8\uC21C\uD55C \uACE8 \uC218\uCE58\uB97C \uB118\uC5B4, \uADF8\uC758 \uB04A\uC784\uC5C6\uB294 \uB178\uB825\uACFC \uC5F4\uC815\uC744 \uC0C1\uC9D5\uD558\uB294 \uAC83\uC774\uB2E4. \uC55E\uC73C\uB85C\uB3C4 \uADF8\uC758 \uD65C\uC57D\uC774 \uACC4\uC18D\uB420\uC9C0 \uC804 \uC138\uACC4 \uCD95\uAD6C \uD32C\uB4E4\uC758 \uC774\uBAA9\uC774 \uC9D1\uC911\uB418\uACE0 \uC788\uB2E4.");
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            
            lblNewLabel_4 = new JLabel("New label");
            lblNewLabel_4.setIcon(new ImageIcon(NewPaper.class.getResource("/background/news.jpg")));
            lblNewLabel_4.setBounds(0, 0, 741, 758);
            contentPane.add(lblNewLabel_4);
      
   }
}
