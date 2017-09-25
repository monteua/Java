
package Vigenere;

import edu.duke.FileResource;

import java.util.Arrays;

public class Tester {
    public static void testCaesarCipher() {
        CaesarCipher cc = new CaesarCipher(3);
        System.out.println(cc.encrypt("Hello World"));
        System.out.println(cc.decrypt("Khoor Zruog"));
    }

    public static void testCaesarCracker() {
        CaesarCracker cc = new CaesarCracker();
        FileResource fr = new FileResource();
        System.out.println(cc.getKey(fr.asString()));
        System.out.println(cc.decrypt(fr.asString()));
    }

    public static void testVigenereCipher() {
        int[] arr = {17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(arr);
        FileResource fr = new FileResource();
        System.out.println(vc.encrypt(fr.asString()));
    }

    public static void testVBSliceString() {
        VigenereBreaker vb = new VigenereBreaker();
            System.out.println(vb.sliceString("abcdefghijklm", 0, 3));
            System.out.println(vb.sliceString("abcdefghijklm", 1, 3));
            System.out.println(vb.sliceString("abcdefghijklm", 2, 3));
            System.out.println(vb.sliceString("abcdefghijklm", 0, 4));
            System.out.println(vb.sliceString("abcdefghijklm", 1, 4));
            System.out.println(vb.sliceString("abcdefghijklm", 2, 4));
            System.out.println(vb.sliceString("abcdefghijklm", 3, 4));
            System.out.println(vb.sliceString("abcdefghijklm", 0, 5));
            System.out.println(vb.sliceString("abcdefghijklm", 1, 5));
            System.out.println(vb.sliceString("abcdefghijklm", 2, 5));
            System.out.println(vb.sliceString("abcdefghijklm", 3, 5));
            System.out.println(vb.sliceString("abcdefghijklm", 4, 5));
    }

    public static void testVBTryKeyLength() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource();
        int[] x = vb.tryKeyLength(fr.asString(), 5, 'e');
        System.out.println(Arrays.toString(x));
    }

    public static void testBreakVigenere() {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();
    }

    public static void main(String[] args) {
        //testCaesarCipher();
        //testCaesarCracker();
        //testVigenereCipher();
        //testVBSliceString();
        //testVBTryKeyLength();
        testBreakVigenere();
    }
}
