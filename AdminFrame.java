//
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class AdminFrame extends JFrame {
//    private List<String[]> signupDataList = new ArrayList<>();
//    private static final String FILE_PATH = "signupData.json"; // ✅ กำหนดไฟล์ JSON
//
//    public AdminFrame() {
//        setTitle("Admin Panel");
//        setSize(800, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        loadSignupData(); // ✅ โหลดข้อมูลเมื่อเปิดโปรแกรม
//    }
//
//    // ✅ เมธอดเพิ่มข้อมูลจาก Signup.java และบันทึกลงไฟล์
//    public void addSignupData(String cardId, String user, String permissions, String startTime, String expiryTime, String status) {
//        signupDataList.add(new String[]{cardId, user, permissions, startTime, expiryTime, status});
//        saveSignupData(); // ✅ บันทึกข้อมูลลงไฟล์
//    }
//
//    // ✅ ให้ DashboardPanel.java และ AccessControlPanel.java ดึงข้อมูล
//    public List<String[]> getSignupData() {
//        return signupDataList;
//    }
//
//    // ✅ บันทึกข้อมูลลงไฟล์ JSON
//    private void saveSignupData() {
//        try (FileWriter writer = new FileWriter(FILE_PATH)) {
//            Gson gson = new Gson();
//            gson.toJson(signupDataList, writer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // ✅ โหลดข้อมูลจากไฟล์ JSON
//    private void loadSignupData() {
//        try (FileReader reader = new FileReader(FILE_PATH)) {
//            Gson gson = new Gson();
//            signupDataList = gson.fromJson(reader, new TypeToken<List<String[]>>() {}.getType());
//            if (signupDataList == null) {
//                signupDataList = new ArrayList<>();
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("⚠ ไม่มีไฟล์ข้อมูล signupData.json - กำลังสร้างไฟล์ใหม่...");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
