package twentytwenty;

import java.util.List;

import static shared.Input.*;
import static shared.Utils.*;

public class DayOne {

    private static int partOne(List<Integer> input) {
        for (int x = 0; x < input.size(); x++)
            for (int y = 0; y < input.size(); y++)
                if (input.get(x) + input.get(y) == 2020)
                    return input.get(x) * input.get(y);
        return -1;
    }

    private static int partTwo(List<Integer> input) {
        for (int x = 0; x < input.size(); x++)
            for (int y = 0; y < input.size(); y++)
                for (int z = 0; z < input.size(); z++)
                    if (input.get(x) + input.get(y) + input.get(z)== 2020)
                    return input.get(x) * input.get(y) * input.get(z);
        return -1;
    }

    public static void main(String[] args) {
        List<Integer> input = readAsIntList("src\\twentytwenty\\DayOne.txt");
        print(partOne(input));
        print(partTwo(input));
    }
}