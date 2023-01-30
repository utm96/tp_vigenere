package org.example.part2;

import com.sun.deploy.util.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Prog14 {
    public static void main(String[] args) {
        String input = "Vyc qxgv gpdzfcdq pks yigimsp jm iaov tmbiivzlv foeygcxg erl exfhfpb mvgd ojbqmcw pgr uf rwth jlkpg pgzlvl qce scwstjrpgr vycb vzgrpar. O rimvkoodcg bg kucpeza rl tlgcpghm kjf udkyu ngia htrbxmwqeya tsukftmwe rls ewvvppkm hfpbl ou ncae ou dyiasorrxvon tmcvsrkq, ih qqdkjgwerrt mvg nyn mvck yc tzifpxmvo nmgdg ceb ih qqetxgqg r pttrgi rwth vyc gxgwcrh pwnc zt vcticrm";
        input = input.toLowerCase().replaceAll("[^a-z]", "");

        Prog14 prog14 = new Prog14();
        String text = prog14.analyserFreq(input, 6);
        System.out.println("text: " + text);
    }

    public static String analyserFreq(String encodedText, int keyLength) {
        Map<Integer, StringBuilder> mapStringColumn = new HashMap<>();
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < keyLength; i++) {
            mapStringColumn.put(i, new StringBuilder());
        }
        for (int i = 0; i < encodedText.length(); i++) {
            mapStringColumn.get(i % keyLength).append(encodedText.charAt(i));
        }
        System.out.println(mapStringColumn);

        for (int i = 0; i < mapStringColumn.size(); i++) {
            System.out.println("Start parse character at position " + (i + 1) + " of key");
            System.out.println("Frequencies: " + getFreqCharacter(mapStringColumn.get(i).toString()));

            System.out.print("Entrez un mapping(ex: a=c) : ");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String[] inputSplited = input.split("=");
            Character character = decode(inputSplited[1], inputSplited[0]).charAt(0);
            System.out.println("=> Character at position " + (i + 1) + " of key: " + character);
            key.append(character);

        }

        System.out.println("key = " + key.toString());
        System.out.println("Start decode");
        System.out.println("cipher: " + encodedText);
        System.out.println("key: " + key);
        return decode(encodedText,key.toString());
    }

    public static Map<Character, Double> getFreqCharacter(String str) {
        HashMap<Character, Integer> charCount = new HashMap<>();
        HashMap<Character, Double> result = new HashMap<>();
        // Count the frequency of each character in the string
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (charCount.containsKey(ch)) {
                charCount.put(ch, charCount.get(ch) + 1);
            } else {
                charCount.put(ch, 1);
            }
        }

        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            result.put(entry.getKey(), entry.getValue() * 1.0 / str.length());
        }
        return result;
    }

    public static String decode(String cipherText, String key) {
        StringBuilder plainText = new StringBuilder();
        for (int i = 0, j = 0; i < cipherText.length(); i++) {
            char c = cipherText.charAt(i);
            if (c >= 'a' && c <= 'z') {
                plainText.append((char) ((c - key.charAt(j) + 26) % 26 + 'a'));
                j = ++j % key.length();
            } else if (c >= 'A' && c <= 'Z') {
                plainText.append((char) ((c - key.charAt(j) + 26) % 26 + 'A'));
                j = ++j % key.length();
            } else {
                plainText.append(c);
            }
        }
        return plainText.toString();
    }
}
