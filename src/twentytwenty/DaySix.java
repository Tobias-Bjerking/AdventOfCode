package twentytwenty;

import java.util.*;

import static shared.Input.readAsLineSeperatedList;
import static shared.Utils.print;

public class DaySix {

    private static int partOne(List<String> groups) {
        int total = 0;
        for (String group: groups) {
            Set<Character> set = new HashSet<>();
            for (String answer : group.split(" "))
                for (char question : answer.toCharArray())
                    set.add(question);

            total += set.size();

        }

        return total;
    }

    private static int partTwo(List<String> groups) {
        int total = 0;
        for (String group : groups) {
            Map<Character, Integer> map = new HashMap<>();
            String[] answers = group.split(" ");
            for (String answer : answers)
                for (char question : answer.toCharArray())
                    map.put(question, map.computeIfAbsent(question, k -> 0) + 1);

            for (int answered : map.values())
                if (answered == answers.length - 1)
                    total++;
        }
        
        return total;
    }

    public static void main(String[] args) {
        List<String> input = readAsLineSeperatedList("src\\twentytwenty\\input\\DaySix.txt");
        print(partOne(input));
        print(partTwo(input));
    }
}