package twentynineteen;

import java.util.List;

import static shared.Input.*;
import static shared.Utils.*;

public class DayOne {

    private static int calculateFuel(int mass){
        return mass / 3 -2;
    }

    private static int partOne(List<Integer> input){
        int total = 0;
        for (int module : input)
            total += calculateFuel(module);
        return total;
    }

    private static int partTwo(List<Integer> input){
        int total = 0;
        for (int module : input) {
            int fuel = module;
            fuel = calculateFuel(fuel);
            while (fuel > 0) {
                total += fuel;
                fuel = calculateFuel(fuel);
            }
        }
        return total;
    }

    public static void main(String[] args) {
        List<Integer> input = readAsIntList("src\\twentynineteen\\input\\DayOne.txt");
        print(partOne(input));
        print(partTwo(input));

    }
}