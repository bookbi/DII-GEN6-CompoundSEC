
import java.time.LocalDateTime;

public class AccessControl {
    private String encodedCard;
    private String accessLevel;

    public AccessControl(String encodedCard, String accessLevel) {
        this.encodedCard = encodedCard;
        this.accessLevel = accessLevel;
    }

    // ตรวจสอบสิทธิ์ของบัตร
    public boolean validateAccess(String cardInput, String levelInput) {
        LocalDateTime currentTime = LocalDateTime.now();
        // จำลองการเข้ารหัส (แค่ตัวอย่าง)
        String expectedCard = "CARD-" + currentTime.getHour(); // Time-Encoded
        return cardInput.equals(expectedCard) && levelInput.equals(accessLevel);
    }
}
