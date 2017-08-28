/**
 * Created by VadymStavskyi on 7/31/2017.
 */
package CaesarCipher;

public class CaesarCipher {
    private String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String convertedAlpha;
    private String encryptedText = "";
    private String decryptedText = "";

    public String alphaEncrypt(String letters, int key) {
        String encrypted = letters.substring(key);
        encrypted = encrypted + letters.substring(0, key);
        return encrypted;
    }


    public Character convertChar(String alpha, String convertedAlpha, Character ch) {
        String lowerAlpha = alpha.toLowerCase();

        boolean isLower = Character.isLowerCase(ch);
        if (alpha.indexOf(ch) == -1 && lowerAlpha.indexOf(ch) == -1) {
            return ch;
        }
        if (!isLower) {
            int charPos = alpha.indexOf(ch);
            if (charPos != -1) {
                return convertedAlpha.charAt(charPos);
            }
        }
        else if (isLower) {
            int charPos = lowerAlpha.indexOf(ch);
            if (charPos != -1) {
                Character encryptedChar = convertedAlpha.charAt(charPos);
                return Character.toLowerCase(encryptedChar);
            }
        }
        return ' ';
    }

    public String encryptText(String text, int key) {
        convertedAlpha = alphaEncrypt(letters, key);
        for ( int i = 0; i<text.length(); i++ ) {
            Character plainChar = text.charAt(i);
            encryptedText += convertChar(letters, convertedAlpha, plainChar);
        }
        System.out.println("Line to encrypt: " + text);
        return "Encrypted: " + encryptedText;
    }


    public String decryptText(String text, int key) {
        convertedAlpha = alphaEncrypt(letters, key);
        for ( int i = 0; i<text.length(); i++ ) {
            Character plainChar = text.charAt(i);
            decryptedText += convertChar(convertedAlpha, letters, plainChar);
        }
        System.out.println("Line to decrypt: " + text);
        return "Decrypted: " + decryptedText;
    }

    public String encryptTwoKeys(String text, int key1, int key2) {
        for ( int i = 0; i<text.length(); i++ ) {
            if (i % 2 == 0) {
                convertedAlpha = alphaEncrypt(letters, key1);
                Character plainChar = text.charAt(i);
                encryptedText += convertChar(letters, convertedAlpha, plainChar);
            }
            else {
                String convertedAlpha = alphaEncrypt(letters, key2);
                Character plainChar = text.charAt(i);
                encryptedText += convertChar(letters, convertedAlpha, plainChar);
            }
        }
        System.out.println("Line to encrypt with two keys: " + text);
        return "Encrypted: " + encryptedText;
    }
}
