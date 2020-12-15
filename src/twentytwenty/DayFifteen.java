package twentytwenty;

import java.util.HashMap;
import java.util.List;

import static shared.Input.readAsCSVInt;
import static shared.Utils.*;

public class DayFifteen {

    private static int memoryGame(List<Integer> input, int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < input.size()-1; i++)
            map.put(input.get(i), i+1);

        int previous = input.get(input.size()-1);
        for (int i = input.size(); i < n; i++) {
            if (map.containsKey(previous)) {
                int diff = i-map.get(previous);
                map.put(previous, i);
                previous = diff;
            } else {
                map.put(previous, i);
                previous = 0;
            }
        }
        return previous;
    }

    public static void main(String[] args) {
        List<Integer> input = readAsCSVInt("src\\twentytwenty\\input\\DayFifteen.txt");
        print(memoryGame(input, 2020));
        start();
        print(memoryGame(input, 30000000));
        stop();
    }

}