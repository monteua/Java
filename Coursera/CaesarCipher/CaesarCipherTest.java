/**
 * Created by VadymStavskyi on 8/6/2017.
 */
package CaesarCipher;

public class CaesarCipherTest {
    public static void main(String[] args) {

        CaesarCipher cc = new CaesarCipher();
        //System.out.println(cc.encryptText("A BAT",19));
        //System.out.println(cc.decryptText("T UTM",19));
        System.out.println(cc.encryptText("Can you imagine life WITHOUT the internet AND computers in your pocket?",15));
        //System.out.println(cc.encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?",21, 8));
        //System.out.println(cc.encryptTwoKeys("Hfs cpwewloj loks cd Hoto kyg Cyy.",26-14, 26-24));

    }
}
