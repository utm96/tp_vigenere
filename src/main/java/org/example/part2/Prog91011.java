package org.example.part2;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Prog91011 {

//    2.2 Test de Friedman
//    Exercice 9 : Soit Tr un grand texte, généré aléatoirement, en utilisant seulement des lettres
//    minuscules. Quelle est la probabilité Kr que deux lettres choisies aléatoirement soient égales, dans
//    Tr ? 1/26

//    Exercice 10 : Soit Te un grand texte rédigé en anglais, et utilisant uniquement des lettres
//    minuscules. La probabilité que deux lettres choisies aléatoirement soient égales dans Te est environ
//    Ke≈0.067. Expliquez pourquoi cette valeur est différente de la valeur de l’exercice 9

    /**
     * Ex9: Polyalphabetic ciphers ( probability of each letter is 1/26). with big text -> Ke = 1/26
     * Ex10: Monoalphabetic Ciphers ( depend on The frequencies of the letters in English)
     * Ex13: depend on language and the text size
     */
    public static void main(String[] args) {
        String text = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
        double k = calculerK(text);
        System.out.println("K=" + k);

        System.out.println("===== Ex12 =====");
        System.out.println("L= " + (0.076-1.0/26)/(k - 1.0/26));
    }

    public static Map<Character, Integer> getFreqCharacter(String str) {
        HashMap<Character, Integer> charCount = new HashMap<>();
        // Count the frequency of each character in the string
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (charCount.containsKey(ch)) {
                charCount.put(ch, charCount.get(ch) + 1);
            } else {
                charCount.put(ch, 1);
            }
        }

        return charCount;
    }

    private static Double calculerK(String text) {
        Map<Character, Integer> mapFreq = getFreqCharacter(text);
        System.out.println(mapFreq);
        double k = 0;
        for (Map.Entry<Character, Integer> entry : mapFreq.entrySet()) {
            k = k + (entry.getValue() * 1.0 / text.length()) * (entry.getValue() - 1) * 1.0 / (text.length() - 1);
        }
        return k;
    }

}
