
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CardManagementPanel extends JPanel {
    private final CustomersFrame customersFrame;
    private JSpinner startTimeSpinner;
    private JSpinner expiryTimeSpinner;
    private JTextField cardIdField;
    private JTextField cardholderNameField;
    private List<String> selectedRooms = new ArrayList<>();
    private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

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
        String floor = selectedFloor; // ✅ ใช้ค่าชั้นที่ผู้ใช้เลือก
        String room = selectedRooms.isEmpty() ? "None" : String.join(", ", selectedRooms);
        String startTime = dateTimeFormat.format(startTimeSpinner.getValue());
        String expiryTime = dateTimeFormat.format(expiryTimeSpinner.getValue());

        DataModel.addCard(cardId, cardholderName, floor, room, startTime, expiryTime); // ✅ ส่งค่าชั้นที่ถูกต้องไปยัง DataModel

        JOptionPane.showMessageDialog(this, "✅ Card Added Successfully!\n" +
                "Card ID: " + cardId + "\nName: " + cardholderName + "\nFloor: " + floor + "\nRooms: " + room);
    }
}
