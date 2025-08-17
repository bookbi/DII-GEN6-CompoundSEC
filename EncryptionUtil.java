import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptionUtil {
    private static final String SECRET_KEY = "YourSecretKeyHere"; // 🔒 ใช้คีย์ลับสำหรับ HMAC

    // ✅ ฟังก์ชันเข้ารหัส Card ID + Expiry Time
    public static String encryptCardID(String cardID, String expiryTime) {
        try {
            String data = cardID + ":" + expiryTime;
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            mac.init(secretKey);
            byte[] hash = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(hash); // ✅ แปลงเป็น Base64 เพื่อให้อ่านง่าย
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // ✅ ฟังก์ชันตรวจสอบบัตรที่เข้ารหัส
    public static boolean verifyCardID(String cardID, String expiryTime, String encryptedCard) {
        String generatedHash = encryptCardID(cardID, expiryTime);
        return generatedHash != null && generatedHash.equals(encryptedCard);
    }


    public static String decryptCardID(String encryptedCard) {

        return encryptedCard;
    }


}
