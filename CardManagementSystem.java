
import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;

public class CardManagementSystem extends JFrame {
    private CardManager cardManager;

    private JTextField cardIDField;
    private JTextField accessLevelField;
    private JButton addButton, editButton, deleteButton;
    private JTextArea cardListArea;

    public CardManagementSystem() {
        cardManager = new CardManager();

        // ตั้งค่า GUI
        setTitle("Card Management System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ส่วนบน: ฟอร์มการจัดการบัตร
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel cardIDLabel = new JLabel("Card ID:");
        cardIDField = new JTextField();
        JLabel accessLevelLabel = new JLabel("Access Level:");
        accessLevelField = new JTextField();

        addButton = new JButton("Add Card");
        editButton = new JButton("Edit Card");
        deleteButton = new JButton("Delete Card");

        formPanel.add(cardIDLabel);
        formPanel.add(cardIDField);
        formPanel.add(accessLevelLabel);
        formPanel.add(accessLevelField);
        formPanel.add(addButton);
        formPanel.add(editButton);

        // ส่วนล่าง: รายการบัตร
        JPanel listPanel = new JPanel(new BorderLayout());
        JLabel listLabel = new JLabel("Card List:");
        cardListArea = new JTextArea();
        cardListArea.setEditable(false);

        listPanel.add(listLabel, BorderLayout.NORTH);
        listPanel.add(new JScrollPane(cardListArea), BorderLayout.CENTER);

        // ปุ่มลบที่ด้านล่าง
        JPanel deletePanel = new JPanel();
        deletePanel.add(deleteButton);

        // ใส่ทุกอย่างในหน้าหลัก
        add(formPanel, BorderLayout.NORTH);
        add(listPanel, BorderLayout.CENTER);
        add(deletePanel, BorderLayout.SOUTH);

        // Action Listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddCard();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleEditCard();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDeleteCard();
            }
        });
    }

    private void handleAddCard() {
        String cardID = cardIDField.getText();
        String accessLevel = accessLevelField.getText();

        if (!cardID.isEmpty() && !accessLevel.isEmpty()) {
            cardManager.addCard(cardID, accessLevel);
            updateCardList();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please enter both Card ID and Access Level.");
        }
    }

    private void handleEditCard() {
        String cardID = cardIDField.getText();
        String newAccessLevel = accessLevelField.getText();

        if (!cardID.isEmpty() && !newAccessLevel.isEmpty()) {
            boolean success = cardManager.editCard(cardID, newAccessLevel);
            if (success) {
                updateCardList();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Card not found!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter both Card ID and new Access Level.");
        }
    }

    private void handleDeleteCard() {
        String cardID = cardIDField.getText();

        if (!cardID.isEmpty()) {
            boolean success = cardManager.deleteCard(cardID);
            if (success) {
                updateCardList();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Card not found!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter Card ID to delete.");
        }
    }

    private void updateCardList() {
        StringBuilder listContent = new StringBuilder();
        for (CardManager.Card card : cardManager.getCards()) {
            String cardID = Encryption.decryptCard(card.getEncryptedID());
            String accessLevel = card.getAccessLevel();
            listContent.append("Card ID: ").append(cardID).append(", Access Level: ").append(accessLevel).append("\n");
        }
        cardListArea.setText(listContent.toString());
    }

    private void clearFields() {
        cardIDField.setText("");
        accessLevelField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CardManagementSystem cms = new CardManagementSystem();
            cms.setVisible(true);
        });
    }

    public static class Encryption {
        // ฟังก์ชันเข้ารหัสข้อมูลบัตร
        public static String encryptCard(String cardData) {
            return Base64.getEncoder().encodeToString(cardData.getBytes());
        }

        // ฟังก์ชันถอดรหัสข้อมูลบัตร
        public static String decryptCard(String encryptedData) {
            return new String(Base64.getDecoder().decode(encryptedData));
        }
    }
}
