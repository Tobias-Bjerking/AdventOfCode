package twentynineteen;

import java.util.List;

import static shared.Input.readAsCSVInt;
import static shared.Input.readAsIntList;
import static shared.Utils.print;

public class DayTwo {

    private static int partOne(IntCodeComputer computer){
        computer.run();
        return computer.memoryDump(0);
    }

    private static int partTwo(List<Integer> input){
        return -1;
    }

    public static void main(String[] args) {
        List<Integer> input = readAsCSVInt("src\\twentynineteen\\input\\DayTwo.txt");
        IntCodeComputer computer = new IntCodeComputer();
        computer.loadProgram(input);
        print(partOne(computer));
        //print(partTwo(input));
    }
}