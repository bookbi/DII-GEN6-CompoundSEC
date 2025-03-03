
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;  // สำหรับ Base64 encoding


public class CardManagementPanel extends JPanel {
    private final CustomersFrame customersFrame;
    private JSpinner startTimeSpinner;
    private JSpinner expiryTimeSpinner;
    private JTextField cardIdField;
    private JTextField cardholderNameField;
    private List<String> selectedRooms = new ArrayList<>();
    private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    private JTextArea encryptionResultArea;
    private JButton showEncryptionButton;

    private String selectedFloor = "1"; // ✅ ค่าเริ่มต้นเป็น "1"

    public CardManagementPanel(CustomersFrame customersFrame) {
        this.customersFrame = customersFrame;
        setLayout(new BorderLayout());
        setBackground(new Color(220, 230, 250));


        // 🔹 Header Title
        JLabel title = new JLabel("\uD83D\uDD11 Card Management", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        add(title, BorderLayout.NORTH);

        // 🔹 Main Panel (GridBagLayout)
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(220, 230, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // 🔹 Card ID
        formPanel.add(new JLabel("Card ID :"), gbc);
        gbc.gridx = 1;
        cardIdField = new JTextField(20);
        formPanel.add(cardIdField, gbc);

        // 🔹 Cardholder Name
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Cardholder Name:"), gbc);
        gbc.gridx = 1;
        cardholderNameField = new JTextField(20);
        formPanel.add(cardholderNameField, gbc);

        // 🔹 Permissions
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Permissions:"), gbc);

        JPanel permissionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        permissionsPanel.setBackground(new Color(220, 230, 250));

        JButton floor1Btn = new JButton("Floor 1");
        JButton floor2Btn = new JButton("Floor 2");
        JButton floor3Btn = new JButton("Floor 3");

        permissionsPanel.add(floor1Btn);
        permissionsPanel.add(floor2Btn);
        permissionsPanel.add(floor3Btn);

        gbc.gridx = 1;
        formPanel.add(permissionsPanel, gbc);

        // ✅ อัปเดตค่า selectedFloor และเปิด JFrame ใหม่เพื่อเลือกห้อง
        floor1Btn.addActionListener(e -> {
            selectedFloor = "1";
            openRoomSelection(1);
        });

        floor2Btn.addActionListener(e -> {
            selectedFloor = "2";
            openRoomSelection(2);
        });

        floor3Btn.addActionListener(e -> {
            selectedFloor = "3";
            openRoomSelection(3);
        });

        // 🔹 Start Time
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Start Time:"), gbc);
        gbc.gridx = 1;
        startTimeSpinner = new JSpinner(new SpinnerDateModel());
        startTimeSpinner.setEditor(new JSpinner.DateEditor(startTimeSpinner, "dd-MM-yyyy HH:mm"));
        formPanel.add(startTimeSpinner, gbc);

        // 🔹 Expiry Time
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel("Expiry Time:"), gbc);
        gbc.gridx = 1;
        expiryTimeSpinner = new JSpinner(new SpinnerDateModel());
        expiryTimeSpinner.setEditor(new JSpinner.DateEditor(expiryTimeSpinner, "dd-MM-yyyy HH:mm"));
        formPanel.add(expiryTimeSpinner, gbc);

        add(formPanel, BorderLayout.CENTER);

        // 🔹 Button Panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton validateButton = new JButton("Validate Time");

        buttonPanel.add(addButton);
        buttonPanel.add(validateButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // 🔹 Event Listeners
        validateButton.addActionListener(e -> validateTime());
        addButton.addActionListener(e -> saveData());

        // ปุ่ม Revoke
        JButton revokeButton = new JButton("Revoke");
        revokeButton.addActionListener(e -> revokeCard());
        formPanel.add(revokeButton, gbc);

        // สร้างปุ่มต่างๆ
        showEncryptionButton = new JButton("Show Time-based Encryption");
        showEncryptionButton.setPreferredSize(new Dimension(200, 30));  // ปรับขนาดปุ่ม
        showEncryptionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // เมื่อกดปุ่ม จะสุ่มตัวอักษรและแสดงผลใน JDialog
                String encryptionResult = generateTimeBasedEncryption();
                showEncryptionResult(encryptionResult);
            }
        });

