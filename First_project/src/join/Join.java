package join;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import loginDAO.LoginDAO;
import loginmain.Login;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

public class Join extends JFrame {

    private JPanel contentPane;
    private JTextField textField_1;
    private JTextField textField_3;
    private JTextField textField_8;
    private JButton btnNewButton;
    private LoginDAO ldao;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JLabel lblNewLabel_4;
    private JLabel lblNewLabel_5;
    private JLabel lblNewLabel_6;
    private JLabel lblNewLabel_7;
    private JLabel lblNewLabel_8;
    private JPasswordField passwordField;
    private JButton btnNewButton_1;
    private ActionListener joinAction;
    private JLabel lblNewLabel_9;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Join frame = new Join();
                    frame.setVisible(true);
                    //화면 가운데 배치
                    Dimension frameSize = frame.getSize();
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Join(){
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
        //화면 가운데 배치
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
        textField_1 = new JTextField();
        textField_1.setFont(new Font("굴림", Font.PLAIN, 22));
        textField_1.setBounds(391, 218, 280, 48);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        textField_3 = new JTextField();
        textField_3.setFont(new Font("굴림", Font.PLAIN, 22));
        textField_3.setColumns(10);
        textField_3.setBounds(391, 320, 280, 48);
        contentPane.add(textField_3);

        textField_8 = new JTextField();
        textField_8.setFont(new Font("굴림", Font.PLAIN, 22));
        textField_8.setColumns(10);
        textField_8.setBounds(391, 540, 280, 48);
        contentPane.add(textField_8);

        btnNewButton = new JButton("\uD68C\uC6D0\uAC00\uC785");
        btnNewButton.setBackground(new Color(0, 0, 160));
        btnNewButton.setForeground(new Color(255, 255, 255));
        SwingUtilities.invokeLater(new Runnable() {//첫 실행시 강제 커서 이동
            public void run() {
            	textField_1.requestFocus();
            }
        });
        
        ActionListener joinAction = new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                lblNewLabel_4.setText("");//오류메세지 초기화
                lblNewLabel_5.setText("");
                lblNewLabel_6.setText("");
                lblNewLabel_7.setText("");
                String name = textField_1.getText();//이름 가져옴
                String id = textField_3.getText();//아이디 가져옴
                String pw = new String(passwordField.getPassword());//비밀번호 가져옴
                String birth = textField_8.getText();//생년월일 가져옴
                try {
                    String encryptedPw = AESUtil.encrypt(pw);//비밀번호 암호화
                    boolean b7 = ldao.insert_join(name, id, encryptedPw, birth);//데이터베이스에 삽입(암호화된 비밀번호 넣음)
                    if (b7) {//회원가입 성공
                        JOptionPane.showMessageDialog(null, "회원가입 성공!! 로그인 화면으로 돌아갑니다");
                        
                        boolean b71=ldao.insert_recordnews(id, 0);//뉴스 조회수의 초기 값을 0으로 설정
            	       
            	        boolean b2=ldao.insert_Gamewtd_score(id, 0);//게임 점수의 초기 값을 0으로 설정
            	        
            	        boolean b3=ldao.insert_Gameperson_score(id, 0);//퀴즈 점수의 초기 값을 0으로 설정
            	        boolean b72=ldao.insert_chuchen(id, 0,0,0,0);//뉴스 조회수의 초기 값을 0으로 설정
            	        //성공하면 로그인으로 돌아감(화면 가운데 배치)
            	        Login login = new Login();
                        login.setLocationRelativeTo(null);
                        dispose();
                        setVisible(false);
                        login.setVisible(true);
                    } else {//회원가입 실패 했을 경우
                        if (ldao.er == false) {//이미 아이디가 존재할 경우
                            textField_3.setText("");    
                        }
                        if (ldao.bd == false) {//생년월일이 올바른 형식이 아닐 경우
                            textField_8.setText("");
                            btnNewButton.setEnabled(false);
                            Thread.sleep(50);
                            btnNewButton.setEnabled(true);
                            
                        }
                        // 커서 움직임
                        if (name.equals("") && (id.equals("") || pw.equals("") || birth.equals(""))) {
                            textField_1.requestFocus();
                        } else if (id.equals("") && (pw.equals("") || birth.equals(""))) {
                            textField_3.requestFocus();
                        } else if (pw.equals("") && birth.equals("")) {
                            passwordField.requestFocus();
                        } else if (birth.equals("")) {
                            textField_8.requestFocus();
                        }
                        // 오류 텍스트 띄우기
                        if (name.equals("")) {
                            lblNewLabel_4.setText("이름를 입력 해주세요");
                        }
                        if (id.equals("")) {
                            lblNewLabel_5.setText("아이디를 입력 해주세요");
                        }
                        if (pw.equals("")) {
                            lblNewLabel_6.setText("비밀번호를 입력 해주세요");
                        }
                        if (birth.equals("")) {
                            lblNewLabel_7.setText("생년월일을 입력 해주세요");
                        }
                    }
                } catch (Exception ex) {
                    
                }
            }
        };
        
        btnNewButton.addActionListener(joinAction);
        textField_8.addActionListener(joinAction);
        
        
        btnNewButton.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 15));
        btnNewButton.setBounds(438, 635, 170, 50);
        contentPane.add(btnNewButton);

        lblNewLabel = new JLabel("\uC774\uB984");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        lblNewLabel.setBounds(251, 213, 116, 57);
        contentPane.add(lblNewLabel);

        lblNewLabel_1 = new JLabel("\uC544\uC774\uB514");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(251, 310, 116, 57);
        contentPane.add(lblNewLabel_1);

        lblNewLabel_2 = new JLabel("\uBE44\uBC00\uBC88\uD638");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        lblNewLabel_2.setBounds(226, 427, 141, 57);
        contentPane.add(lblNewLabel_2);

        lblNewLabel_3 = new JLabel("\uC0DD\uB144\uC6D4\uC77C");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_3.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
        lblNewLabel_3.setBounds(226, 530, 131, 57);
        contentPane.add(lblNewLabel_3);

        lblNewLabel_4 = new JLabel("");
        lblNewLabel_4.setForeground(new Color(255, 0, 0));
        lblNewLabel_4.setFont(new Font("굴림", Font.PLAIN, 20));
        lblNewLabel_4.setBounds(391, 276, 305, 31);
        contentPane.add(lblNewLabel_4);

        lblNewLabel_5 = new JLabel("");
        lblNewLabel_5.setForeground(new Color(255, 0, 0));
        lblNewLabel_5.setFont(new Font("굴림", Font.PLAIN, 20));
        lblNewLabel_5.setBounds(391, 378, 327, 31);
        contentPane.add(lblNewLabel_5);

        lblNewLabel_6 = new JLabel("");
        lblNewLabel_6.setForeground(new Color(255, 0, 0));
        lblNewLabel_6.setFont(new Font("굴림", Font.PLAIN, 20));
        lblNewLabel_6.setBounds(391, 488, 316, 31);
        contentPane.add(lblNewLabel_6);

        lblNewLabel_7 = new JLabel("");
        lblNewLabel_7.setFont(new Font("굴림", Font.PLAIN, 20));
        lblNewLabel_7.setForeground(new Color(255, 0, 0));
        lblNewLabel_7.setBounds(391, 594, 327, 31);
        contentPane.add(lblNewLabel_7);

        lblNewLabel_8 = new JLabel("\uD68C\uC6D0\uAC00\uC785");
        lblNewLabel_8.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 50));
        lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_8.setBounds(280, 108, 489, 85);
        contentPane.add(lblNewLabel_8);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("굴림", Font.PLAIN, 22));
        passwordField.setBounds(391, 427, 280, 48);
        contentPane.add(passwordField);

        btnNewButton_1 = new JButton("");
        btnNewButton_1.setOpaque(false);
        btnNewButton_1.setContentAreaFilled(false);
        btnNewButton_1.setBorderPainted(false);
        btnNewButton_1.setFocusPainted(false);
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//뒤로가기(가운데 배치)
            	Login login = new Login();
                login.setLocationRelativeTo(null);
                dispose();
                setVisible(false);
                login.setVisible(true);
            }
        });
        btnNewButton_1.setFont(new Font("굴림", Font.PLAIN, 20));
        btnNewButton_1.setIcon(new ImageIcon("./src/ImageIcon/back.png"));
        btnNewButton_1.setBounds(58, 122, 73, 37);
        contentPane.add(btnNewButton_1);
        
        lblNewLabel_9 = new JLabel("");
        lblNewLabel_9.setIcon(new ImageIcon(Join.class.getResource("/background/back1.jpg")));
        lblNewLabel_9.setBounds(0, 0, 1034, 756);
        contentPane.add(lblNewLabel_9);
    }
}
