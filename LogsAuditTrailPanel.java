import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LogsAuditTrailPanel extends JPanel {
    private JTable logTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    public LogsAuditTrailPanel() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("📜 Logs & Audit Trail", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        // สร้างตาราง
        String[] columns = {"Card ID", "Cardholder", "Floor/Room", "Start Time", "Expiry Time", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        logTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(logTable);
        add(scrollPane, BorderLayout.CENTER);

        // แถบค้นหา
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBackground(new Color(220, 230, 250));
        searchPanel.add(new JLabel("Search:"));
        searchField = new JTextField(20);
        JButton searchButton = new JButton("🔍 Search");
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.SOUTH);
    }

    // ฟังก์ชันเพิ่ม log ลงในตาราง
    public void addLog(String timestamp, String cardId, String cardholder, String location, String status) {
        tableModel.addRow(new Object[]{timestamp, cardId, cardholder, location, status});
    }
}
