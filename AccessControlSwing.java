

import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class AccessControlSwing {
    private static Map<String, String> CARD_DATABASE = new HashMap<>();
    private JFrame frame;
    private JTextField cardInput;
    private JLabel resultLabel;

    static {
        CARD_DATABASE.put("12345", "high");
        CARD_DATABASE.put("67890", "medium");
    }

    public AccessControlSwing() {
        frame = new JFrame("Access Control System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        cardInput = new JTextField();
        JButton checkButton = new JButton("Check Access");
        resultLabel = new JLabel("Enter card ID", SwingConstants.CENTER);

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAccess();
            }
        });

        panel.add(cardInput);
        panel.add(checkButton);
        panel.add(resultLabel);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void checkAccess() {
        String cardId = cardInput.getText();
        if (CARD_DATABASE.containsKey(cardId)) {
            resultLabel.setText("Access Granted: " + CARD_DATABASE.get(cardId));
        } else {
            resultLabel.setText("Access Denied");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AccessControlSwing());
    }
}
