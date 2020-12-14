package twentytwenty;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static shared.Input.readAsList;
import static shared.Utils.print;

public class DayThirteen {

    private static int partOne(List<String> input) {
        int timestamp = Integer.parseInt(input.get(0));
        return Arrays.stream(input.get(1).split(","))
                .filter(s -> !s.equals("x"))
                .map(Integer::parseInt)
                .min(Comparator.comparingInt(id -> id - (timestamp % id)))
                .map(id -> (id - (timestamp % id)) * id )
                .get();
    }

    private static long partTwo(List<String> busses) {
        long timestamp = 0L;
        long timeDiff = Long.parseLong(busses.get(0));
        for (int i = 1; i < busses.size(); i++) {
            if (!busses.get(i).equals("x")) {
                while ((timestamp + i) % Integer.parseInt(busses.get(i)) != 0)
                    timestamp += timeDiff;
                timeDiff *= Integer.parseInt(busses.get(i));
            }
        }
        return timestamp;
    }

    public static void main(String[] args) {
        List<String> input = readAsList("src\\twentytwenty\\input\\DayThirteen.txt");
        print(partOne(input));
        print(partTwo(Arrays.asList(input.get(1).split(","))));
    }
}