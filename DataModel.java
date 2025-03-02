
import java.util.ArrayList;
import java.util.List;

public class DataModel {
    private static List<String[]> accessCards = new ArrayList<>();
    private static List<DataListener> listeners = new ArrayList<>(); // List ของผู้ที่ติดตามข้อมูล

    // เพิ่มข้อมูลใหม่ และแจ้งให้ทุก Listener รู้
    public static void addCard(String cardId, String user, String floor, String room, String startTime, String expiryTime) {
        accessCards.add(new String[]{cardId, user, floor, room, startTime, expiryTime});
        notifyListeners();
    }

    public static List<String[]> getCards() {
        return accessCards;
    }

    // อัปเดตข้อมูล และแจ้งให้ทุก Listener รู้
    public static void updateCard(String cardId, String floor, String room, String startTime, String expiryTime) {
        for (String[] card : accessCards) {
            if (card[0].equals(cardId)) {
                card[2] = floor;
                card[3] = room;
                card[4] = startTime;
                card[5] = expiryTime;
                break;
            }
        }
        notifyListeners();
    }

    // เพิ่ม Listener ที่ติดตามข้อมูล
    public static void addListener(DataListener listener) {
        listeners.add(listener);
    }

    // แจ้งเตือนทุก Listener ว่ามีข้อมูลเปลี่ยนแปลง
    private static void notifyListeners() {
        for (DataListener listener : listeners) {
            listener.onDataChanged();
        }
    }

}