        // จัดเรียงปุ่มให้อยู่ในแถวเดียวกัน

        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));  // จัดเรียงปุ่มแบบแนวนอน

        buttonPanel.add(validateButton);
        buttonPanel.add(showEncryptionButton);  // วางปุ่ม Show Time-based Encryption ไว้ด้านขวา
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        add(buttonPanel, BorderLayout.SOUTH);  // วางแถวปุ่มที่ด้านล่างสุดของหน้าจอ

        // ฟังก์ชันนี้จะทำให้การแสดงผลที่มุมขวาล่าง
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(6, 2));
    }


    // ฟังก์ชัน revokeCard
    private void revokeCard() {
        String cardId = cardIdField.getText();  // ดึงข้อมูลบัตร
        if (!cardId.isEmpty()) {
            // อัปเดตสถานะใน DataModel และฐานข้อมูล
            DataModel.updateCardStatus(cardId, "Revoked");

            // แสดงข้อความยืนยันการเพิกถอน
            JOptionPane.showMessageDialog(this, "✅ Card " + cardId + " has been revoked successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "❌ Please enter a valid Card ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    private void validateTime() {
        Date startTime = (Date) startTimeSpinner.getValue();
        Date expiryTime = (Date) expiryTimeSpinner.getValue();

        if (expiryTime.after(startTime)) {
            JOptionPane.showMessageDialog(this, "✅ Time is valid!", "Validation", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "❌ Expiry Time must be later than Start Time!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openRoomSelection(int floor) {
        JFrame roomFrame = new JFrame("Select Rooms - Floor " + floor);
        roomFrame.setSize(400, 300);
        roomFrame.setLayout(new GridLayout(3, 4, 10, 10));
        roomFrame.getContentPane().setBackground(new Color(220, 230, 250));

        int startRoom = floor * 100 + 1;
        for (int i = 0; i < 12; i++) {
            int roomNumber = startRoom + i;
            JButton roomButton = new JButton("Room " + roomNumber);
            roomButton.setBackground(new Color(255, 230, 250));
            roomButton.addActionListener(new RoomSelectionHandler(roomNumber));
            roomFrame.add(roomButton);
        }

        roomFrame.setVisible(true);
    }

    private class RoomSelectionHandler implements ActionListener {
        private int roomNumber;

        public RoomSelectionHandler(int roomNumber) {
            this.roomNumber = roomNumber;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            selectedRooms.add("Room " + roomNumber);
            JOptionPane.showMessageDialog(null, "Added: Room " + roomNumber);
        }
    }

    private void saveData() {
        String cardId = cardIdField.getText();
        String cardholderName = cardholderNameField.getText();
        String floor = selectedFloor;
        String room = selectedRooms.isEmpty() ? "None" : String.join(", ", selectedRooms);
        String startTime = dateTimeFormat.format(startTimeSpinner.getValue());
        String expiryTime = dateTimeFormat.format(expiryTimeSpinner.getValue());

        // เพิ่มข้อมูลใหม่ใน DataModel
        DataModel.addCard(cardId, cardholderName, floor, room, startTime, expiryTime);
        JOptionPane.showMessageDialog(this, "✅ Card Added Successfully!\n" +
                "Card ID: " + cardId + "\nName: " + cardholderName + "\nFloor: " + floor + "\nRooms: " + room);
    }

    // ฟังก์ชันสำหรับการเข้ารหัส Time-based (สุ่มตัวอักษรและใช้ SHA-256)
    private String generateTimeBasedEncryption() {
        try {
            // สร้างเวลาแบบ timestamp
            long currentTimeMillis = System.currentTimeMillis();  // ใช้เวลาปัจจุบัน
            String timeString = String.valueOf(currentTimeMillis); // แปลงเวลาปัจจุบันเป็น String

            // สร้างข้อความที่ใช้เข้ารหัส (ตัวอย่างที่ใช้เวลาปัจจุบัน)
            String combinedData = "Time-Based Encryption: " + timeString;

            // สร้าง SHA-256 hash
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(combinedData.getBytes());

            // แปลง hash เป็น Base64
            String base64Encoded = Base64.getEncoder().encodeToString(hashBytes);

            return base64Encoded;  // คืนค่าผลลัพธ์ที่เข้ารหัสในรูป Base64
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }
    // ฟังก์ชันแสดงผลลัพธ์การเข้ารหัสใน JDialog
    private void showEncryptionResult(String encryptionResult) {
        JDialog resultDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Encryption Result", true);
        resultDialog.setSize(400, 200);  // ขนาดของ JDialog ที่เล็ก
        resultDialog.setLocationRelativeTo(this);  // วางให้อยู่กลางหน้าต่างหลัก

        JTextArea resultArea = new JTextArea(5, 30);
        resultArea.setText("Time-Based Encryption: \n" + encryptionResult);
        resultArea.setEditable(false); // ไม่ให้แก้ไข
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);

        resultDialog.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultDialog.dispose();  // ปิด JDialog เมื่อกด OK
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        resultDialog.add(buttonPanel, BorderLayout.SOUTH);

        resultDialog.setVisible(true);  // แสดง JDialog
    }



}
