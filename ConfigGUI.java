import javax.swing.*;
import java.awt.*;

// Singleton Configuration Class
class ConfigManager {
    private static ConfigManager instance;
    private String theme = "Light";
    private String language = "English";

    private ConfigManager() {}

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

// GUI Class
public class ConfigGUI extends JFrame {
    private JComboBox<String> themeComboBox, languageComboBox;
    private JLabel resultLabel;

    public ConfigGUI() {
        setTitle("Configuration Manager");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        themeComboBox = new JComboBox<>(new String[]{"Light", "Dark"});
        languageComboBox = new JComboBox<>(new String[]{"English", "Thai"});

        JButton applyButton = new JButton("Apply");
        resultLabel = new JLabel("Settings: " + ConfigManager.getInstance().getTheme() + ", " + ConfigManager.getInstance().getLanguage());

        applyButton.addActionListener(e -> {
            ConfigManager.getInstance().setTheme((String) themeComboBox.getSelectedItem());
            ConfigManager.getInstance().setLanguage((String) languageComboBox.getSelectedItem());
            resultLabel.setText("Settings: " + ConfigManager.getInstance().getTheme() + ", " + ConfigManager.getInstance().getLanguage());
        });

        add(themeComboBox);
        add(languageComboBox);
        add(applyButton);
        add(resultLabel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConfigGUI().setVisible(true));
    }
}
