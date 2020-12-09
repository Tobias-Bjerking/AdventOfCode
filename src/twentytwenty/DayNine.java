package twentytwenty;

import java.util.List;

import static shared.Input.readAsLongList;

public class DayNine {

    private static Long partOne(List<Long> input, int preamble) {
        for (int i = preamble; i < input.size(); i++) {
            List<Long> sums = input.subList(i - preamble, i);
            int index = i;
            if (sums.stream().map(x -> input.get(index) - x).noneMatch(sums::contains))
                return input.get(index);
        }
        return -1L;
    }

    private static long partTwo(List<Long> input, Long target) {
        int i = 0;
        long total = input.get(i);
        for (int j = 1; j < input.size(); j++) {
            total+= input.get(j);
            while (total > target)
                total -= input.get(i++);
            if (total == target)
                return input.subList(i, j).stream().reduce(Math::min).get() + input.subList(i, j).stream().reduce(Math::max).get();
        }
        return -1L;
    }

    public static void main(String[] args) {
        List<Long> input = readAsLongList("src\\twentytwenty\\input\\DayNine.txt");
        System.out.println(partOne(input, 25));
        System.out.println(partTwo(input, partOne(input, 25)));
    }
}