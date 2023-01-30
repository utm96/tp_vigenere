package org.example.part1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Prog3 {
    public static void main(String[] args) {
//        Character lettre = args[0].charAt(0);
//        Character cle = args[1].charAt(0);
        Character lettre = 'm';
        Character cle = 'b';
        Map<Character, Integer> charIntMapping = new HashMap<>();
        Map<Integer, Character> intCharMapping = new HashMap<>();
        int i = 1;
        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
            charIntMapping.put(alphabet, i);
            intCharMapping.put(i, alphabet);
            i++;
        }
        int cleInt = charIntMapping.get(cle);
        int lettreInt = charIntMapping.get(lettre);
        int encodedChar = (cleInt + lettreInt) % 26;
        System.out.println(cle + " = " + cleInt);
        System.out.println(lettre + " + " + cleInt + " = " + intCharMapping.get(encodedChar));

    }
}
