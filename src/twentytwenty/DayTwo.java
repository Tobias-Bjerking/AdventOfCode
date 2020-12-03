package twentytwenty;

import java.util.ArrayList;
import java.util.List;

import static shared.Input.readAsList;
import static shared.Utils.print;

public class DayTwo {

    private static boolean countPartOne(Policy policy) {
        int count = 0;
        for (int i = 0; i < policy.getPassword().length(); i++) {
            char c = policy.getPassword().charAt(i);
            if (policy.getLetter() == c)
                count++;
        }
        return policy.getMin() <= count && count <= policy.getMax();
    }

    private static int partOne(List<Policy> input) {
        int total = 0;
        for (Policy p : input)
            if (countPartOne(p))
                total++;
        return total;
    }

    private static boolean countPartTwo(Policy p) {
        return (p.letter == p.password.charAt(p.min-1) || p.letter == p.password.charAt(p.max-1)) && !(p.letter == p.password.charAt(p.min-1) && p.letter == p.password.charAt(p.max-1));
    }

    private static int partTwo(List<Policy> input) {
        int total = 0;
        for (Policy p : input)
            if (countPartTwo(p))
                total++;
        return total;
    }


    public static void main(String[] args) {
        List<String> strings = readAsList("src\\twentytwenty\\DayTwo.txt");
        List<Policy> input = new ArrayList<>(strings.size());
        for(String line : strings)
            input.add(Policy.parse(line));

        print(partOne(input));
        print(partTwo(input));
    }

    private static class Policy {
        private int min;
        private int max;
        private char letter;
        private String password;

        public int getMin() {
            return min;
        }

        public int getMax() {
            return max;
        }

        public char getLetter() {
            return letter;
        }

        public String getPassword() {
            return password;
        }

        private Policy(int min, int max, char letter, String password) {
            this.min = min;
            this.max = max;
            this.letter = letter;
            this.password = password;
        }

        public static Policy parse(String string) {
            int min = Integer.parseInt(string.split("-")[0]);
            string = string.split("-")[1];
            int max = Integer.parseInt(string.split(" ")[0]);
            String password = string.split(" ")[2];
            string = string.split(" ")[1];
            char letter = string.charAt(0);
            return new Policy(min, max, letter, password);
        }

        @Override
        public String toString() {
            return min + "-" + max + " " + letter + ": " + password;
        }
    }
}