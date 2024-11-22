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
   private JButton btnNewButton_1_1_2; // ���� ��� ��ư
   private JButton btnNewButton_1_1_1_1;
   private JButton btnNewButton_2;
   private JLabel lblNewLabel_2_1; // ���� ��ó url
   private boolean likeButtonClicked = false; // ���ƿ� ��ư �ʱⰪ
   private boolean isFontLarge = false; // �۾� ũ�� �ʱⰪ
   private SoundThread soundThread; // SoundThread ��ü
   private boolean isPlaying = false; // ���� ��� ���¸� ������ ����
   private int currentIndex = 0; // �� ��纰 ������ȣ
   private int[] pageLikes = new int[4]; // �� ����� ���ƿ� ���� �迭
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
               //ȭ�� �߾ӿ� ��ġ
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
	   try {//LoginDAO�� ����
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
      //ȭ���߾ӿ� ǥ��
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
      JButton btnNewButton = new JButton(""); // Right ��ư
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
        	 if (isPlaying & currentIndex == 0) //���� ������ 0�϶�
                 soundThread.stopRunning();//���� ������ ������ ����
              
              if (isPlaying & currentIndex == 1) //���� ������ 1�϶�
                 soundThread.stopRunning();//���� ������ ������ ����

              if (isPlaying & currentIndex == 2) //���� ������ 2�϶�
                 soundThread.stopRunning();//���� ������ ������ ����

            if (currentIndex < 3)//3���� �������� ������ ����
               currentIndex++;

            if (currentIndex == 0) {//������ 1�϶�
            	
            	
               textArea.setText(
                     "��Ƽ�� �ֽ����� ���ݼ� ������(30)�� �����̾�׿��� 31��° ���� ������ �� �ٸ� ������ ����� ������. �̹� ���� ��Ƽ�Ѱ� ��ü���� ����Ƽ���� ����, �������� �Ĺ� 75�п� �������� ���� ������ ���� �¸��� �̲�����.\r\n"
                           + "�������� �̹� ���� ���� ������ Ȱ���� ��ġ�� ���� �ٽ� ������ �ڸ���Ҵ�. ���� Ź���� �ӵ��� ��ī�ο� ���� �ɷ��� ���� �౸ �ҵ�κ��� ���縦 �ް� �ִ�. �̹� ��� �������� �ƽþ� �����μ� �����̾�׿��� ���� ���� ���� ���� ����� ����ϰ� �Ǿ���.\r\n"
                           + "�������� ��� �� ���ͺ信�� \"�� ������ �ҵ� ���п� �̷� ����� ���� �� �־���. �׻� �ּ��� ���� �÷����� ���̸�, �� ���� ��� ���� �⿩�ϰ� �ʹ�\"�� �Ұ��� ������.\r\n"
                           + "��Ƽ���� ������ \"�������� �츮 ���� ������ ���̽���. ���� ��Ű� ����� �츮 ��ο��� ū ������ �ش�\"�� �������� Ī���ߴ�.\r\n"
                           + "�������� �̹� ����� �ܼ��� �� ��ġ�� �Ѿ�, ���� ���Ӿ��� ��°� ������ ��¡�ϴ� ���̴�. �����ε� ���� Ȱ���� ��ӵ��� �� ���� �౸ �ҵ��� �̸��� ���ߵǰ� �ִ�.");
               lblNewLabel.setText("������, �����̾�� 31��° ��");
               lblNewLabel_1.setIcon(new ImageIcon("./src/image/son.jpg"));
               lblNewLabel_2_1.setText(url[0]);
               

               if (pageLikes[0] == 1) {//��õ �ѹ� ������ ���
                  btnNewButton_1_1_1_1.setEnabled(false);
                  likeButtonClicked = true;
               } else {//��õ �� ������ ���
                  btnNewButton_1_1_1_1.setEnabled(true);
                  likeButtonClicked = false;
               }
            }
            if (currentIndex == 1) {//2������ �϶�
            	lblNewLabel_3.setText("��õ��: "+pageLikes_cnt[1]);
               textArea.setText(
                     "NAB�� �������ý��� �������� ������ ī���� ȯ������ ���� �ɷ����� �¸��߽��ϴ�. �̳� ��⿡�� �״� 3�� �� 10���� ������Ű�� ���� �̲������ϴ�. ������ ī�̴� ���� ���� ��ü�� ����� �帧�� �ٲٴ� ���� ������ �ֽ��ϴ�. ���� ���� ������ �ܼ��� ����� ����� ���� �Ѿ, �ҵ鿡�Դ� ������ ������ �˴ϴ�.\r\n"
                           + "������ ī�̴� � �������� �پ ������ ��ɰ� �پ ����� ������ϴ�. ���� �ƹ����� �� ī�� ���� NAB���� Ȱ���� �������� ����������, ���� �������� ����� �����ǿ��� ū ������ ���ƽ��ϴ�. �������� � �������� �������� ��� ���߷°� ���м��� �󱸿� �������, Ư���� ���� ��Ÿ���� �����߽��ϴ�. ���� ���� �ո�� ��Ȯ�� �þߴ� �׸� NAB ����� �ְ��� ���ͷ� ��������ϴ�.\r\n"
                           + "���� ������ Ư�� ū ���뿡�� �巯���ϴ�. NAB �÷��̿��������� ������ ī�̴� ���� �پ �йڰ��� ��Ȳ �Ǵܷ��� �����ݴϴ�. �״� ������ ġ���Ҽ��� ���� ���� ���ϸ�, �������� ������ ���� �����ϴ� ������ ���������� �������ϴ�. ���� �������� ���� ���簡 ������� �����⸦ �ٲٴ� ������ �����ٰ� ���մϴ�.\r\n"
                           + "������ ī�̴� �پ �����μ��� �ɷ��� �Ѿ, Ŀ�´�Ƽ�� ��ȸ���� ū ������ ��Ĩ�ϴ�. �״� �ڼ� ���� ��� Ȱ���� ���� ��ȸ�� å���� ���ϰ� �ֽ��ϴ�. ���� ������� �� ��Ʈ�� �Ѿ, �پ��� �о߿��� ����鿡�� ������ �ְ� �ֽ��ϴ�.\r\n"
                           + "������ ī�̴� ���� �پ ������ �ΰ����� ���� �� �ҵ��� ����� �ް� �ֽ��ϴ�. ���� �̷��� ���� ���� ���� ����� ������ ���Դϴ�. NAB ����� �ְ��� ���� ������ �ڶ��ϴ� ������ ī�̰� ���� � �������� ����� ������, �ҵ��� ����ϸ� ��ٸ��ϴ�.");
               lblNewLabel.setText("������ ī��, �״� �ְ��� ����");
               lblNewLabel_1.setIcon(new ImageIcon("./src/image/basketball-14861_640.jpg"));
               lblNewLabel_2_1.setText(url[1]);

               if (pageLikes[1] == 1) {//��õ �ѹ� ������ ���
                  btnNewButton_1_1_1_1.setEnabled(false);
                  likeButtonClicked = true;
               } else {//��õ �� ������ ���
                  btnNewButton_1_1_1_1.setEnabled(true);
                  likeButtonClicked = false;
               }
            }
            if (currentIndex == 2) {//3������
            	lblNewLabel_3.setText("��õ��: "+pageLikes_cnt[2]);
               textArea.setText(
                     "SSS ���� ����� ������ ���� ���̽����� Ȩ ��⿡�� �����μ� �پ ����� ���������ϴ�. �̳� ���� �߱� �ҵ� ���̿��� ���� ���� �ӿ��� ����Ǿ�����, �� ������ ���� �������μ� �������� ���� ���� ��븦 �����ϸ� ��⸦ �ֵ��߽��ϴ�.\r\n"
                           + "�輱���� ���� �����μ� �׶��忡 �ö� ù �������� ��Ȯ�ϰ� ������ ���� �������ϴ�. �״� 7�̴� ���� ���� �����鿡�� 1��Ÿ ���������� ������ �����ս��� ��������, ��� Ÿ�ڵ��� �Ϻ��� ��������ϴ�. Ư�� �� ������ ��ȭ���� �����̴��� ���� Ÿ�ڵ��� �ռ���� �ϸ� ������ �ҵ鿡�� �ڽŰ��� �ɾ��־����ϴ�.\r\n"
                           + "����, �� ������ ���� �ɷ� �ܿ��� ����� �ַ翡�� ��ø�ϰ� �����Ͽ� ���� �������� ������ �������ϴ�. ���� ����� ��ַ��� ����� �帧�� �¿��ϴ� �߿��� ��ҿ�����, ��� ���ǿ��� ���� ������� ��Ȱ�ϰ� �����Ͽ� �¸��� �̲���½��ϴ�.\r\n"
                           + "�̳� ���� ����� ������ �پ ������ �������� �����̴� ����, SSS ���� ���� ���� �����ϸ� �ҵ鿡�� ū ����� �����߽��ϴ�. �� ������ ���� �ҵ鿡�� �ڽŰ��� ����� �ɾ��ִ� �߿��� ������ �߽��ϴ�.");
               lblNewLabel.setText("\"�����, ������ �����ϸ� �¸�\"");
               lblNewLabel_1.setIcon(new ImageIcon("./src/image/baseball.jpg"));
               lblNewLabel_2_1.setText(url[2]);

               if (pageLikes[2] == 1) {//��õ �ѹ� ���������
                  btnNewButton_1_1_1_1.setEnabled(false);
                  likeButtonClicked = true;
               } else {//��õ �� ���������
                  btnNewButton_1_1_1_1.setEnabled(true);
                  likeButtonClicked = false;
               }
            }
            if (currentIndex == 3) {//4������
            	lblNewLabel_3.setText("��õ��: "+pageLikes_cnt[3]);
               textArea.setText(
                     "�̹� ���� PGS�� ��� ���̿��� ��������. �� ���� �ʹݺ��� ġ���� �������� ��ġ�� ������ ��⸦ �̾��. �׷��� ���� 30��, �谭���� ù ��° ���� ����ϸ� ����� �帧�� �ٲپ���. ���� ù ��° ���� �ڽ� �ۿ��� ȭ���� �帮��� �� ���� ������� ��ģ ��, ������ ������ ������ ����� ���� ����̾���.\r\n"
                           + "���� �谭���� ���� 40�п� �ٽ� �� �� ���� ����ߴ�. �̹����� �߿����� ���� ���� ��, ����� �������� ���ʶ߸��� �ڽ� ������ ������ ħ���ϰ� �������ߴ�. ���� �پ �Ǵܷ°� �ӵ��� ��� ��������� �� �� ƴ�� ���� �ʾҴ�.\r\n"
                           + "���������� �Ĺ� 60��, �谭���� ��ƮƮ���� �ϼ��ϴ� ���� �Ͷ߷ȴ�. ������� �ڳ�ű ��Ȳ���� ������ ������ ������ PGS�� �谭�ο��� ���� �����߰�, �״� ��� ��Ű�ۿ��� 1��1 ��Ȳ���� ħ���ϰ� ���� ��� ������ �о� �־���. �̷ν� �谭���� �ڽ��� ù PGS ��ƮƮ���� ����ϸ� ���� 3-1 �¸��� Ȯ��������.");
               lblNewLabel.setText("�谭��, ȭ���� ��ƮƮ�� �޼�");
               lblNewLabel_1.setIcon(new ImageIcon("./src/image/Lee.jpg"));
               lblNewLabel_2_1.setText(url[3]);
               if (pageLikes[3] == 1) {//��õ �ѹ� ���������
                  btnNewButton_1_1_1_1.setEnabled(false);
                  likeButtonClicked = true;
               } else {//��õ �� ���������
                  btnNewButton_1_1_1_1.setEnabled(true);
                  likeButtonClicked = false;
               }
            }

         }
      });
      btnNewButton.setBackground(Color.WHITE);
      btnNewButton.setFont(new Font("����ü", Font.PLAIN, 17));
      btnNewButton.setIcon(new ImageIcon("./src/image/right.png"));
      btnNewButton.setBounds(681, 705, 46, 41);
      contentPane.add(btnNewButton);

      lblNewLabel = new JLabel("\uC190\uC751\uBBFC, \uD504\uB808\uBBF8\uC5B4\uB9AC\uADF8 31\uBC88\uC9F8 \uACE8");
      lblNewLabel.setFont(new Font("������� ExtraBold", Font.BOLD | Font.ITALIC, 20));
      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel.setBounds(199, 33, 319, 62);
      contentPane.add(lblNewLabel);

      btnNewButton_1 = new JButton(""); // Left ��ư
      btnNewButton_1.setOpaque(false);
      btnNewButton_1.setContentAreaFilled(false);
      btnNewButton_1.setBorderPainted(false);
      btnNewButton_1.setFocusPainted(false);
      btnNewButton_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         }
      });

      JButton btnNewButton_1_1_1 = new JButton(""); // font ũ�� ���� ��ư
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
         public void mouseClicked(MouseEvent e) {//�۾� ũ�� ����
            if (isFontLarge) {
               textArea.setFont(new Font("����ü", Font.PLAIN, 18)); // ���� �۾� ũ��� ����
            } else {
               textArea.setFont(new Font("����ü", Font.BOLD, 30)); // �۾� ũ�� ����
            }
            isFontLarge = !isFontLarge; // ���� ����
         }

      });
      btnNewButton_1_1_1.setIcon(new ImageIcon("./src/image/iconmonstr-text-3-16.png"));
      btnNewButton_1_1_1.setBackground(new Color(255, 255, 255));
      btnNewButton_1_1_1.setBounds(557, 61, 46, 34);
      contentPane.add(btnNewButton_1_1_1);
      
      btnNewButton_1.setVisible(false);
      
      btnNewButton_1.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {//������ư
        	 if(currentIndex == 1) {
        		 btnNewButton_1.setVisible(false);
        	 }
        	 btnNewButton.setVisible(true);
        	 if (isPlaying & currentIndex == 1) //���� ������ 1�϶� ��ư ������ ����
                 soundThread.stopRunning();
              
              if (isPlaying & currentIndex == 2) //���� ������ 2�϶� ��ư ������ ����
                 soundThread.stopRunning();

              if (isPlaying & currentIndex == 3) //���� ������ 3�϶� ��ư ������ ����
                 soundThread.stopRunning();

            if (currentIndex >= 1)//1�̻� �϶� ���� ������ ������
               currentIndex--;

            if (currentIndex == 0) {//1������
            	lblNewLabel_3.setText("��õ��: "+pageLikes_cnt[0]);
               textArea.setText(
                     "��Ƽ�� �ֽ����� ���ݼ� ������(30)�� �����̾�׿��� 31��° ���� ������ �� �ٸ� ������ ����� ������. �̹� ���� ��Ƽ�Ѱ� ��ü���� ����Ƽ���� ����, �������� �Ĺ� 75�п� �������� ���� ������ ���� �¸��� �̲�����.\r\n"
                           + "�������� �̹� ���� ���� ������ Ȱ���� ��ġ�� ���� �ٽ� ������ �ڸ���Ҵ�. ���� Ź���� �ӵ��� ��ī�ο� ���� �ɷ��� ���� �౸ �ҵ�κ��� ���縦 �ް� �ִ�. �̹� ��� �������� �ƽþ� �����μ� �����̾�׿��� ���� ���� ���� ���� ����� ����ϰ� �Ǿ���.\r\n"
                           + "�������� ��� �� ���ͺ信�� \"�� ������ �ҵ� ���п� �̷� ����� ���� �� �־���. �׻� �ּ��� ���� �÷����� ���̸�, �� ���� ��� ���� �⿩�ϰ� �ʹ�\"�� �Ұ��� ������.\r\n"
                           + "��Ƽ���� ������ \"�������� �츮 ���� ������ ���̽���. ���� ��Ű� ����� �츮 ��ο��� ū ������ �ش�\"�� �������� Ī���ߴ�.\r\n"
                           + "�������� �̹� ����� �ܼ��� �� ��ġ�� �Ѿ�, ���� ���Ӿ��� ��°� ������ ��¡�ϴ� ���̴�. �����ε� ���� Ȱ���� ��ӵ��� �� ���� �౸ �ҵ��� �̸��� ���ߵǰ� �ִ�.");
               lblNewLabel.setText("������, �����̾�� 31��° ��");
               lblNewLabel_1.setIcon(new ImageIcon("./src/image/son.jpg"));
               lblNewLabel_2_1.setText(url[0]);
               
               if (pageLikes[0] == 1) {//��õ �ѹ� ���������
                  btnNewButton_1_1_1_1.setEnabled(false);
                  likeButtonClicked = true;
               } else {//��õ �� ���������
                  btnNewButton_1_1_1_1.setEnabled(true);
                  likeButtonClicked = false;
               }
            }
            if (currentIndex == 1) {//2������
            	lblNewLabel_3.setText("��õ��: "+pageLikes_cnt[1]);
               textArea.setText(
                     "NAB�� �������ý��� �������� ������ ī���� ȯ������ ���� �ɷ����� �¸��߽��ϴ�. �̳� ��⿡�� �״� 3�� �� 10���� ������Ű�� ���� �̲������ϴ�. ������ ī�̴� ���� ���� ��ü�� ����� �帧�� �ٲٴ� ���� ������ �ֽ��ϴ�. ���� ���� ������ �ܼ��� ����� ����� ���� �Ѿ, �ҵ鿡�Դ� ������ ������ �˴ϴ�.\r\n"
                           + "������ ī�̴� � �������� �پ ������ ��ɰ� �پ ����� ������ϴ�. ���� �ƹ����� �� ī�� ���� NAB���� Ȱ���� �������� ����������, ���� �������� ����� �����ǿ��� ū ������ ���ƽ��ϴ�. �������� � �������� �������� ��� ���߷°� ���м��� �󱸿� �������, Ư���� ���� ��Ÿ���� �����߽��ϴ�. ���� ���� �ո�� ��Ȯ�� �þߴ� �׸� NAB ����� �ְ��� ���ͷ� ��������ϴ�.\r\n"
                           + "���� ������ Ư�� ū ���뿡�� �巯���ϴ�. NAB �÷��̿��������� ������ ī�̴� ���� �پ �йڰ��� ��Ȳ �Ǵܷ��� �����ݴϴ�. �״� ������ ġ���Ҽ��� ���� ���� ���ϸ�, �������� ������ ���� �����ϴ� ������ ���������� �������ϴ�. ���� �������� ���� ���簡 ������� �����⸦ �ٲٴ� ������ �����ٰ� ���մϴ�.\r\n"
                           + "������ ī�̴� �پ �����μ��� �ɷ��� �Ѿ, Ŀ�´�Ƽ�� ��ȸ���� ū ������ ��Ĩ�ϴ�. �״� �ڼ� ���� ��� Ȱ���� ���� ��ȸ�� å���� ���ϰ� �ֽ��ϴ�. ���� ������� �� ��Ʈ�� �Ѿ, �پ��� �о߿��� ����鿡�� ������ �ְ� �ֽ��ϴ�.\r\n"
                           + "������ ī�̴� ���� �پ ������ �ΰ����� ���� �� �ҵ��� ����� �ް� �ֽ��ϴ�. ���� �̷��� ���� ���� ���� ����� ������ ���Դϴ�. NAB ����� �ְ��� ���� ������ �ڶ��ϴ� ������ ī�̰� ���� � �������� ����� ������, �ҵ��� ����ϸ� ��ٸ��ϴ�.");
               lblNewLabel.setText("������ ī��, �״� �ְ��� ����");
               lblNewLabel_1.setIcon(new ImageIcon("./src/image/basketball-14861_640.jpg"));
               lblNewLabel_2_1.setText(url[1]);
               btnNewButton_1.setVisible(true);
               if (pageLikes[1] == 1) {//��õ �ѹ� ���������
                  btnNewButton_1_1_1_1.setEnabled(false);
                  likeButtonClicked = true;
               } else {//��õ �� ���������
                  btnNewButton_1_1_1_1.setEnabled(true);
                  likeButtonClicked = false;
               }
            }
            if (currentIndex == 2) {//3������
            	lblNewLabel_3.setText("��õ��: "+pageLikes_cnt[2]);
               textArea.setText(
                     "SSS ���� ����� ������ ���� ���̽����� Ȩ ��⿡�� �����μ� �پ ����� ���������ϴ�. �̳� ���� �߱� �ҵ� ���̿��� ���� ���� �ӿ��� ����Ǿ�����, �� ������ ���� �������μ� �������� ���� ���� ��븦 �����ϸ� ��⸦ �ֵ��߽��ϴ�.\r\n"
                           + "�輱���� ���� �����μ� �׶��忡 �ö� ù �������� ��Ȯ�ϰ� ������ ���� �������ϴ�. �״� 7�̴� ���� ���� �����鿡�� 1��Ÿ ���������� ������ �����ս��� ��������, ��� Ÿ�ڵ��� �Ϻ��� ��������ϴ�. Ư�� �� ������ ��ȭ���� �����̴��� ���� Ÿ�ڵ��� �ռ���� �ϸ� ������ �ҵ鿡�� �ڽŰ��� �ɾ��־����ϴ�.\r\n"
                           + "����, �� ������ ���� �ɷ� �ܿ��� ����� �ַ翡�� ��ø�ϰ� �����Ͽ� ���� �������� ������ �������ϴ�. ���� ����� ��ַ��� ����� �帧�� �¿��ϴ� �߿��� ��ҿ�����, ��� ���ǿ��� ���� ������� ��Ȱ�ϰ� �����Ͽ� �¸��� �̲���½��ϴ�.\r\n"
                           + "�̳� ���� ����� ������ �پ ������ �������� �����̴� ����, SSS ���� ���� ���� �����ϸ� �ҵ鿡�� ū ����� �����߽��ϴ�. �� ������ ���� �ҵ鿡�� �ڽŰ��� ����� �ɾ��ִ� �߿��� ������ �߽��ϴ�.");
               lblNewLabel.setText("\"�����, ������ �����ϸ� �¸�\"");
               lblNewLabel_1.setIcon(new ImageIcon("./src/image/baseball.jpg"));
               lblNewLabel_2_1.setText(url[2]);
               btnNewButton_1.setVisible(true);
               if (pageLikes[2] == 1) {//��õ �ѹ� ���������
                  btnNewButton_1_1_1_1.setEnabled(false);
                  likeButtonClicked = true;
               } else {//��õ �� ���������
                  btnNewButton_1_1_1_1.setEnabled(true);
                  likeButtonClicked = false;
               }
            }
            if (currentIndex == 3) {//4������
            	lblNewLabel_3.setText("��õ��: "+pageLikes_cnt[3]);
               textArea.setText(
                     "�̹� ���� PGS�� ��� ���̿��� ��������. �� ���� �ʹݺ��� ġ���� �������� ��ġ�� ������ ��⸦ �̾��. �׷��� ���� 30��, �谭���� ù ��° ���� ����ϸ� ����� �帧�� �ٲپ���. ���� ù ��° ���� �ڽ� �ۿ��� ȭ���� �帮��� �� ���� ������� ��ģ ��, ������ ������ ������ ����� ���� ����̾���.\r\n"
                           + "���� �谭���� ���� 40�п� �ٽ� �� �� ���� ����ߴ�. �̹����� �߿����� ���� ���� ��, ����� �������� ���ʶ߸��� �ڽ� ������ ������ ħ���ϰ� �������ߴ�. ���� �پ �Ǵܷ°� �ӵ��� ��� ��������� �� �� ƴ�� ���� �ʾҴ�.\r\n"
                           + "���������� �Ĺ� 60��, �谭���� ��ƮƮ���� �ϼ��ϴ� ���� �Ͷ߷ȴ�. ������� �ڳ�ű ��Ȳ���� ������ ������ ������ PGS�� �谭�ο��� ���� �����߰�, �״� ��� ��Ű�ۿ��� 1��1 ��Ȳ���� ħ���ϰ� ���� ��� ������ �о� �־���. �̷ν� �谭���� �ڽ��� ù PGS ��ƮƮ���� ����ϸ� ���� 3-1 �¸��� Ȯ��������.");
               lblNewLabel.setText("�谭��, ȭ���� ��ƮƮ�� �޼�");
               lblNewLabel_1.setIcon(new ImageIcon("./src/image/Lee.jpg"));
               lblNewLabel_2_1.setText(url[3]);
               btnNewButton_1.setVisible(true);
               if (pageLikes[3] == 1) {//��õ �ѹ� ���������
                  btnNewButton_1_1_1_1.setEnabled(false);
                  likeButtonClicked = true;
               } else {//��õ �� ���������
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
      lblNewLabel_1.setIcon(new ImageIcon("./src/image/son.jpg")); // �̹��� �ִ� ���
      lblNewLabel_1.setBounds(88, 105, 563, 227);
      contentPane.add(lblNewLabel_1);

      btnNewButton_1_1_2 = new JButton(""); // ���� ��� ��ư
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

            if (currentIndex == 0) {//������ 1�϶� ����Ǵ� �Ҹ�
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

            if (currentIndex == 1) {//������ 2�϶� ����Ǵ� �Ҹ�
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

            if (currentIndex == 2) {//������ 3�϶� ����Ǵ� �Ҹ�
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

            if (currentIndex == 3) {//������ 4�϶� ����Ǵ� �Ҹ�
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
      lblNewLabel_3.setFont(new Font("������� ExtraBold", Font.PLAIN, 15));
      lblNewLabel_3.setBounds(546, 40, 78, 22);
      contentPane.add(lblNewLabel_3);
      lblNewLabel_3.setText("��õ��: "+pageLikes_cnt[0]);
      btnNewButton_1_1_1_1.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {

            if (!likeButtonClicked) { // ���ƿ� ��ư�� Ŭ������ �ʾ��� ���� ����
               likeButtonClicked = true;
               JOptionPane.showMessageDialog(null, "��õ �Ϸ�");
               btnNewButton_1_1_1_1.setEnabled(false); // ���ƿ� ��ư ��Ȱ��ȭ`
               if (currentIndex == 0) {//1������ ���� ������ ��Ȱ��ȭ
                  pageLikes[0] = 1;
                  pageLikes_cnt[0] += 1;
                  ldao.update_chuchen(id, pageLikes[0], pageLikes[1], pageLikes[2], pageLikes[3]);
                  ldao.update_chuchen_cnt(pageLikes_cnt[0], pageLikes_cnt[1], pageLikes_cnt[2], pageLikes_cnt[3]);
                  lblNewLabel_3.setText("��õ��: "+pageLikes_cnt[0]);
               }
               if (currentIndex == 1) {//2������ ���� ������ ��Ȱ��ȭ
                  pageLikes[1] = 1;
                  pageLikes_cnt[1] += 1;
                  ldao.update_chuchen(id, pageLikes[0], pageLikes[1], pageLikes[2], pageLikes[3]);
                  ldao.update_chuchen_cnt(pageLikes_cnt[0], pageLikes_cnt[1], pageLikes_cnt[2], pageLikes_cnt[3]);
                  lblNewLabel_3.setText("��õ��: "+pageLikes_cnt[1]);
               }
               if (currentIndex == 2) {//3������ ���� ������ ��Ȱ��ȭ
                  pageLikes[2] = 1;
                  pageLikes_cnt[2] += 1;
                  ldao.update_chuchen(id, pageLikes[0], pageLikes[1], pageLikes[2], pageLikes[3]);
                  ldao.update_chuchen_cnt(pageLikes_cnt[0], pageLikes_cnt[1], pageLikes_cnt[2], pageLikes_cnt[3]);
                  lblNewLabel_3.setText("��õ��: "+pageLikes_cnt[2]);
               }
               if (currentIndex == 3) {//4������ ���� ������ ��Ȱ��ȭ
                  pageLikes[3] = 1;
                  pageLikes_cnt[3] += 1;
                  ldao.update_chuchen(id, pageLikes[0], pageLikes[1], pageLikes[2], pageLikes[3]);
                  ldao.update_chuchen_cnt(pageLikes_cnt[0], pageLikes_cnt[1], pageLikes_cnt[2], pageLikes_cnt[3]);
                  lblNewLabel_3.setText("��õ��: "+pageLikes_cnt[3]);
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
      if (pageLikes[0] == 1) {//��õ �ѹ� ������ ���
          btnNewButton_1_1_1_1.setEnabled(false);
          likeButtonClicked = true;
       } else {//��õ �� ������ ���
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
      lblNewLabel_2.setFont(new Font("�������", Font.BOLD, 14));
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
      lblNewLabel_2_1.setFont(new Font("����", Font.BOLD, 13));
      lblNewLabel_2_1.setBounds(260, 723, 250, 22);
      contentPane.add(lblNewLabel_2_1);
      
      
            
            scrollPane = new JScrollPane();
            scrollPane.setBounds(91, 344, 563, 358);
            contentPane.add(scrollPane);
      
            textArea = new JTextArea();
            textArea.setBackground(new Color(255, 255, 255));
            scrollPane.setViewportView(textArea);
            textArea.setEditable(false);
            textArea.setFont(new Font("����ü", Font.PLAIN, 18));
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
