
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Signup extends JFrame {
    private JTextField txtCardID, txtCardholder;
    private JSpinner startTimeSpinner, expiryTimeSpinner;
    private List<String> selectedRooms = new ArrayList<>();
    private CustomersFrame adminFrame; // ✅ อ้างอิงไปยัง AdminFrame
    private List<String[]> signupDataList; // ✅ ใช้ส่งข้อมูล

    public Signup(CustomersFrame adminFrame, List<String[]> signupDataList) {
        this.adminFrame = adminFrame;
        this.signupDataList = signupDataList; // ✅ ใช้เก็บข้อมูล

        setTitle("Signup - The White Lotus");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // 🎨 Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(250, 600));
        sidebar.setBackground(new Color(33, 76, 160));

        JLabel logo = new JLabel("🪷 The White Lotus", JLabel.CENTER);
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        logo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        sidebar.add(logo);

        JButton btnBack = new JButton("⬅ Back");
        btnBack.addActionListener(e -> {
            this.setVisible(false);  // ซ่อนหน้าต่าง Signup
            adminFrame.setVisible(true); // แสดง CustomersFrame อีกครั้ง
        });
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(btnBack);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));


        // 🎨 Signup Form
        JPanel signupPanel = new JPanel(new BorderLayout());
        signupPanel.setBackground(Color.WHITE);
        signupPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel header = new JLabel("Create Your Account", JLabel.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.setForeground(new Color(33, 76, 160));
        signupPanel.add(header, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Card ID:"), gbc);

        gbc.gridx = 1;
        txtCardID = new JTextField(20);
        formPanel.add(txtCardID, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Cardholder Name:"), gbc);

        gbc.gridx = 1;
        txtCardholder = new JTextField(20);
        formPanel.add(txtCardholder, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Permissions:"), gbc);

        // ✅ เลือก Permissions (ห้อง)
        JPanel permissionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        permissionsPanel.setBackground(Color.WHITE);

        JButton floor1Btn = new JButton("Floor 1");
        JButton floor2Btn = new JButton("Floor 2");
        JButton floor3Btn = new JButton("Floor 3");

        floor1Btn.addActionListener(e -> openRoomSelection(1));
        floor2Btn.addActionListener(e -> openRoomSelection(2));
        floor3Btn.addActionListener(e -> openRoomSelection(3));

        permissionsPanel.add(floor1Btn);
        permissionsPanel.add(floor2Btn);
        permissionsPanel.add(floor3Btn);

        gbc.gridx = 1;
        formPanel.add(permissionsPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Start Time:"), gbc);

        gbc.gridx = 1;
        startTimeSpinner = new JSpinner(new SpinnerDateModel());
        startTimeSpinner.setEditor(new JSpinner.DateEditor(startTimeSpinner, "dd-MM-yyyy HH:mm"));
        formPanel.add(startTimeSpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Expiry Time:"), gbc);

        gbc.gridx = 1;
        expiryTimeSpinner = new JSpinner(new SpinnerDateModel());
        expiryTimeSpinner.setEditor(new JSpinner.DateEditor(expiryTimeSpinner, "dd-MM-yyyy HH:mm"));
        formPanel.add(expiryTimeSpinner, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        JButton btnSignup = new JButton("Sign Up");
        btnSignup.addActionListener(e -> saveSignupData());
        formPanel.add(btnSignup, gbc);

        signupPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(sidebar, BorderLayout.WEST);
        mainPanel.add(signupPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private void saveSignupData() {
        String cardID = txtCardID.getText();
        String cardholder = txtCardholder.getText();
        String startTime = new SimpleDateFormat("dd-MM-yyyy HH:mm").format((Date) startTimeSpinner.getValue());
        String expiryTime = new SimpleDateFormat("dd-MM-yyyy HH:mm").format((Date) expiryTimeSpinner.getValue());
        String permissions = String.join(", ", selectedRooms);

        if (!cardID.isEmpty() && !cardholder.isEmpty() && !permissions.isEmpty()) {
            String[] signupData = {cardID, cardholder, startTime, expiryTime, permissions, "Pending"};
            signupDataList.add(signupData); // ✅ เพิ่มไปที่ List
            adminFrame.updateAdminData(); // ✅ อัปเดต UI ใน AdminFrame

            JOptionPane.showMessageDialog(this, "✅ Signup successful!");
            // setVisible(false); // ถ้าต้องการซ่อนหน้าต่างแทน
        } else {
            JOptionPane.showMessageDialog(this, "❌ Please fill all fields and select permissions!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void openRoomSelection(int floor) {
        new RoomSelectionDialog(this, floor);
    }

    public void addSelectedRooms(List<String> rooms) {
        selectedRooms.addAll(rooms);
    }

    public class RoomSelectionDialog extends JDialog {
        private Signup signup;
        private List<JCheckBox> roomCheckBoxes = new ArrayList<>();

        public RoomSelectionDialog(Signup signup, int floor) {
            super(signup, "Select Rooms - Floor " + floor, true);
            this.signup = signup;

            setLayout(new BorderLayout());
            JPanel roomPanel = new JPanel(new GridLayout(0, 3, 5, 5));

            for (int i = 1; i <= 12; i++) {
                String roomNumber = floor + "0" + i;
                JCheckBox checkBox = new JCheckBox("Room " + roomNumber);
                roomCheckBoxes.add(checkBox);
                roomPanel.add(checkBox);
            }

            JButton btnConfirm = new JButton("Confirm");
            btnConfirm.addActionListener(e -> {
                List<String> selectedRooms = new ArrayList<>();
                for (JCheckBox checkBox : roomCheckBoxes) {
                    if (checkBox.isSelected()) {
                        selectedRooms.add(checkBox.getText());
                    }
                }
                signup.addSelectedRooms(selectedRooms);
                dispose();
            });

            add(new JLabel("Select rooms for Floor " + floor, JLabel.CENTER), BorderLayout.NORTH);
            add(roomPanel, BorderLayout.CENTER);
            add(btnConfirm, BorderLayout.SOUTH);

            setSize(300, 300);
            setLocationRelativeTo(signup);
            setVisible(true);
        }
    }
}
