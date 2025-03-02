
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DashboardPanel extends JPanel {
    private DefaultTableModel tableModel;
    private JTable logTable;
    private JButton refreshButton;
    private CustomersFrame customersFrame;

    public DashboardPanel(CustomersFrame customersFrame) {
        this.customersFrame = customersFrame;
        if (this.customersFrame == null) {
            System.out.println("⚠️ Warning: customersFrame is null in DashboardPanel!");
        }
        setLayout(new BorderLayout());

        JPanel dashboardPanel = createDashboardPanel();
        add(dashboardPanel, BorderLayout.CENTER);

        refreshTable(); // ✅ โหลดข้อมูลเมื่อเปิดหน้า
    }

    private void refreshTable() {
        if (customersFrame != null) {
            List<String[]> updatedData = customersFrame.getSignupData();
            if (updatedData != null) {
                updateTableFromSignup(updatedData);
            } else {
                System.out.println("⚠️ Warning: signupDataList is null.");
            }
        } else {
            System.out.println("⚠️ Warning: customersFrame is null.");
        }
    }

    // ✅ อัปเดตข้อมูลจาก CustomersFrame.java ไปยัง JTable
    public void updateTableFromSignup(List<String[]> signupDataList) {
        if (signupDataList == null || tableModel == null) {
            System.out.println("⚠️ Warning: signupDataList or tableModel is null.");
            return;
        }

        System.out.println("📊 Updating table with " + signupDataList.size() + " records.");
        tableModel.setRowCount(0); // ✅ เคลียร์ข้อมูลเก่า

        for (String[] rowData : signupDataList) {
            if (rowData.length == tableModel.getColumnCount()) { // ✅ ป้องกัน index error
                tableModel.addRow(rowData); // ✅ เติมข้อมูลใหม่
            } else {
                System.out.println("⚠️ Error: rowData size mismatch.");
            }
        }
    }

    private JPanel createDashboardPanel() {
        JPanel dashboardPanel = new JPanel(new BorderLayout());
        JLabel statusLabel = new JLabel("🔄 System Status: Normal", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // ✅ กำหนดคอลัมน์ของตาราง
        String[] columnNames = {"Card ID", "User Name", "Permissions", "Start Time", "Expiry Time", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0);
        logTable = new JTable(tableModel);
        JScrollPane logScrollPane = new JScrollPane(logTable);

        // ✅ ปุ่มต่างๆ
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(220, 230, 250));
        JButton addCardBtn = new JButton("➕ Add New Card");
        JButton suspendCardBtn = new JButton("❌ Suspend Card");
        JButton viewLogsBtn = new JButton("📜 View Logs");
        refreshButton = new JButton("🔄 Refresh");

        addCardBtn.addActionListener(e -> addNewCard());
        suspendCardBtn.addActionListener(e -> suspendCard());
        viewLogsBtn.addActionListener(e -> showLogDialog());
        refreshButton.addActionListener(e -> refreshTable()); // ✅ กดแล้วรีเฟรชข้อมูล

        buttonPanel.add(addCardBtn);
        buttonPanel.add(suspendCardBtn);
        buttonPanel.add(viewLogsBtn);
        buttonPanel.add(refreshButton);

        dashboardPanel.add(statusLabel, BorderLayout.NORTH);
        dashboardPanel.add(logScrollPane, BorderLayout.CENTER);
        dashboardPanel.add(buttonPanel, BorderLayout.SOUTH);

        return dashboardPanel;
    }

    private void addNewCard() {
        JTextField cardIdField = new JTextField();
        JTextField userField = new JTextField();
        JTextField permissionsField = new JTextField(); // ✅ เพิ่มช่องกรอก Permissions
        JSpinner startTimeSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner expiryTimeSpinner = new JSpinner(new SpinnerDateModel());

        startTimeSpinner.setEditor(new JSpinner.DateEditor(startTimeSpinner, "dd-MM-yyyy HH:mm"));
        expiryTimeSpinner.setEditor(new JSpinner.DateEditor(expiryTimeSpinner, "dd-MM-yyyy HH:mm"));

        String[] options = {"Granted", "Denied"};
        JComboBox<String> statusBox = new JComboBox<>(options);

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Card ID:"));
        panel.add(cardIdField);
        panel.add(new JLabel("User:"));
        panel.add(userField);
        panel.add(new JLabel("Permissions:")); // ✅ เพิ่ม Permissions
        panel.add(permissionsField);
        panel.add(new JLabel("Start Time:"));
        panel.add(startTimeSpinner);
        panel.add(new JLabel("Expiry Time:"));
        panel.add(expiryTimeSpinner);
        panel.add(new JLabel("Status:"));
        panel.add(statusBox);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Card", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String cardId = cardIdField.getText();
            String user = userField.getText();
            String permissions = permissionsField.getText(); // ✅ ดึงข้อมูล Permissions
            String startTime = new SimpleDateFormat("dd-MM-yyyy HH:mm").format((Date) startTimeSpinner.getValue());
            String expiryTime = new SimpleDateFormat("dd-MM-yyyy HH:mm").format((Date) expiryTimeSpinner.getValue());
            String status = (String) statusBox.getSelectedItem();

            if (!cardId.isEmpty() && !user.isEmpty()) {
                tableModel.addRow(new String[]{cardId, user, permissions, startTime, expiryTime, status});
            }
        }
    }

    private void suspendCard() {
        int selectedRow = logTable.getSelectedRow();
        if (selectedRow != -1) {
            int statusColumn = logTable.getColumnCount() - 1; // ✅ หา "Status" คอลัมน์
            tableModel.setValueAt("Denied", selectedRow, statusColumn);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a card to suspend.");
        }
    }

    private void showLogDialog() {
        JDialog logDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "View Logs", true);
        logDialog.setSize(400, 300);
        logDialog.setLocationRelativeTo(this);

        JTable logTableCopy = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(logTableCopy);
        logDialog.add(scrollPane);

        logDialog.setVisible(true);
    }
}
