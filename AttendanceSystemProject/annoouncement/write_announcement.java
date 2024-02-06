package annoouncement;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class write_announcement extends JFrame {
    private JTextField title;
    private JComboBox<String> writerComboBox;
    private DatabaseManager databaseManager;

    public write_announcement() {
        setBounds(new Rectangle(600, 0, 450, 280));
        setTitle("�������� ���");

        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("�������� ����");
        lblNewLabel.setBounds(12, 25, 80, 15);
        getContentPane().add(lblNewLabel);

        title = new JTextField("�������� ����");
        title.setBounds(104, 22, 317, 21);
        getContentPane().add(title);
        title.setColumns(10);

        JLabel label1 = new JLabel("�������� ����");
        label1.setBounds(12, 59, 80, 15);
        getContentPane().add(label1);

        JTextArea textArea = new JTextArea("�������� ����");
        textArea.setLineWrap(true);
        textArea.setRows(5);
        textArea.setBounds(104, 53, 317, 69);
        getContentPane().add(textArea);

        JLabel label2 = new JLabel("�ۼ���");
        label2.setBounds(12, 140, 57, 15);
        getContentPane().add(label2);

        // DatabaseManager �ν��Ͻ� ����
        databaseManager = new DatabaseManager("jdbc:mysql://localhost:3306/attendancesystem?characterEncoding=UTF-8&serverTimezone=UTC", "root", "1234");

        // �����ͺ��̽����� ����� ������ �����ͼ� �޺��ڽ��� �߰�
        writerComboBox = new JComboBox<>();
        writerComboBox.setBounds(104, 137, 200, 21);
        populateUserComboBox(); // ����� ���� �����ͼ� �޺��ڽ��� �߰�
        getContentPane().add(writerComboBox);

        JButton btnWrite = new JButton("�ۼ��Ϸ�");
    }

    // �����ͺ��̽����� ����� ������ �����ͼ� �޺��ڽ��� �߰��ϴ� �޼���
    private void populateUserComboBox() {
        try {
            String query = "SELECT user_name FROM user WHERE user_idValue = 1;"; // user_id�� 1�� ����ڸ� ������
            ResultSet resultSet = databaseManager.executeQuery(query);
            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String userName = resultSet.getString("user_name");
                writerComboBox.addItem(userName); // ����� �̸��� �޺��ڽ��� �߰�
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

