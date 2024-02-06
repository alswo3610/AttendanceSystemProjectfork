package annoouncement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class board_announcement extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public board_announcement() {
        setTitle("Announcement Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ���̺� �� �ʱ�ȭ
        tableModel = new DefaultTableModel();
        tableModel.addColumn("����");
        tableModel.addColumn("�Խñ�");
        tableModel.addColumn("�ۼ���");
        tableModel.addColumn("�ۼ�����");

        // ���̺� �ʱ�
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // �����ͺ��̽����� ������ �����ͼ� ���̺� �߰�
        DatabaseManager databaseManager = new DatabaseManager("jdbc:mysql://localhost:3306/attendancesystem?characterEncoding=UTF-8&serverTimezone=UTC", "root", "1234");
        loadDataFromDatabase(databaseManager);

        // ȭ�� ���� ��ư �߰�
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> loadDataFromDatabase(databaseManager));

        // �۾��� ��ư �߰�
        JButton writeButton = new JButton("�۾���");
        writeButton.addActionListener(e -> {
            write_announcement writeAnnouncementWindow = new write_announcement();
            writeAnnouncementWindow.setVisible(true);
        });

        // ���� ��ư �߰�
        JButton deleteButton = new JButton("����");
        deleteButton.addActionListener(e -> {
            // ���õ� �� ���� ��� ����
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int announcementNum = (int) tableModel.getValueAt(selectedRow, 0);
                // ���⼭ �ش� ������ �Խñ��� �����ϴ� �ڵ带 �߰��ϼ���
            } else {
                JOptionPane.showMessageDialog(board_announcement.this, "������ ���� �����ϼ���.", "���", JOptionPane.WARNING_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshButton);
        buttonPanel.add(writeButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    private void loadDataFromDatabase(DatabaseManager databaseManager) {
        try {
            // �����ͺ��̽����� ������ ��������
            String query = "SELECT a.announcement_num, a.announcement_title, u.user_name, a.announcement_date " +
                    "FROM announcement a " +
                    "JOIN user u ON a.user_id = u.user_id";
            ResultSet resultSet = databaseManager.executeQuery(query);

            // ���̺� �� �ʱ�ȭ
            tableModel.setRowCount(0);

            // ����� ���̺� �߰�
            while (resultSet.next()) {
                int announcementNum = resultSet.getInt("announcement_num");
                String title = resultSet.getString("announcement_title");
                String userName = resultSet.getString("user_name");
                Date date = resultSet.getDate("announcement_date");

                Object[] row = {announcementNum, title, userName, date};
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(board_announcement::new);
    }
}
