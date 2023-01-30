package org.example.part3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Prog15 {
    public static void main(String[] args) {
        String input = "zpysrrobrwjbvebpwb";
        input = input.toLowerCase().replaceAll("[^a-z]", "");
        String word = "plaintext";
        Prog15 prog15 = new Prog15();
        String text = prog15.getDecodeWithPostion(word, input, 0);
        System.out.println("keyPossible: " + text);

        String key = parseKey(text);
        if (key != null) {
            System.out.println("key: " + key);
        }
    }

    private static String parseKey(String text) {
        int keyLength = -1;
        for (int i = 1; i < text.length(); i++) {
            String textShifted = shiftLeft(text, i);
            if (text.equals(textShifted)) {
                keyLength = i;
                break;
            }
        }
        if (keyLength > -1) {
            return text.substring(0, keyLength);
        }
        return null;
    }


    public static String getDecodeWithPostion(String word, String cipher, int position) {
        String subCipher = cipher.substring(position, position + word.length());
        return decode(subCipher, word);
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

    public static String shiftLeft(String text, int shift) {
        char[] textChars = text.toCharArray();
        for (int i = 0; i < shift; i++) {
            char firstChar = textChars[0];
            for (int j = 0; j < textChars.length - 1; j++) {
                textChars[j] = textChars[j + 1];
            }
            textChars[textChars.length - 1] = firstChar;
        }
        return new String(textChars);
    }
}
