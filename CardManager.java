
import java.util.ArrayList;
import java.util.List;

public class CardManager {
    private List<Card> cards;

    public CardManager() {
        this.cards = new ArrayList<>();
    }

    // เพิ่มบัตร
    public void addCard(String cardID, String accessLevel) {
        String encryptedID = CardManagementSystem.Encryption.encryptCard(cardID);
        cards.add(new Card(encryptedID, accessLevel));
    }

    // แก้ไขบัตร
    public boolean editCard(String cardID, String newAccessLevel) {
        for (Card card : cards) {
            if (CardManagementSystem.Encryption.decryptCard(card.getEncryptedID()).equals(cardID)) {
                card.setAccessLevel(newAccessLevel);
                return true;
            }
        }
        return false; // บัตรไม่พบ
    }

    // ลบบัตร
    public boolean deleteCard(String cardID) {
        return cards.removeIf(card -> CardManagementSystem.Encryption.decryptCard(card.getEncryptedID()).equals(cardID));
    }

    // รับรายการบัตรทั้งหมด
    public List<Card> getCards() {
        return cards;
    }

    // Inner class สำหรับเก็บข้อมูลบัตร
    class Card {
        private String encryptedID;
        private String accessLevel;

        public Card(String encryptedID, String accessLevel) {
            this.encryptedID = encryptedID;
            this.accessLevel = accessLevel;
        }

        public String getEncryptedID() {
            return encryptedID;
        }

        public String getAccessLevel() {
            return accessLevel;
        }

        public void setAccessLevel(String accessLevel) {
            this.accessLevel = accessLevel;
        }
    }
}
