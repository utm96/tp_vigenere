package org.example.part1;

import java.util.Scanner;

public class Prog1 {
    public static void main(String[] args) {
        System.out.print("Entrez un texte : ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        System.out.println("Text: " + input);

    }
}
