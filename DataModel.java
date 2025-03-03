import java.util.ArrayList;
import java.util.List;

public class DataModel {
    private static List<String[]> signupDataList = new ArrayList<>();  // เก็บข้อมูลการสมัคร
    private static List<DataListener> listeners = new ArrayList<>();  // List ของ listeners

    // ฟังก์ชันในการเพิ่มข้อมูลบัตร
    public static void addCard(String cardId, String user, String floor, String room, String startTime, String expiryTime) {
        signupDataList.add(new String[]{cardId, user, floor, room, startTime, expiryTime, "Granted"});  // ตั้งค่า Status เป็น "Granted"
        notifyListeners();  // แจ้งเตือน listeners เมื่อข้อมูลเปลี่ยนแปลง
    }
    // ฟังก์ชันในการอัปเดต Status เป็น Denied เมื่อ Revoke
    public static void revokeCard(String cardId) {
        for (String[] card : signupDataList) {
            if (card[0].equals(cardId)) {
                card[6] = "Denied";  // เปลี่ยน Status เป็น "Denied"
                break;
            }
        }
        notifyListeners();  // แจ้งเตือน listeners เมื่อข้อมูลเปลี่ยนแปลง
    }

    // ฟังก์ชันในการอัปเดตข้อมูลบัตร
    public static void updateCard(String cardId, String floor, String room, String startTime, String expiryTime) {
        // ค้นหาบัตรตาม cardId และอัปเดตข้อมูล
        for (String[] card : signupDataList) {
            if (card[0].equals(cardId)) {  // เปรียบเทียบกับ cardId
                card[2] = floor;           // อัปเดตข้อมูล floor
                card[3] = room;            // อัปเดตข้อมูล room
                card[4] = startTime;       // อัปเดตข้อมูล startTime
                card[5] = expiryTime;      // อัปเดตข้อมูล expiryTime
                break;  // ออกจากลูปเมื่อพบข้อมูล
            }
        }
        notifyListeners();  // แจ้งให้ listeners รับรู้เมื่อข้อมูลมีการเปลี่ยนแปลง
    }
    // ฟังก์ชันในการดึงข้อมูลการสมัครทั้งหมด
    public static List<String[]> getSignupData() {
        return signupDataList;  // คืนค่า signupDataList
    }


    // ฟังก์ชันดึงข้อมูลบัตรทั้งหมด
    public static List<String[]> getCards() {
        return signupDataList;  // คืนค่า signupDataList
    }

    // ฟังก์ชันในการอัปเดตสถานะบัตร
    public static void updateCardStatus(String cardId, String status) {
        for (String[] card : signupDataList) {
            if (card[0].equals(cardId)) {
                card[5] = status;  // เปลี่ยนสถานะของบัตร
                break;
            }
        }
        notifyListeners();  // แจ้งเตือน listeners
    }

    // แจ้งเตือน listeners เมื่อข้อมูลเปลี่ยนแปลง
    private static void notifyListeners() {
        for (DataListener listener : listeners) {
            listener.onDataChanged();
        }
    }

    // ฟังก์ชันในการเพิ่ม listener
    public static void addListener(DataListener listener) {
        listeners.add(listener);  // เพิ่ม listener ลงในรายการ
    }

}
