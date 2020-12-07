package twentytwenty;

import java.util.List;
import java.util.stream.Collectors;

import static shared.Input.readAsList;
import static shared.Utils.print;

public class DayTwo {

    private static boolean countPartOne(Policy policy) {
        long count = policy.getPassword().chars().asDoubleStream()
                .filter(c -> c == policy.getLetter())
                .count();
        return policy.getMin() <= count && count <= policy.getMax();
    }

    private static int partOne(List<Policy> input) {
        return (int) input.stream()
                .filter(DayTwo::countPartOne)
                .count();
    }

    private static boolean countPartTwo(Policy p) {
        return (p.letter == p.password.charAt(p.min-1) || p.letter == p.password.charAt(p.max-1))
                && !(p.letter == p.password.charAt(p.min-1) && p.letter == p.password.charAt(p.max-1));
    }

    private static int partTwo(List<Policy> input) {
        return (int) input.stream()
                .filter(DayTwo::countPartTwo)
                .count();
    }


    public static void main(String[] args) {
        List<Policy> input = readAsList("src\\twentytwenty\\input\\DayTwo.txt").parallelStream()
                .map(Policy::parse)
                .collect(Collectors.toList());
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
    }
}