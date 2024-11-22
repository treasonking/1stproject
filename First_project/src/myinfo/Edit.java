package myinfo;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import loginDAO.LoginDAO;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class Edit extends JFrame {

   private JPanel contentPane;
   private JPasswordField passwordField_1;
   private LoginDAO ldao;
   private boolean isPasswordCorrect = false;
   private JLabel lblNewLabel_1;
   private JButton btnNewButton_1;
   private JButton btnNewButton;
   private JPasswordField passwordField;
   private ActionListener action;
   private ActionListener confirmAction;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               Edit frame = new Edit("testId", 0, 0, 0, 0, 0,0,0,0,0);
               frame.setVisible(true);
               // �߾� ��ġ
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
   public Edit(String id, int viewCount1, double sum5, int score, int cnt, int cnt1,int like0,int like1,int like2,int like3) {
      setResizable(false);
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
      // �߾� ��ġ
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
      btnNewButton_1 = new JButton("");
      btnNewButton_1.setOpaque(false);
      btnNewButton_1.setContentAreaFilled(false);
      btnNewButton_1.setBorderPainted(false);
      btnNewButton_1.setFocusPainted(false);
      SwingUtilities.invokeLater(new Runnable() {// ù ����� ���� Ŀ�� �̵�
         public void run() {
            passwordField.requestFocus();
         }
      });
      action = new ActionListener() {

         public void actionPerformed(ActionEvent e) {// ��й�ȣ �´��� Ȯ��
            String ck = new String(passwordField.getPassword());
            String ps;

            try {
               ps = ldao.passwordck(id, ck);
               if (ck.equals(ps)) {// ��й�ȣ ��ġ��
                  JOptionPane.showMessageDialog(null, "��й�ȣ ��ġ�մϴ�");
                  passwordField_1 = new JPasswordField();
                  passwordField_1.setColumns(10);
                  passwordField_1.setBounds(286, 313, 385, 41);
                  passwordField_1.addActionListener(confirmAction);
                  contentPane.add(passwordField_1);
                  passwordField.setVisible(false);
                  passwordField.setEnabled(false);
                  passwordField_1.setVisible(true);
                  passwordField_1.requestFocus();
                  lblNewLabel_1.setText("������ ��й�ȣ");
                  btnNewButton.setVisible(true);
                  btnNewButton_1.setVisible(false);
                  btnNewButton_1.setEnabled(false);
               }
            } catch (Exception e1) {// ��й�ȣ ����ġ��
               JOptionPane.showMessageDialog(null, "��й�ȣ ��ġ���� �ʽ��ϴ�");
               e1.printStackTrace();
               dispose();
               new MyInfo(id, viewCount1, sum5, score, cnt, cnt1,like0,like1,like2,like3).setVisible(true);
               setVisible(false);
               return; // ������ ����
            }
         }
      };

      btnNewButton_1.addActionListener(action);

      btnNewButton_1.setFont(new Font("������� ExtraBold", Font.PLAIN, 15));
      btnNewButton_1.setBounds(649, 304, 188, 57);
      contentPane.add(btnNewButton_1);

      btnNewButton = new JButton("\uC644\uB8CC");
      btnNewButton.setOpaque(false);
      btnNewButton.setContentAreaFilled(false);
      btnNewButton.setBorderPainted(false);
      btnNewButton.setFocusPainted(false);
      confirmAction = new ActionListener() {

         public void actionPerformed(ActionEvent e) {// ����
            String pw_up = new String(passwordField_1.getPassword());
            boolean b1 = ldao.edit(id, pw_up);
            if (b1) {// ���� ������
               JOptionPane.showMessageDialog(null, "���� �Ϸ� �Ǿ����ϴ�.");
               dispose();
               setVisible(false);
               new MyInfo(id, viewCount1, sum5, score, cnt, cnt1,like0,like1,like2,like3).setVisible(true);
            }

         }
      };
      btnNewButton.addActionListener(confirmAction);

      btnNewButton.setFont(new Font("������� ExtraBold", Font.PLAIN, 15));
      btnNewButton.setBounds(377, 364, 218, 72);
      contentPane.add(btnNewButton);

      lblNewLabel_1 = new JLabel("\uC544\uC774\uB514");
      lblNewLabel_1.setFont(new Font("���� ���", Font.PLAIN, 15));
      lblNewLabel_1.setBounds(286, 262, 373, 41);
      contentPane.add(lblNewLabel_1);

      lblNewLabel_1.setText("���� ��й�ȣ�� �Է� �ϼ���");
      JButton btnNewButton_2 = new JButton("");
      btnNewButton_2.setOpaque(false);
      btnNewButton_2.setContentAreaFilled(false);
      btnNewButton_2.setBorderPainted(false);
      btnNewButton_2.setFocusPainted(false);
      btnNewButton_2.addMouseListener(new MouseAdapter() {// �ڷΰ���
         @Override
         public void mouseClicked(MouseEvent e) {
            dispose();
            setVisible(false);
            new MyInfo(id, viewCount1, sum5, score, cnt, cnt1,like0,like1,like2,like3).setVisible(true);
         }
      });
      btnNewButton_2.setIcon(new ImageIcon(Edit.class.getResource("/ImageIcon/back.png")));
      btnNewButton_2.setFont(new Font("����", Font.PLAIN, 23));
      btnNewButton_2.setBounds(57, 129, 78, 57);
      contentPane.add(btnNewButton_2);

      passwordField = new JPasswordField();
      passwordField.setFont(new Font("����", Font.PLAIN, 15));
      passwordField.setBounds(286, 313, 385, 41);
      passwordField.addActionListener(action);
      contentPane.add(passwordField);

      JLabel lblNewLabel = new JLabel("");
      lblNewLabel.setIcon(new ImageIcon(Edit.class.getResource("/background/back1.jpg")));
      lblNewLabel.setBounds(0, 0, 1001, 758);
      contentPane.add(lblNewLabel);
      btnNewButton.setVisible(false);
      passwordField.requestFocus();

   }
}
