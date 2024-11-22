package Calendar;

import javax.swing.*;

import mainpage.Mainpage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class Diary extends JFrame{
    private JLabel monthLabel;
    private JPanel calendarPanel;
    private GregorianCalendar cal;
    private static JFrame frame;

    public Diary(String id,double sum5,int score,int cnt,int cnt1,int like0,int like1,int like2,int like3) {
        frame = new JFrame("Diary Calendar");
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Top panel
        JPanel topPanel = new JPanel(new BorderLayout());
        JButton prevMonthBtn = new JButton("\uB3CC\uC544\uAC00\uAE30");
        prevMonthBtn.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 15));
        monthLabel = new JLabel("", JLabel.CENTER);
        monthLabel.setFont(new Font("나눔고딕 ExtraBold", Font.PLAIN, 15));
        topPanel.add(prevMonthBtn, BorderLayout.WEST);
        topPanel.add(monthLabel, BorderLayout.CENTER);
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);

        calendarPanel = new JPanel(new GridLayout(7, 7));
        frame.getContentPane().add(calendarPanel, BorderLayout.CENTER);

        cal = new GregorianCalendar();
        Dimension frameSize = frame.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        updateCalendar();

        prevMonthBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//뒤로가기
                dispose();
                setVisible(false);
                new Mainpage(id,sum5,score,cnt,cnt1,like0,like1,like2,like3).setVisible(true);
                frame.setVisible(false);
                
               
            }
        });
        
        prevMonthBtn.setOpaque(false);
        prevMonthBtn.setContentAreaFilled(false);
        prevMonthBtn.setBorderPainted(false);
        prevMonthBtn.setFocusPainted(false);

        frame.setVisible(true);
    }

    private void updateCalendar() {
        calendarPanel.removeAll();

        cal.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월", Locale.KOREA);
        monthLabel.setText(sdf.format(cal.getTime()));
        
        String[] daysOfWeek = {"일", "월", "화", "수", "목", "금", "토"};
        for (String day : daysOfWeek) {
            JLabel label = new JLabel(day, JLabel.CENTER);
            calendarPanel.add(label);
        }

        int startDay = cal.get(Calendar.DAY_OF_WEEK);

        cal.add(Calendar.MONTH, -1);
        int daysInPrevMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.add(Calendar.MONTH, 1);

        for (int i = 0; i < startDay - 1; i++) {
            JLabel label = new JLabel(String.valueOf(daysInPrevMonth - startDay + 2 + i), JLabel.CENTER);
            label.setForeground(Color.GRAY);
            calendarPanel.add(label);
        }

        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= daysInMonth; i++) {
            JPanel dayPanel = new JPanel(new BorderLayout());
            JLabel dayLabel = new JLabel(String.valueOf(i), JLabel.CENTER);
            dayPanel.add(dayLabel, BorderLayout.NORTH);
            

            if (i == 1 || i == 5 || i == 8 || i == 15 || i == 22 || i == 29) {
                JLabel fixedScheduleLabel = new JLabel("경기 없음", JLabel.CENTER);
                dayPanel.add(fixedScheduleLabel, BorderLayout.CENTER);
            }
            if (i == 2 || i == 3 || i == 4) {
                JLabel fixedScheduleLabel = new JLabel("<html>타이거즈<br>라이온즈</html>", JLabel.CENTER);
                dayPanel.add(fixedScheduleLabel, BorderLayout.CENTER);
            }
            if ( i == 6  ) {
               JLabel fixedScheduleLabel = new JLabel("스타전", JLabel.CENTER);
               dayPanel.add(fixedScheduleLabel, BorderLayout.CENTER);
            }
            if ( i ==9 || i == 10 || i == 11) {
               JLabel fixedScheduleLabel = new JLabel("<html>타이거즈<br>트윈스</html>", JLabel.CENTER);
               dayPanel.add(fixedScheduleLabel, BorderLayout.CENTER);
            }
            if ( i ==12 || i == 13 || i == 14) {
               JLabel fixedScheduleLabel = new JLabel("<html>타이거즈<br>랜더스</html>", JLabel.CENTER);
               dayPanel.add(fixedScheduleLabel, BorderLayout.CENTER);
            }
            if ( i ==16 || i == 17 || i == 18) {
               JLabel fixedScheduleLabel = new JLabel("<html>타이거즈<br>라이온즈</html>", JLabel.CENTER);
               dayPanel.add(fixedScheduleLabel, BorderLayout.CENTER);
            }
            if ( i ==19 || i == 20 || i == 21) {
               JLabel fixedScheduleLabel = new JLabel("<html>타이거즈<br>이글스</html>", JLabel.CENTER);
               dayPanel.add(fixedScheduleLabel, BorderLayout.CENTER);
            }
            if ( i ==23 || i == 24 || i == 25) {
               JLabel fixedScheduleLabel = new JLabel("<html>타이거즈<br>다이노스</html>", JLabel.CENTER);
               dayPanel.add(fixedScheduleLabel, BorderLayout.CENTER);
            }
            if ( i ==26 || i == 27 || i == 28) {
               JLabel fixedScheduleLabel = new JLabel("<html>타이거즈<br>히어로즈</html>", JLabel.CENTER);
               dayPanel.add(fixedScheduleLabel, BorderLayout.CENTER);
            }
            if ( i == 30 || i == 31 ) {
               JLabel fixedScheduleLabel = new JLabel("<html>타이거즈<br>베어스</html>", JLabel.CENTER);
               dayPanel.add(fixedScheduleLabel, BorderLayout.CENTER);
            }
            
            calendarPanel.add(dayPanel);
        }

        int totalSpaces = 42 - (startDay - 1 + daysInMonth);
        for (int i = 1; i <= totalSpaces; i++) {
            JLabel label = new JLabel(String.valueOf(i), JLabel.CENTER);
            label.setForeground(Color.GRAY);
            calendarPanel.add(label);
        }

        calendarPanel.revalidate();
        calendarPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Diary("userId",0,0,0,0,0,0,0,0);
                Dimension frameSize = frame.getSize();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
            }
        });
    }
}

