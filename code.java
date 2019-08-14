import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;
public class AESEncryption {

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("What message you want to send?");
        String messageToEncrypt = input.nextLine();
        System.out.println("Message sent by A: " + messageToEncrypt);
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
    keygen.init(256);
        SecretKey skey = keygen.generateKey();
        byte[] encKey = new byte[]{-32, 74, 61, 28, -125, 118, -124, 85, 37, -23, 88, 110, 32, -76, -55, 74};
        System.out.println("Encrypting  at A's side........");
        String encrypted = encrypt(messageToEncrypt, encKey);
        System.out.println("Hashed Value: " + encrypted);
        System.out.println("Decrypting  at B's side........");
        String originalString = decrypt(encrypted, encKey);
        System.out.println("Message Recieved by B :" + originalString);
    }


    public static String encrypt(String plainText, byte[] rawkey) throws Exception {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(rawkey, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal((new BASE64Decoder()).decodeBuffer(plainText));
            return (new BASE64Encoder()).encode(encrypted);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw e;
        }
    }


    public static String decrypt(String encryptedText, byte[] rawkey) throws Exception {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(rawkey, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] decrypted = cipher.doFinal((new BASE64Decoder()).decodeBuffer(encryptedText));
            return (new BASE64Encoder()).encode(decrypted);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw e;
        }
    }
}
