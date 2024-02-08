package attendancesystema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Counseling extends JFrame {
    private JTextField titleField;
    private JTextField authorField;
    private JTextField subjectField;
    private JCheckBox cb1, cb2, cb3;
    private JTextArea contentArea;

    public Counseling() {
        setTitle("���� ��û");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel for title and author fields
        JPanel titlePanel = new JPanel(new GridLayout(1,4));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JPanel topPanel = new JPanel(new GridLayout(2, 2));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel subjectLabel = new JLabel("���� ����");
        cb1 = new JCheckBox("JAVA");
        cb2 = new JCheckBox("CAD 1��");
        cb3 = new JCheckBox("CAD 2��");
        JLabel titleLabel = new JLabel("����");
        titleField = new JTextField();
        JLabel authorLabel = new JLabel("�޴� ���");
        authorField = new JTextField();
        titlePanel.add(cb1);
        titlePanel.add(cb2);
       	titlePanel.add(cb3);
        titlePanel.add(subjectLabel);
        
        topPanel.add(titleLabel);
        topPanel.add(titleField);
        topPanel.add(authorLabel);
        topPanel.add(authorField);

        // Panel for content area
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        JLabel contentLabel = new JLabel("Content:");
        contentArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(contentArea);

        contentPanel.add(contentLabel, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveArticle();
            }
        });
        buttonPanel.add(saveButton);

        // Add components to main panel
        mainPanel.add(titlePanel,BorderLayout.NORTH);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add main panel to frame
        getContentPane().add(mainPanel);

        // Set frame visible
        setVisible(true);
    }

    private void saveArticle() {
        String title = titleField.getText();
        String author = authorField.getText();
        String content = contentArea.getText();

       
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Counseling();
            }
        });
    }
}