package twentytwenty;

import java.util.HashMap;
import java.util.List;

import static shared.Input.readAsIntList;
import static shared.Utils.*;

public class DayTen {

    private static int partOne(List<Integer> input) {
        int one = 0, three = 0;
        for (int i = 1; i < input.size(); i++) {
            if (input.get(i) - input.get(i-1) > 1)
                three++;
            else
                one++;
        }
        return one * three;
    }

    private static long partTwo(List<Integer> input, HashMap<Integer, Long> visited) {
        long total = 0;
        int joltage = input.get(0);
        if (input.size() == 1)
            return 1;
        if (input.get(1) == joltage+1) {
            if (visited.containsKey(joltage+1))
                total += visited.get(joltage + 1);
            else {
                long i = partTwo(input.subList(1, input.size()), visited);
                visited.put(joltage+1, i);
                total += i;
            }
        }
        if (input.contains(joltage+2)) {
            if (visited.containsKey(joltage+2))
                total += visited.get(joltage+2);
            else {
                long i = partTwo(input.subList(input.indexOf(joltage+2), input.size()), visited);
                visited.put(joltage+2, i);
                total += i;
            }
        }
        if (input.contains(joltage+3)) {
            if (visited.containsKey(joltage+3))
                total += visited.get(joltage+3);
            else {
                long i = partTwo(input.subList(input.indexOf(joltage+3), input.size()), visited);
                visited.put(joltage+3, i);
                total += i;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        List<Integer> input = readAsIntList("src\\twentytwenty\\input\\DayTen.txt");
        input.add(0);
        input.sort(Integer::compareTo);
        input.add(input.get(input.size()-1) + 3);
        print(partOne(input));
        print(partTwo(input, new HashMap<>()));
    }
}