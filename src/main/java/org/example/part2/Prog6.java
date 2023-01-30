package org.example.part2;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Prog6 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Entrez le cipher : ");
        String input = sc.nextLine();
        input = input.toLowerCase().replaceAll("[^a-z]", "");
        Map<String, Integer> frequencies = new HashMap<>();
        Map<String, Integer> mapResult = new HashMap<>();
        Queue<String> setSequenceToCheck = new LinkedList<>();
        for (int i = 0; i < input.length() - 3; i++) {
            String seqToCheck = input.substring(i, i + 3);
            if (!setSequenceToCheck.contains(seqToCheck)) setSequenceToCheck.add(seqToCheck);
            System.out.println("seqToCheck: " + seqToCheck);
        }

        while (setSequenceToCheck.size() > 0) {

            String seq = setSequenceToCheck.poll();
            System.out.println("Start check : " + seq);
            Pattern pattern = Pattern.compile(seq);
            Matcher matcher = pattern.matcher(input);
            int i = 0;
            List<String> possibleNextCase = new ArrayList<>();
            while (matcher.find()) {
                System.out.println("found: " + seq);
                i++;
                String nextCase = buildNextPossibleCase(seq, input);
                if (nextCase != null && !possibleNextCase.contains(nextCase)) possibleNextCase.add(nextCase);
            }
            if (i > 1) {
                mapResult.put(seq, i);
                setSequenceToCheck.addAll(possibleNextCase);
            }

        }


        for (String seq : mapResult.keySet()) {
            System.out.println(seq +" trouvé " + mapResult.get(seq) + " fois" );
        }
    }

    private static String buildNextPossibleCase(String seq, String input) {
        Pattern pattern = Pattern.compile(seq + ".");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(0);
        } else return null;

    }

}
