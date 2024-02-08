package attendancesystema;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Statics {

    public static void main(String[] args) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendancesystem?serverTimezone=UTC",
                    "root", "1234");
            System.out.println("success");
            Statement stmt = conn.createStatement();

            String query = "SELECT * FROM attendance";
            ResultSet rs = stmt.executeQuery(query);
           

            DefaultPieDataset dataset = new DefaultPieDataset();

            int presentCount = 0;
            int totalCount = 0;
            
            while (rs.next()) {
                String column4Value = rs.getString("attendance_status");
              //  System.out.println(column4Value);

                // "attendance_status"�� "present"�̸� presentCount ����
                if (column4Value.equalsIgnoreCase("present")) {
                    presentCount++;
                }
                totalCount++; // �� �⼮ Ƚ�� ����
            }

            // ���� ��Ʈ�� present�� ���� �ƴ� ����� ���� ǥ��
            dataset.setValue("Present (" + presentCount + " / " + totalCount + ")", presentCount);
            dataset.setValue("Absent or Tardy (" + (totalCount - presentCount) + " / " + totalCount + ")", totalCount - presentCount);

            JFreeChart chart = ChartFactory.createPieChart(
                    "Attendance Statistics",
                    dataset,
                    true,
                    true,
                    false
            );

            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(800, 600));

            JFrame frame = new JFrame("Statistics Pie Chart");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(chartPanel, BorderLayout.CENTER);
            frame.pack(); // Layout�� �籸���Ͽ� ������Ʈ���� �ùٸ��� ǥ�õǵ��� ��
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println("SQLException" + ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}