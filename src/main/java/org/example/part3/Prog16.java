package org.example.part3;

public class Prog16 {
    public static void main(String[] args) {
        String input = "bhnvegxxchxbkcjkhmkrqkyaeearymswrbmlq";
        input = input.toLowerCase().replaceAll("[^a-z]", "");
        String word = "plaintext";
        Prog16 prog15 = new Prog16();
        for (int i = 0; i + word.length() < input.length(); i++) {
            System.out.println("Start check position: " + i);
            String text = prog15.getDecodeWithPostion(word, input, i);
            System.out.println("keyPossible: " + text);
            String key = parseKey(text,i);
            if (key != null) {
                System.out.println("key: " + key);
                System.out.println("===== Start Decode ======");
                System.out.println("cipher: " + input);
                System.out.println("key: " + key);
                System.out.println("text: " + decode(input, key));
            } else {
                System.out.println("can not find key with position: " + i);
            }
        }
    }

    private static String parseKey(String textRaw, int postition) {
        int keyLength = -1;
        for (int i = 1; i < textRaw.length(); i++) {
            String text = shiftRight(textRaw, postition%textRaw.length());
            String textShifted = shiftLeft(text, i);
            if (text.equals(textShifted)) {
                keyLength = i;
                return text.substring(0, keyLength);
            }
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
    public static String shiftRight(String text, int shift) {
        char[] textChars = text.toCharArray();
        for (int i = 0; i < shift; i++) {
            char lastChar = textChars[textChars.length - 1];
            for (int j = textChars.length - 1; j > 0; j--) {
                textChars[j] = textChars[j - 1];
            }
            textChars[0] = lastChar;
        }
        return new String(textChars);
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
