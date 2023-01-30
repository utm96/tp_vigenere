package org.example.part1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Prog4 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Entrez un texte : ");
        String input = sc.nextLine();
        input = input.toLowerCase().replaceAll("[^a-z]", "");

        System.out.print("Entrez un cle : ");
        String cle = sc.nextLine();
        cle = cle.toLowerCase().replaceAll("[^a-z]", "");
        int cleSize = cle.length();

        Map<Character, Integer> charIntMapping = new HashMap<>();
        Map<Integer, Character> intCharMapping = new HashMap<>();
        int i = 1;
        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
            charIntMapping.put(alphabet, i);
            intCharMapping.put(i, alphabet);
            i++;
        }
        StringBuilder output = new StringBuilder();
        char[] inputArrayChar = input.toCharArray();
        for (int index = 0; index < inputArrayChar.length; index++) {
            char charInput = inputArrayChar[index];
            char charKey = cle.charAt(index % cleSize);
            output.append(encodeCharWithKey(charInput, charKey, charIntMapping, intCharMapping));
        }
        System.out.println("Texte chiffré : " + output);

    }

    public static char encodeCharWithKey(char input, char key, Map<Character, Integer> mapCharInt, Map<Integer, Character> mapIntChar) {
        int inputInt = mapCharInt.get(input);
        int keyInt = mapCharInt.get(key);
        System.out.println(inputInt + " " + keyInt);
        int encodedChar = (inputInt + keyInt) % 26;
        if (encodedChar == 0) encodedChar = 26;
        return mapIntChar.get(encodedChar);
    }
}
