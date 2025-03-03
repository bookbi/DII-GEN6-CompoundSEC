
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AccessControlPanel extends JPanel implements DataListener {
    private JTextField searchField;
    private JButton searchButton;
    private JTable accessTable;
    private DefaultTableModel tableModel;
    private JComboBox<String> floorComboBox, roomComboBox;
    private JTextField startTimeField, expiryTimeField;
    private CustomersFrame customersFrame;
    private JButton updatePermissions;


    // ✅ Constructor ใหม่ รับ CustomersFrame เพื่อดึงข้อมูล Signup
    public AccessControlPanel() {
        this.customersFrame = customersFrame;
        setLayout(new BorderLayout());
        setBackground(new Color(220, 230, 250));
        DataModel.addListener(this);  // แจ้งให้ DataModel ส่งข้อมูลใหม่เมื่อมีการเปลี่ยนแปลง

        // Title
        JLabel title = new JLabel("🔒 Access Control", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        // Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchPanel.add(new JLabel("Cardholder:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        // Table Panel
        String[] columns = {"Card ID", "User", "Permissions","Room", "Start Time", "Expiry Time", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        accessTable = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(accessTable);
        add(tableScroll, BorderLayout.CENTER);

        refreshTable(); // โหลดข้อมูลเมื่อเปิดหน้า

        // Permissions Panel
        JPanel permissionsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        permissionsPanel.setBorder(BorderFactory.createTitledBorder("Update Access"));
        permissionsPanel.setBackground(new Color(220, 230, 250));

        permissionsPanel.add(new JLabel("Select Floor:"));
        floorComboBox = new JComboBox<>(new String[]{"1", "2", "3"});
        permissionsPanel.add(floorComboBox);

        permissionsPanel.add(new JLabel("Select Room:"));
        roomComboBox = new JComboBox<>(new String[]{"101", "102", "103", "104", "201", "305"});
        permissionsPanel.add(roomComboBox);

        permissionsPanel.add(new JLabel("Start Time:"));
        startTimeField = new JTextField(10);
        permissionsPanel.add(startTimeField);

        permissionsPanel.add(new JLabel("Expiry Time:"));
        expiryTimeField = new JTextField(10);
        permissionsPanel.add(expiryTimeField);

        updatePermissions = new JButton("Update Permissions");
        permissionsPanel.add(updatePermissions);

        add(permissionsPanel, BorderLayout.SOUTH);

        // ✅ สมัครเป็น Listener เพื่อให้ DataModel แจ้งเตือนเมื่อมีการเปลี่ยนแปลง
        DataModel.addListener(this);

        // ✅ โหลดข้อมูล Signup ครั้งแรก
        loadDataFromSignup();

        // Action Listeners
        searchButton.addActionListener(e -> searchCard(searchField.getText()));
        updatePermissions.addActionListener(e -> updateAccess());

        // ปุ่ม Revoke ใน AccessControlPanel
        JButton revokeButton = new JButton("Revoke Access");
        revokeButton.addActionListener(e -> revokeAccess());
        permissionsPanel.add(revokeButton);
    }
    @Override
    public void onDataChanged() {
        refreshTable();  // เมื่อข้อมูลเปลี่ยนแปลงให้โหลดข้อมูลใหม่
    }
    private void refreshTable() {
        List<String[]> updatedData = DataModel.getSignupData();  // ดึงข้อมูลจาก DataModel
        updateAccessControlTable(updatedData);  // อัปเดตข้อมูลในตาราง
    }

//    @Override
//    public void onDataChanged() {
//        loadDataFromSignup();  // เมื่อข้อมูลเปลี่ยนแปลงให้โหลดข้อมูลใหม่
//    }

    // ฟังก์ชันในการ Revoke การ์ด
    private void revokeAccess() {
        int selectedRow = accessTable.getSelectedRow();
        if (selectedRow != -1) {
            String cardId = (String) tableModel.getValueAt(selectedRow, 0);
            DataModel.revokeCard(cardId);  // เรียกฟังก์ชัน revokeCard เพื่อเปลี่ยน Status เป็น Denied
            JOptionPane.showMessageDialog(this, "Card " + cardId + " has been revoked.");
        } else {
            JOptionPane.showMessageDialog(this, "❌ Please select a card to revoke.");
        }
    }



    private void loadDataFromSignup() {
        List<String[]> signupData = DataModel.getSignupData();
        updateAccessControlTable(signupData);
    }

    // ✅ อัปเดตตารางให้แสดงข้อมูล Signup ถูกต้อง
    private void updateAccessControlTable(List<String[]> signupData) {
        tableModel.setRowCount(0);  // ลบแถวเก่า
        for (String[] row : signupData) {
            tableModel.addRow(row);  // เพิ่มข้อมูลใหม่
        }
    }


    private void searchCard(String query) {
        tableModel.setRowCount(0);
        List<String[]> cards = DataModel.getCards();
        for (String[] card : cards) {
            if (card[1].toLowerCase().contains(query.toLowerCase())) {
                tableModel.addRow(card);
            }
        }
    }

    private void updateAccess() {
        String floor = (String) floorComboBox.getSelectedItem();
        String room = (String) roomComboBox.getSelectedItem();
        String startTime = startTimeField.getText();
        String expiryTime = expiryTimeField.getText();

        JOptionPane.showMessageDialog(null, "Updated Access for Floor " + floor + " Room " + room +
                "\nStart Time: " + startTime + "\nExpiry Time: " + expiryTime);

        if (accessTable.getSelectedRow() != -1) {
            // เลือกการ์ดที่ต้องการอัปเดตจากตาราง
            String cardId = (String) tableModel.getValueAt(accessTable.getSelectedRow(), 0);
            // เรียกใช้ updateCard() เพื่ออัปเดตข้อมูลการ์ดใน DataModel
            DataModel.updateCard(cardId, floor, room, startTime, expiryTime);
        }
    }





}
