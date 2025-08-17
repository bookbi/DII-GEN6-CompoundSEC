
import javax.swing.*;
        import java.awt.*;

public class DashboardPanelA extends JPanel {
    public DashboardPanelA() {
        setLayout(new BorderLayout());
        JLabel title = new JLabel("🏠 Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        JTextArea status = new JTextArea("🚀 ระบบกำลังทำงาน...\n📊 รายงานล่าสุด...");
        status.setFont(new Font("Arial", Font.PLAIN, 16));
        add(new JScrollPane(status), BorderLayout.CENTER);
    }
}
