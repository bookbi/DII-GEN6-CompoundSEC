import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AdminFrame extends JFrame {
    private List<String[]> signupDataList = new ArrayList<>(); // ✅ เก็บข้อมูลผู้สมัคร

    public AdminFrame() {
        setTitle("Admin Panel");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // ✅ เมธอดเพิ่มข้อมูลจาก Signup.java
    public void addSignupData(String cardId, String user, String permissions, String startTime, String expiryTime, String status) {
        signupDataList.add(new String[]{cardId, user, permissions, startTime, expiryTime, status});
    }

    // ✅ ให้ DashboardPanel.java และ AccessControlPanel.java ดึงข้อมูล
    public List<String[]> getSignupData() {
        return signupDataList;
    }
}

