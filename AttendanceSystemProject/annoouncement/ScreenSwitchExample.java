package annoouncement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScreenSwitchExample extends JFrame implements ActionListener {
    JPanel panel1, panel2, panel3, panel4, panel5;
    JButton switchLeftButton, switchRightButton;
    JPanel currentPanel;

    public ScreenSwitchExample() {
        setTitle("Screen Switch Example");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �г� ����
        panel1 = new JPanel();
        panel1.setBackground(Color.WHITE);
        panel1.add(new JLabel("This is Panel 1"));

        panel2 = new JPanel();
        panel2.setBackground(Color.LIGHT_GRAY);
        panel2.add(new JLabel("This is Panel 2"));

        panel3 = new JPanel();
        panel3.setBackground(Color.CYAN);
        panel3.add(new JLabel("This is Panel 3"));

        panel4 = new JPanel();
        panel4.setBackground(Color.YELLOW);
        panel4.add(new JLabel("This is Panel 4"));

        panel5 = new JPanel();
        panel5.setBackground(Color.ORANGE);
        panel5.add(new JLabel("This is Panel 5"));

        // ��ȯ ��ư ����
        switchLeftButton = new JButton("<");
        switchLeftButton.addActionListener(this);

        switchRightButton = new JButton(">");
        switchRightButton.addActionListener(this);

        // �����ӿ� ù ��° �гΰ� ��ư �߰�
        add(panel1, BorderLayout.CENTER);
        add(switchLeftButton, BorderLayout.WEST);
        add(switchRightButton, BorderLayout.EAST);

        // ���� �г� ����
        currentPanel = panel1;

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == switchLeftButton || e.getSource() == switchRightButton) {
            Container contentPane = getContentPane();
            if (e.getSource() == switchLeftButton) {
                if (currentPanel == panel1)
                    currentPanel = panel5;
                else if (currentPanel == panel2)
                    currentPanel = panel1;
                else if (currentPanel == panel3)
                    currentPanel = panel2;
                else if (currentPanel == panel4)
                    currentPanel = panel3;
                else if (currentPanel == panel5)
                    currentPanel = panel4;
            } else if (e.getSource() == switchRightButton) {
                if (currentPanel == panel1)
                    currentPanel = panel2;
                else if (currentPanel == panel2)
                    currentPanel = panel3;
                else if (currentPanel == panel3)
                    currentPanel = panel4;
                else if (currentPanel == panel4)
                    currentPanel = panel5;
                else if (currentPanel == panel5)
                    currentPanel = panel1;
            }

            contentPane.removeAll();
            contentPane.add(currentPanel, BorderLayout.CENTER);
            contentPane.add(switchLeftButton, BorderLayout.WEST);
            contentPane.add(switchRightButton, BorderLayout.EAST);
            validate();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ScreenSwitchExample();
            }
        });
    }
}
