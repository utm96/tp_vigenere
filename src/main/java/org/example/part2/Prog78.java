package org.example.part2;

import java.util.*;

public class Prog78 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Entrez le cipher : ");
        String input = sc.nextLine();
        input = input.toLowerCase().replaceAll("[^a-z]", "");
        Map<String, Integer> frequencies = new HashMap<>();
        Map<String, Integer> mapResult = new HashMap<>();
        Map<String, Integer> counts = new HashMap<>();
        Map<String, List<Integer>> mapDetailIndex = new HashMap<>();
        int i = 0;
        while (i <= input.length() - 3) {
            for (int j = 3; j + i <= input.length(); j++) {
                System.out.println(i + "," + j);
                String seq = input.substring(i, i + j);
                if (mapDetailIndex.containsKey(seq)) {
                    mapDetailIndex.get(seq).add(i);
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    mapDetailIndex.put(seq, list);
                }
            }
            i++;
        }

        for (Map.Entry<String, List<Integer>> entry : mapDetailIndex.entrySet()) {
            if (entry.getValue().size() > 1) {
//                System.out.println(entry.getKey() + " trouvé " + entry.getValue().size() + " fois");
                System.out.println(entry.getKey() + " trouvé " + entry.getValue());
            }
        }

        TreeMap<String, List<Integer>> counts_final = new TreeMap<String, List<Integer>>(new LengthCompare(mapDetailIndex));

        //Sort elements by putting back in map
        for (Map.Entry<String, List<Integer>> entry : mapDetailIndex.entrySet()) {
            if (entry.getValue().size() > 1) counts_final.put(entry.getKey(), entry.getValue());
        }

        //ex8 supprime les répétitions peu probables : priority : length of seq -> number of repeatation
        int sizeSupprime = counts_final.size() / 10;
        while (sizeSupprime > 0) {
            counts_final.pollLastEntry();
            sizeSupprime--;
        }

        // map factors count
        Map<Integer, Integer> mapFactorsCount = new HashMap<>();
        for (Map.Entry<String, List<Integer>> entry : counts_final.entrySet()) {
            List<Integer> indexList = entry.getValue();
            List<Integer> distanceList = new ArrayList<>();
            for (int index = 0; index < indexList.size() - 1; index++) {
                distanceList.add(indexList.get(index + 1) - indexList.get(index));
            }
            System.out.println("distance: " + distanceList);
            for (Integer distance : distanceList) {
                for (int value = 2; value <= distance; value++) {
                    if (distance % value == 0) {
                        System.out.println("value: " + value);
                        if (mapFactorsCount.containsKey(value)) {
                            int count = mapFactorsCount.get(value);
                            mapFactorsCount.put(value, count + 1);
                        } else mapFactorsCount.put(value, 1);
                    }
                }
            }
        }
        System.out.println(counts_final);
        System.out.println(mapFactorsCount);

        int keyLength = getKeyLenth(mapFactorsCount);
        System.out.println(keyLength);

    }

    private static int getKeyLenth(Map<Integer, Integer> mapFactorsCount) {
        System.out.println("input Map: " + mapFactorsCount);
        int keyLength = -1;
        int keyCount = -1;
        for (Map.Entry<Integer, Integer> entry : mapFactorsCount.entrySet()) {
            if (entry.getValue() > keyCount) {
                keyLength = entry.getKey();
                keyCount = entry.getValue();
            }
        }
        return keyLength;
    }


}

class LengthCompare implements Comparator<String> {

    Map<String, List<Integer>> base;

    public LengthCompare(Map<String, List<Integer>> base) {
        this.base = base;
    }

    @Override
    public int compare(String s1, String s2) {
        int return_val = 0;

        //sort by length
        if (s1.length() < s2.length()) {
            return_val = 1;
        } else if (s1.length() > s2.length()) {
            return_val = -1;
        } else { //sort by count
            if (base.get(s1).size() < base.get(s2).size()) {
                return_val = 1;
            } else if (base.get(s1).size() > base.get(s2).size()) {
                return_val = -1;
            } else {
                //Alphabetical
                return_val = s1.compareTo(s2);
            }
        }
        return return_val;
    }

}//end of private class