package Vigenere;
import java.io.File;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {

    public String sliceString(String message, int whichSlice, int totalSlices) {
        String result = "";
        for (int i=whichSlice; i<message.length(); i+=totalSlices) {
            char ch = message.charAt(i);
            result += ch;
        }
        return result;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cracks = new CaesarCracker();

        for (int i=0;i<klength;i++) {
            key[i] = cracks.getKey(sliceString(encrypted, i, klength));
            //System.out.println(key[i]);
        }
        return key;
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> words = new HashSet<>();
        for (String line: fr.lines()) {
            line = line.toLowerCase();
            words.add(line);
        }
        return words;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;

        String[] words = message.split("\\W+");
        for (int i=0; i<words.length; i++) {
            if (dictionary.contains(words[i].toLowerCase())) {
                count++;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary, char mostCommon, String language) {
        int count = 0;
        String result = "";
        int[] key = {};
        for (int i=1; i<=100; i++) {
            int[] tempKey = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vc = new VigenereCipher(tempKey);
            String tempResult = vc.decrypt(encrypted);
            int tempCount = countWords(tempResult, dictionary);

            if (tempCount >= count) {
                count = tempCount;
                result = tempResult;
                key = tempKey;
            }
        }

        String[] words = result.split("\\W+");
        System.out.println("\n\nTrying to break on: " + language);
        System.out.println("Most common character:" + mostCommon);
        System.out.println("Key found:" + Arrays.toString(key));
        System.out.println("Key length:" + key.length);
        System.out.println("Valid words: " + count);
        System.out.println("Total words: " + words.length);

        return result;
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> mostCommon = new HashMap<>();
        for (String value: dictionary) {
            value = value.toLowerCase();
            for (int i=0; i<value.length(); i++) {
                char ch = value.charAt(i);
                if (mostCommon.containsKey(ch)) {
                    mostCommon.put(ch, mostCommon.get(ch)+1);
                }
                else {
                    mostCommon.put(ch, 1);
                }
            }
        }
        int count = 0;
        char ch = ' ';

        for (Character key: mostCommon.keySet()) {
            if (mostCommon.get(key) > count) {
                count = mostCommon.get(key);
                ch = key;
            }
        }
        return ch;
    }

    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        int count = 0;
        String result = "";
        String lang = "";

        for (String language: languages.keySet()) {
            HashSet<String> dictionary = languages.get(language);
            char mostCommon = mostCommonCharIn(dictionary);
            String tempResult = breakForLanguage(encrypted, dictionary, mostCommon, language);
            int tempCount = countWords(tempResult, dictionary);

            if (tempCount > count) {
                count = tempCount;
                result = tempResult;
                lang = language;
            }
        }
        System.out.println("======Encrypted message is in " + lang + "======");
        return result;
    }

    public void breakVigenere () {
        DirectoryResource dr = new DirectoryResource();
        HashMap<String, HashSet<String>> languages = new HashMap<>();

        for (File file: dr.selectedFiles()) {
            String filename = file.getName();
            FileResource frDict = new FileResource(file);

            System.out.println("Making dictionary for " + filename + " language.");
            HashSet<String> dictionary = readDictionary(frDict);
            languages.put(filename, dictionary);
            System.out.println("Done!");
        }

        FileResource fr = new FileResource();
        String file = fr.asString();

        String decrypted = breakForAllLangs(file, languages);

        System.out.println("\n\n====Decrypted text:====\n\n");
        System.out.println(decrypted);
        //System.out.println(mostCommonCharIn(dictionary));
    }
}
