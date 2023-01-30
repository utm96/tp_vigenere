package org.example.part1;

import java.util.Scanner;

public class Prog2 {
    public static void main(String[] args) {
        System.out.print("Entrez un texte : ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        input = input.toLowerCase().replaceAll("[^a-z]", "");
        System.out.print("Texte non chiffré : " + input);

    }
}
